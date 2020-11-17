package com.example.week3lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etAge;
    Button btnShow;
    int timesClicked;
    SharedPreferences userInfo;

    public void saveInfo(){
        SharedPreferences.Editor edit = userInfo.edit();
        edit.putString("name","Lare");
        edit.putInt("age", 23);
        edit.apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("timesClicked",timesClicked);
        super.onSaveInstanceState(outState);
    }


    public void showName(){
        timesClicked++;
        //Toast.makeText(this,"Hi, " + etName.getText().toString() + " has clicked: " + timesClicked + " times",Toast.LENGTH_LONG).show();
        int age = userInfo.getInt("age",0);
        Toast.makeText(this,"Age: " + age,Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MyLifeCycleMethods","This is onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MyLifeCycleMethods","This is onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MyLifeCycleMethods","This is onDestroy");

    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("MyLifeCycleMethods","This is onPause");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("MyLifeCycleMethods","This is onResume");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("MyLifeCycleMethods","This is onRestart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        timesClicked = savedInstanceState.getInt("timesClicked");
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("MyLifeCycleMethods","This is onRestoreInstance");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInfo = getSharedPreferences("fit2081w3.data",MODE_PRIVATE);

        etName = findViewById(R.id.etName);
        btnShow = findViewById(R.id.btnShow);
        etAge = findViewById(R.id.etAge);


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveInfo();
                showName();
            }
        });
        Log.i("MyLifeCycleMethods","This is onCreate");
    }

}