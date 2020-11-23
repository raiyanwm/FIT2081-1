package edu.monash.fit2081.calculatorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    private double valueOne = Double.NaN;
    private double valueTwo;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char NO_OPERATION = '?';

    private char CURRENT_ACTION;
    private DecimalFormat decimalFormat;
    public TextView interScreen;  // Intermediate result Screen
    private TextView resultScreen; // Result Screen


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Reference both TextViews
        interScreen =  findViewById(R.id.InterScreen);
        resultScreen =  findViewById(R.id.resultScreen);
        decimalFormat = new DecimalFormat("#.##########");
    }



    public void buttonSevenClick(View v) {
        interScreen.setText(interScreen.getText() + "7");
    }

    public void buttonEightClick(View v) {
        interScreen.setText(interScreen.getText() + "8");
    }

    public void buttonNineClick(View v) {
        interScreen.setText(interScreen.getText() + "9");
    }

    public void buttonDivisionClick(View v) {
        computeCalculation();
        if (Double.isNaN(valueOne)) {
            showToast("Invalid Key");
        } else {
            CURRENT_ACTION = DIVISION;
            resultScreen.setText(decimalFormat.format(valueOne) + "/");
            interScreen.setText("");
        }
    }

    public void buttonEqualClick(View v) {

        /*
        * Call ComputeCalculation method
        * Update the result TextView by adding the '=' char and result of operation
        * Reset valueOne
        * Set CURRENT_ACTION to NO_OPERATION
        * */
    }

    public void buttonClearClick(View v) {
        /*
        * if the intermediate TextView has text then
        *       delete the last character
        * else
              * reset valueOne, valueTwo, the content of result TextView,
              * and the content of intermediate TextView
        * */
    }


    private void computeCalculation() {

        if (!Double.isNaN(valueOne)) {
            String valueTwoString = interScreen.getText().toString();
            if (!valueTwoString.equals("")) {
                valueTwo = Double.parseDouble(valueTwoString);
                interScreen.setText(null);
                if (CURRENT_ACTION == ADDITION)
                    valueOne = this.valueOne + valueTwo;
                else if (CURRENT_ACTION == SUBTRACTION)
                    valueOne = this.valueOne - valueTwo;
                else if (CURRENT_ACTION == MULTIPLICATION)
                    valueOne = this.valueOne * valueTwo;
                else if (CURRENT_ACTION == DIVISION)
                    valueOne = this.valueOne / valueTwo;
            }
        } else {
            try {
                valueOne = Double.parseDouble(interScreen.getText().toString());
            } catch (Exception e) {
            }

        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
