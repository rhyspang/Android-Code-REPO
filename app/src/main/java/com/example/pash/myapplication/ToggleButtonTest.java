package com.example.pash.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

/**
 * Created by PASH on 2016/6/10.
 */
public class ToggleButtonTest extends Activity implements CompoundButton.OnCheckedChangeListener{
    private ToggleButton toggleButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_toggle_button);
        toggleButton = (ToggleButton) findViewById(R.id.toggle_button);
        imageView = (ImageView)findViewById(R.id.imageview);
        toggleButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.toggle_button:

                break;
            default:
                break;
        }
    }
}
