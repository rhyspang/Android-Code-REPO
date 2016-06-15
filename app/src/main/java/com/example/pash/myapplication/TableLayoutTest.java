package com.example.pash.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by PASH on 2016/6/10.
 */
public class TableLayoutTest extends Activity implements View.OnClickListener{
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_point;
    private Button btn_clear;
    private Button btn_del;
    private Button btn_plus;
    private Button btn_minus;
    private Button btn_multiply;
    private Button btn_divide;
    private Button btn_equal;

    private EditText et_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_equal = (Button) findViewById(R.id.btn_equal);

        et_input = (EditText) findViewById(R.id.show);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                et_input.setText(str + ((Button)v).getText());
                break;

            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                et_input.setText(str + " " + ((Button)v).getText() + " ");
                break;

            case R.id.btn_del:
                if (str != null && !str.equals("")) {
                    et_input.setText(str.substring(0, str.length()-1));
                }
                break;

            case R.id.btn_clear:
                et_input.setText("");
                break;
            case R.id.btn_equal:
                getResult();
                break;

            default:
                break;

        }
    }

    private void getResult() {
        String exp = et_input.getText().toString();
        if (exp == null || exp.equals("") ) {
            return;
        }
        if (!exp.contains(" ")) {
            return;
        }

        String s1 = exp.substring(0, exp.indexOf(" "));
        String op = exp.substring(exp.indexOf(" ")+1, exp.indexOf(" ")+2);
        String s2 = exp.substring(exp.indexOf(" ")+3);

        double d1 = 0, d2 = 0;
        try {
            d1 = Double.parseDouble(s1);
            d2 = Double.parseDouble(s2);
        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "This calulator can only calculate dyadic operation.Please input valid exp",
                    Toast.LENGTH_LONG).show();
        }

        if (!s1.equals("") && !s2.equals("")) {
            if (op.equals("+")) {
                et_input.setText("" + (d1+d2));
            }else if (op.equals("-")){
                et_input.setText("" + (d1-d2));
            }else if (op.equals("*")) {
                et_input.setText("" + (d1*d2));
            }else if (op.equals("/")) {
                et_input.setText("" + (d1/d2));
            }
        }
    }
}
