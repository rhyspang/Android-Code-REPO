package com.example.pash.myapplication;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by PASH on 2016/6/11.
 */
public class AnimationTest extends Activity implements View.OnClickListener{
    private Button scaleAnimation;
    private Button translateAnimation;
    private Button btn_game;
    private ImageView image;
    private ImageView image2;
    private Button bt_animator_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_animationmenu);

        scaleAnimation = (Button) findViewById(R.id.scale);
        scaleAnimation.setOnClickListener(this);

        translateAnimation = (Button) findViewById(R.id.translate);
        translateAnimation.setOnClickListener(this);

        btn_game = (Button) findViewById(R.id.btn_game);
        btn_game.setOnClickListener(this);

        image = (ImageView) findViewById(R.id.image);
        image2 = (ImageView) findViewById(R.id.image2);

        bt_animator_test = (Button) findViewById(R.id.btn_animator_test);
        bt_animator_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Animation loadAnimation;
        switch (v.getId()) {
            case R.id.scale:
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
                image.startAnimation(loadAnimation);
                break;
            case R.id.translate:
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
                loadAnimation.setFillAfter(true);
                image.startAnimation(loadAnimation);
                image2.startAnimation(loadAnimation);
                translateAnimation.startAnimation(loadAnimation);
                break;
            case R.id.btn_game:
                startActivity(new Intent(this, EightFigure.class));
                break;
            case R.id.btn_animator_test:
                startActivity(new Intent(this, AnimatiorTest.class));
                break;
            default:
                break;
        }
    }
}
