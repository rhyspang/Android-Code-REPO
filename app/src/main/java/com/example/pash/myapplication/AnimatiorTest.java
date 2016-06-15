package com.example.pash.myapplication;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by PASH on 2016/6/13.
 */
public class AnimatiorTest extends Activity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animator_test);

        button = (Button) findViewById(R.id.bt_move);
    }

    public void clicked(View view) {
        ObjectAnimator.ofFloat(view, "translationX", 0f, 100f).setDuration(1000).start();
    }

}
