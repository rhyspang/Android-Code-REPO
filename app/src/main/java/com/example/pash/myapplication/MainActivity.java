package com.example.pash.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button startRadioGroup;
    private Button startToggleButton;
    private Button startMarqueeText;

    private Button startFrameLayout;
    private Button startTableLayout;

    private Button startListView;

    private Button startAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startRadioGroup = (Button) findViewById(R.id.start_layout_radio_button);
        startRadioGroup.setOnClickListener(this);

        startToggleButton = (Button) findViewById(R.id.start_toggle_button);
        startToggleButton.setOnClickListener(this);

        startMarqueeText = (Button) findViewById(R.id.start_marquee_text);
        startMarqueeText.setOnClickListener(this);

        startFrameLayout = (Button) findViewById(R.id.frameLayout_case);
        startFrameLayout.setOnClickListener(this);

        startTableLayout = (Button) findViewById(R.id.start_table_layout);
        startTableLayout.setOnClickListener(this);

        startListView = (Button) findViewById(R.id.start_listview);
        startListView.setOnClickListener(this);

        startAnimation = (Button) findViewById(R.id.start_animation);
        startAnimation.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_layout_radio_button:
                Intent intent = new Intent(this, RadioButtonTest.class);
                startActivity(intent);
                break;
            case R.id.start_toggle_button:
                startActivity(new Intent(this, ToggleButtonTest.class));
                break;
            case R.id.start_marquee_text:
                startActivity(new Intent(this, MarqueeTest.class));
                break;
            case R.id.frameLayout_case:
                startActivity(new Intent(this, FrameLayoutCaseTest.class));
               /* overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);*/
                break;
            case R.id.start_table_layout:
                startActivity(new Intent(this, TableLayoutTest.class));
                break;
            case R.id.start_listview:
                startActivity(new Intent(this, ListViewTest.class));
                break;
            case R.id.start_animation:
                startActivity(new Intent(this, AnimationTest.class));

                break;
            default:
                break;
        }
    }
}
