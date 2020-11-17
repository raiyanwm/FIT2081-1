package com.example.fit2081_tute1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etLength;
    EditText etWidth;
    EditText etHeight;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etName = findViewById(R.id.etName);
        etLength = findViewById(R.id.etLength);
        etWidth = findViewById(R.id.etWidth);
        etHeight = findViewById(R.id.etHeight);
        btnCalculate = findViewById(R.id.btnCalculate);

    }

    public void calculateVolume(View v) {
        int length = Integer.parseInt(etLength.getText().toString());
        int width = Integer.parseInt(etWidth.getText().toString());
        int height = Integer.parseInt(etHeight.getText().toString());
        String name = etName.getText().toString();
        int volume = length*width*height;

        Toast.makeText(this,"The " + name + "'s volume is " + volume + " cm^3", Toast.LENGTH_LONG).show();
        etName.setText("");
        etLength.setText("");
        etWidth.setText("");
        etHeight.setText("");

    }

}