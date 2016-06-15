package com.example.pash.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by PASH on 2016/6/11.
 */
public class GameEightFigure extends Activity implements View.OnClickListener, Animation.AnimationListener{
    private Button bt_loc1;
    private Button bt_loc2;
    private Button bt_loc3;
    private Button bt_loc4;
    private Button bt_loc5;
    private Button bt_loc6;
    private Button bt_loc7;
    private Button bt_loc8;
    private Button bt_loc9;
    private TextView tvShow;

    private int emptyX = 2;
    private int emptyY = 2;
    private Button[][] buttons;
    private int clickedButtonNum;
    private int moveTimes = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eight_figure_puzzle);

        bt_loc9 = (Button) findViewById(R.id.bt_loc9);
        bt_loc1 = (Button) findViewById(R.id.bt_loc1);
        bt_loc2 = (Button) findViewById(R.id.bt_loc2);
        bt_loc3 = (Button) findViewById(R.id.bt_loc3);
        bt_loc4 = (Button) findViewById(R.id.bt_loc4);
        bt_loc5 = (Button) findViewById(R.id.bt_loc5);
        bt_loc6 = (Button) findViewById(R.id.bt_loc6);
        bt_loc7 = (Button) findViewById(R.id.bt_loc7);
        bt_loc8 = (Button) findViewById(R.id.bt_loc8);
        tvShow = (TextView) findViewById(R.id.show);

        buttons = new Button[][] {
                {bt_loc1, bt_loc2, bt_loc3},
                {bt_loc4, bt_loc5, bt_loc6},
                {bt_loc7, bt_loc8, bt_loc9}
        };

        bt_loc9.setOnClickListener(this);
        bt_loc1.setOnClickListener(this);
        bt_loc2.setOnClickListener(this);
        bt_loc3.setOnClickListener(this);
        bt_loc4.setOnClickListener(this);
        bt_loc5.setOnClickListener(this);
        bt_loc6.setOnClickListener(this);
        bt_loc7.setOnClickListener(this);
        bt_loc8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_loc1:
                clickedButtonNum = 1;
                if (canMove(1)) {
                    moveAction(1);
                }
                break;

            case R.id.bt_loc2:
                clickedButtonNum = 2;
                if (canMove(2)) {
                    moveAction(2);
                }
                break;
            case R.id.bt_loc3:
                clickedButtonNum = 3;
                if (canMove(3)) {
                    moveAction(3);
                }
                break;
            case R.id.bt_loc4:
                clickedButtonNum = 4;
                if (canMove(4)) {
                    moveAction(4);
                }
                break;
            case R.id.bt_loc5:
                clickedButtonNum = 5;
                if (canMove(5)) {
                    moveAction(5);
                }
                break;
            case R.id.bt_loc6:
                clickedButtonNum = 6;
                if (canMove(6)) {
                    moveAction(6);
                }
                break;
            case R.id.bt_loc7:
                clickedButtonNum = 7;
                if (canMove(7)) {
                    moveAction(7);
                }
                break;
            case R.id.bt_loc8:
                clickedButtonNum = 8;
                if (canMove(8)) {
                    moveAction(8);
                }
                break;
            case R.id.bt_loc9:
                clickedButtonNum = 9;
                if (canMove(9)) {
                    moveAction(9);
                }
                break;

            default:
                break;


        }
    }
    private boolean canMove(int num) {
        int posX = (num-1) / 3;
        int posY = (num-1) % 3;
        if (Math.abs(posX - emptyX) == 1 && posY == emptyY ||
                Math.abs(posY - emptyY) == 1 && posX == emptyX) {
            return true;
        }
        return false;
    }

    private void moveAction(int num) {
        int posX = (num-1) / 3;
        int posY = (num-1) % 3;
        Animation loadAnimation;
        if (posX + 1 == emptyX) {
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.move_down);
            buttons[posX][posY].startAnimation(loadAnimation);
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.move_up);
            buttons[emptyX][emptyY].startAnimation(loadAnimation);
            loadAnimation.setAnimationListener(this);
        }else if (posX - 1 == emptyX) {
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.move_up);
            buttons[posX][posY].startAnimation(loadAnimation);
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.move_down);
            buttons[emptyX][emptyY].startAnimation(loadAnimation);
            loadAnimation.setAnimationListener(this);
        }else if (posY + 1 == emptyY) {
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.move_right);
            buttons[posX][posY].startAnimation(loadAnimation);
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.move_left);
            buttons[emptyX][emptyY].startAnimation(loadAnimation);
            loadAnimation.setAnimationListener(this);
        }else if (posY - 1 == emptyY) {
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.move_left);
            buttons[posX][posY].startAnimation(loadAnimation);
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.move_right);
            buttons[emptyX][emptyY].startAnimation(loadAnimation);
            loadAnimation.setAnimationListener(this);
        }

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        int posX = (clickedButtonNum - 1) / 3;
        int posY = (clickedButtonNum - 1) % 3;
        String num = buttons[posX][posY].getText().toString();

        buttons[posX][posY].setText("");
        buttons[emptyX][emptyY].setText(num);
        emptyX = posX;
        emptyY = posY;
        moveTimes++;
        tvShow.setText("Move times: " + moveTimes);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
