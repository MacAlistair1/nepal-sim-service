package com.mis.community.nepalsimservices;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalculatorActivity extends Activity {


    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private char CURRENT_ACTION;

    private double valueOne;
    private double valueTwo;
    private double finalVal =0;

    Button zero, one, two, three, four, five, six, seven, eight, nine, dot, plus, minus, divide, multi, equal, clear;
    ImageButton backBtn;
    EditText editText;
    TextView infoTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        zero = findViewById(R.id.buttonZero);
        one = findViewById(R.id.buttonOne);
        two = findViewById(R.id.buttonTwo);
        three = findViewById(R.id.buttonThree);
        four = findViewById(R.id.buttonFour);
        five = findViewById(R.id.buttonFive);
        six = findViewById(R.id.buttonSix);
        seven = findViewById(R.id.buttonSeven);
        eight = findViewById(R.id.buttonEight);
        nine = findViewById(R.id.buttonNine);
        dot = findViewById(R.id.buttonDot);
        plus = findViewById(R.id.buttonAdd);
        minus = findViewById(R.id.buttonSubtract);
        divide = findViewById(R.id.buttonDivide);
        multi = findViewById(R.id.buttonMultiply);
        equal = findViewById(R.id.buttonEqual);
        clear = findViewById(R.id.buttonClear);
        editText = findViewById(R.id.editText);
        infoTextView = findViewById(R.id.infoTextView);
        backBtn = findViewById(R.id.backBtn);

        final String input = editText.getText().toString();
        if (input.equals("") || input.isEmpty()){
            notEnable();
        }

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append("0");
                enable();
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append("1");
                enable();
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append("2");
                enable();
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append("3");
                enable();
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append("4");
                enable();
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append("5");
                enable();
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append("6");
                enable();
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append("7");
                enable();
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append("8");
                enable();
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append("9");
                enable();
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String val = editText.getText().toString();
                if (!val.contains(".")){
                    editText.append(".");
                }else{

                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CURRENT_ACTION = ADDITION;
                String val = infoTextView.getText().toString();
                if (!val.contains("+")){
                    valueOne = Double.parseDouble(editText.getText().toString());
                    infoTextView.setText(valueOne + "+" );
                }else{

                }
                editText.setText(null);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_ACTION = SUBTRACTION;
                String val = infoTextView.getText().toString();
                if (!val.contains("-")){
                    valueOne = Double.parseDouble(editText.getText().toString());
                    infoTextView.setText(valueOne + "-" );
                }else{

                }
                    editText.setText(null);
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_ACTION = DIVISION;
                String val = infoTextView.getText().toString();
                if (!val.contains("/")){
                    valueOne = Double.parseDouble(editText.getText().toString());
                    infoTextView.setText(valueOne + "/" );
                }else{

                }
                editText.setText(null);
            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_ACTION = MULTIPLICATION;
                String val = infoTextView.getText().toString();
                if (!val.contains("*")){
                    valueOne = Double.parseDouble(editText.getText().toString());
                    infoTextView.setText(valueOne + "*" );
                }else{

                }
                editText.setText(null);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(null);
                infoTextView.setText(null);
                notEnable();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() > 0){
                    StringBuilder builder = new StringBuilder(editText.getText());
                    editText.setText(builder.subSequence(0, builder.length() -1));
                }else if (editText.getText().length() == 0 || input.isEmpty() || input.equals("")){
                    notEnable();
                    valueOne = Double.NaN;
                    valueTwo = Double.NaN;
                    editText.setText(null);
                    infoTextView.setText(null);

                }
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() > 0){
                    valueTwo = Double.parseDouble(editText.getText().toString());
                }else {
                    valueTwo = 0;
                }
                switch (CURRENT_ACTION) {
                    case ADDITION:
                        finalVal = valueOne + valueTwo;
                        infoTextView.setText(valueOne + "+"+ valueTwo );
                        editText.setText("="+finalVal);
                        break;
                    case SUBTRACTION:
                        finalVal = valueOne - valueTwo;
                        infoTextView.setText(valueOne + "-"+ valueTwo );
                        editText.setText("="+finalVal);
                        break;
                    case DIVISION:
                        finalVal = valueOne / valueTwo;
                        infoTextView.setText(valueOne + "/"+ valueTwo );
                        editText.setText("="+finalVal);
                        break;
                    case MULTIPLICATION:
                        finalVal = valueOne * valueTwo;
                        infoTextView.setText(valueOne + "*"+ valueTwo );
                        editText.setText("="+finalVal);
                        break;

                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        editText.setText(String.valueOf(finalVal));
                        infoTextView.setText("");
                        CURRENT_ACTION = '0';
                    }
                },1000);
            }
        });

    }
    public void enable(){
        plus.setEnabled(true);
        minus.setEnabled(true);
        divide.setEnabled(true);
        multi.setEnabled(true);
        clear.setEnabled(true);
        equal.setEnabled(true);
        dot.setEnabled(true);
        backBtn.setVisibility(View.VISIBLE);
    }
    public void notEnable(){
        plus.setEnabled(false);
        minus.setEnabled(false);
        divide.setEnabled(false);
        multi.setEnabled(false);
        clear.setEnabled(false);
        equal.setEnabled(false);
        dot.setEnabled(false);
        backBtn.setVisibility(View.INVISIBLE);
    }
}
