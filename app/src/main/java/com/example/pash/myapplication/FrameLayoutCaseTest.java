package com.example.pash.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.AccessControlContext;

/**
 * Created by PASH on 2016/6/10.
 */
public class FrameLayoutCaseTest extends Activity implements View.OnClickListener{
    private Button case1;
    private Button case2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayout_case);

        case1 = (Button) findViewById(R.id.case1);
        case2 = (Button) findViewById(R.id.case2);
        case1.setOnClickListener(this);
        case2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.case1:
                startActivity(new Intent(this, FrameLayoutCase1.class));
                break;
            case R.id.case2:
                startActivity(new Intent(this, FrameLayoutCase2.class));
                break;

            default:
                break;
        }
    }
}
