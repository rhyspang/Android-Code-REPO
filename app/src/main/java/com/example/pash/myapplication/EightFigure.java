package com.example.pash.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pash.myapplication.game_calc.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by PASH on 2016/6/12.
 */

public class EightFigure extends Activity implements View.OnClickListener, Animation.AnimationListener{

    private Button autoMove;
    public static final int ITEM_WIDTH = 80;
    public static final int NUM_TEXT_SIZE = 52;

    /**
     * 块上显示数字
     */
    private List<Integer> nums;
    /**
     * 保存3*3按钮的数组
     */
    private Button[][] buttons;
    private TextView tv_moveTimes;

    private GridLayout gameView;

    /**
     * 空白块
     */
    private Position blackBlock;
    /**
     * 点击的块
     */
    private Position clickedBlock;
    /**
     * 交换次数
     */
    private int moveTimes;

    /**
     * 标记是否正在进行交换动画
     */
    private boolean isAnimating;

    /**
     * 动画图层
     */
    private RelativeLayout mAnimLayout;

    /**
     * 动画图层上的被点击按键
     */
    private Button cButton;

    /**
     * 动画图层上的空白按键
     */
    private Button bButton;

    /**
     * 解决方案移动序列
     */
    private List<Integer> solution;

    /**
     * 第moveCount次自动移动
     */
    private int moveCount;

    /**
     * 是否为根据得出的移动方案的自动移动模式
     */
    private boolean autoMode;

    //定义简单类方便记录块的位置
    class Position {

        int x, y;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridlayout_test);

        blackBlock = new Position();
        clickedBlock = new Position();
        initNums();

        buttons = new Button[][]{
                {       (Button) findViewById(R.id.button1),
                        (Button) findViewById(R.id.button2),
                        (Button) findViewById(R.id.button3)
                },
                {       (Button) findViewById(R.id.button4),
                        (Button) findViewById(R.id.button5),
                        (Button) findViewById(R.id.button6)
                },
                {       (Button) findViewById(R.id.button7),
                        (Button) findViewById(R.id.button8),
                        (Button) findViewById(R.id.button9)
                }
        };

        tv_moveTimes = (TextView) findViewById(R.id.tv_move);
        gameView = (GridLayout) findViewById(R.id.game_view);

        autoMove = (Button) findViewById(R.id.autoMove);
        autoMove.setOnClickListener(this);

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j].setOnClickListener(this);
                if (nums.get(i*3+j) != 9) {
                    buttons[i][j].setText("" + nums.get(i*3+j));
                }
            }
        }

    }

     private void initNums() {
         nums = new ArrayList<>();
         for (int i = 0; i < 9 ; i++) {
             nums.add(i+1);
         }
         Collections.sort(nums, new Comparator<Integer>() {
             @Override
             public int compare(Integer lhs, Integer rhs) {
                 return Math.random() > 0.5 ? 1 : -1;
             }
         });

         int inverseNum = getInverseNum();
         //若逆序数为奇数, 不可能交换使得最后有序, 故交换两个相邻数字使其逆序数奇偶性改变
         if ((inverseNum & 1) != 0) {
             int fNum = -1;
             for (int i = 0; i < nums.size(); i++) {
                 if (fNum == -1 && nums.get(i) != 9) {
                     fNum = i;
                     continue;
                 }
                 if (fNum != -1 && nums.get(i) != 9) {
                     swap(fNum, i);
                     break;
                 }
             }
         }

         //找到空白块
         for (int i = 0; i < nums.size(); i++) {
             if (nums.get(i) == 9) {
                 blackBlock.x = i / 3;
                 blackBlock.y = i % 3;
                 break;
             }
         }

     }

    private int[] toIntArray() {
        int[] int_arr = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            int_arr[i] = nums.get(i);
        }
        return int_arr;
    }
    private void swap(int idx1, int idx2) {
        int t = nums.get(idx1);
        nums.set(idx1, nums.get(idx2));
        nums.set(idx2, t);
    }

    //计算逆序数
    private int getInverseNum() {
        int inverseNum = 0;
        for (int i = 1; i < nums.size(); i++) {
            //排除9
            if (nums.get(i) == 9)
                continue;
            for (int j = 0; j < i; j++) {
                if (nums.get(j) == 9)   continue;
                if(nums.get(j) > nums.get(i)) {
                    inverseNum++;
                }
            }
        }
        return inverseNum;
    }


    @Override
    public void onClick(View v) {
        if (isAnimating) {
            return ;
        }
        int clickedButtonId = -1;
        switch (v.getId()) {
            case R.id.button1:
                clickedButtonId = 1;
                break;
            case R.id.button2:
                clickedButtonId = 2;
                break;
            case R.id.button3:
                clickedButtonId = 3;
                break;
            case R.id.button4:
                clickedButtonId = 4;
                break;
            case R.id.button5:
                clickedButtonId = 5;
                break;
            case R.id.button6:
                clickedButtonId = 6;
                break;
            case R.id.button7:
                clickedButtonId = 7;
                break;
            case R.id.button8:
                clickedButtonId = 8;
                break;
            case R.id.button9:
                clickedButtonId = 9;
                break;
            case R.id.autoMove:
                solution = new Solution().getSolution(toIntArray());
                autoMode = true;
                moveTimes = 0;
                moveCount = 0;
                autoRun();
                break;
            default:
                break;
        }
        if (clickedButtonId != -1) {
            clickedBlock.x = (clickedButtonId-1) / 3;
            clickedBlock.y = (clickedButtonId-1) % 3;
            if (canMove())  moveAction();
        }
    }

    private boolean canMove() {
        if (Math.abs(clickedBlock.x - blackBlock.x) == 1 && clickedBlock.y == blackBlock.y ||
                Math.abs(clickedBlock.y - blackBlock.y) == 1 && clickedBlock.x == blackBlock.x) {
            return true;
        }
        return false;
    }

    private void autoRun() {
        if (moveCount < solution.size()) {
            setMoveConf(solution.get(moveCount++));
            moveAction();
        }else {
            autoMode = false;
        }
    }
    private void setMoveConf(int actionIndex) {
        switch (actionIndex) {
            case 0:
                clickedBlock.x = blackBlock.x - 1;
                clickedBlock.y = blackBlock.y;
                break;
            case 1:
                clickedBlock.x = blackBlock.x + 1;
                clickedBlock.y = blackBlock.y;
                break;
            case 2:
                clickedBlock.x = blackBlock.x;
                clickedBlock.y = blackBlock.y - 1;
                break;
            case 3:
                clickedBlock.x = blackBlock.x;
                clickedBlock.y = blackBlock.y + 1;
                break;
            default:
                break;
        }
    }

    private void moveAction() {
        setUpAnimLayout();
        cButton = new Button(this);
        int gridLeft = gameView.getLeft();
        int gridTop = gameView.getTop();
        int cbLeft = buttons[clickedBlock.x][clickedBlock.y].getLeft();
        int cbTop = buttons[clickedBlock.x][clickedBlock.y].getTop();
        RelativeLayout.LayoutParams cbLP = new RelativeLayout.LayoutParams(
                getPxValue(ITEM_WIDTH), getPxValue(ITEM_WIDTH)
        );
        cbLP.leftMargin = gridLeft + cbLeft;
        cbLP.topMargin = gridTop + cbTop;
        cButton.setLayoutParams(cbLP);
        cButton.setText(buttons[clickedBlock.x][clickedBlock.y].getText());
        cButton.setTextSize(NUM_TEXT_SIZE);
        cButton.setTextColor(getResources().getColor(R.color.colorPrimary));
        cButton.setBackground(getResources().getDrawable(R.drawable.ashen_bg));
        mAnimLayout.addView(cButton);

        bButton = new Button(this);
        int bbLeft = buttons[blackBlock.x][blackBlock.y].getLeft();
        int bbTop = buttons[blackBlock.x][blackBlock.y].getTop();
        RelativeLayout.LayoutParams bbLP = new RelativeLayout.LayoutParams(
                getPxValue(ITEM_WIDTH), getPxValue(ITEM_WIDTH));
        bbLP.leftMargin = gridLeft + bbLeft;
        bbLP.topMargin = gridTop + bbTop;
        bButton.setLayoutParams(bbLP);
        bButton.setBackground(getResources().getDrawable(R.drawable.ashen_bg));
        mAnimLayout.addView(bButton);

        if (clickedBlock.x + 1 == blackBlock.x) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.move_down);
            animation.setFillAfter(true);
            cButton.startAnimation(animation);

            animation = AnimationUtils.loadAnimation(this, R.anim.move_up);
            animation.setFillAfter(true);
            animation.setAnimationListener(this);
            bButton.startAnimation(animation);

        }else if (clickedBlock.x - 1 == blackBlock.x) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.move_up);
            animation.setFillAfter(true);
            cButton.startAnimation(animation);

            animation = AnimationUtils.loadAnimation(this, R.anim.move_down);
            animation.setFillAfter(true);
            animation.setAnimationListener(this);
            bButton.startAnimation(animation);

        }else if (clickedBlock.y + 1 == blackBlock.y) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.move_right);
            animation.setFillAfter(true);
            cButton.startAnimation(animation);

            animation = AnimationUtils.loadAnimation(this, R.anim.move_left);
            animation.setFillAfter(true);
            animation.setAnimationListener(this);
            bButton.startAnimation(animation);

        }else if (clickedBlock.y - 1 == blackBlock.y) {

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.move_left);
            animation.setFillAfter(true);
            cButton.startAnimation(animation);

            animation = AnimationUtils.loadAnimation(this, R.anim.move_right);
            animation.setFillAfter(true);
            animation.setAnimationListener(this);
            bButton.startAnimation(animation);
        }
    }

    private int getPxValue(int itemWidth) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                itemWidth, getResources().getDisplayMetrics());
    }

    private void setUpAnimLayout() {
        if (mAnimLayout == null) {
            mAnimLayout = new RelativeLayout(this);
            LinearLayout.LayoutParams ll = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            addContentView(mAnimLayout, ll);
        }
    }

    private boolean isSuccess() {
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) != i+1) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        buttons[clickedBlock.x][clickedBlock.y].setVisibility(View.INVISIBLE);
        buttons[blackBlock.x][blackBlock.y].setVisibility(View.INVISIBLE);
        isAnimating = true;
    }

    @Override
    public void onAnimationEnd(Animation animation) {

        tv_moveTimes.setText("" + (++moveTimes));
        buttons[blackBlock.x][blackBlock.y]
                .setText(buttons[clickedBlock.x][clickedBlock.y].getText().toString());
        buttons[clickedBlock.x][clickedBlock.y].setText("");

        mAnimLayout.removeAllViews();
        buttons[clickedBlock.x][clickedBlock.y].setVisibility(View.VISIBLE);
        buttons[blackBlock.x][blackBlock.y].setVisibility(View.VISIBLE);
        swap(blackBlock.x*3+blackBlock.y, clickedBlock.x*3+clickedBlock.y);
        blackBlock.x = clickedBlock.x;
        blackBlock.y = clickedBlock.y;

        isAnimating = false;

        if (autoMode) {
            autoRun();
        }
        if (isSuccess()) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("YOU WIN!");
            dialog.setMessage("You solved the puzzle within " + moveTimes + " Steps");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            dialog.show();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
