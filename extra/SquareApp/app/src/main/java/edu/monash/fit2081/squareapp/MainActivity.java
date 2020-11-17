package edu.monash.fit2081.squareapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateSquare(View view){
        // Get a reference to the input edit text (i.e. number)
        EditText number=findViewById(R.id.number);
        //Get a reference to the output text view (i.e. result)
        TextView result=findViewById(R.id.result);
        //Integer.parseInt is a function that converts a string into an integer value
        int theNumber=Integer.parseInt(number.getText().toString());
        int theSquare=theNumber*theNumber;
        //Integer.toString() is a function that convert an integer value into a string
        result.setText(Integer.toString(theSquare));
    }
}
