package com.example.pash.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by PASH on 2016/6/10.
 */
public class RadioButtonTest extends Activity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_radio_button);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.male:
                Toast.makeText(this, "性别选择:男", Toast.LENGTH_SHORT).show();
                break;
            case R.id.female:
                Toast.makeText(this, "性别选择:女", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
