package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            display.setShowSoftInputOnFocus(false);
        }

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }

            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String liftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
        }
        else {
            display.setText(String.format("%s%s%s",liftStr,strToAdd,rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void bakcspaceBTN(View view){
        int cursorPos = display.getSelectionStart();
        int textlen = display.getText().length();
        if (cursorPos != 0 && textlen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }

    }
    public void clearBTN(View view){
        display.setText("");
    }
    public void parentheseBTN(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0 ;
        int closedPar = 0;
        int textlen = display.getText().length();
        for (int i = 0;i<cursorPos;i++){
            if (display.getText().toString().substring(i,i+1).equals("(")){
                openPar += 1;
            }
            if (display.getText().toString().substring(i,i+1).equals(")")){
                closedPar += 1;
            }
        }
        if (openPar == closedPar || display.getText().toString().substring(textlen-1,textlen).equals("(")){
            updateText("(");
        }
        else if (closedPar < openPar && !display.getText().toString().substring(textlen-1,textlen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos +1);
    }
    public void exponentBTN(View view){
        updateText("E");

    }
    public void divideBTN(View view){
        updateText("/");

    }
    public void sevenBTN(View view){
        updateText("7");
    }
    public void eightBTN(View view){
        updateText("8");
    }
    public void nineBTN(View view){
        updateText("9");
    }
    public void multiplyBTN(View view){
        updateText("X");
    }
    public void fourBTN(View view){
        updateText("4");
    }
    public void fiveBTN(View view){
        updateText("5");
    }
    public void sixBTN(View view){
        updateText("6");
    }
    public void subtractionBTN(View view){
        updateText("-");
    }
    public void oneBTN(View view){
        updateText("1");
    }
    public void twoBTN(View view){
        updateText("2");
    }
    public void threeBTN(View view){
        updateText("3");
    }
    public void addBTN(View view){
        updateText("+");
    }
    public void plusMinusBTN(View view){
        updateText("-");
    }
    public void zeroBTN(View view){
        updateText("0");
    }
    public void pointBTN(View view){
        updateText(".");
    }
    public void equalsBTN(View view){
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("X","*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());

    }


}