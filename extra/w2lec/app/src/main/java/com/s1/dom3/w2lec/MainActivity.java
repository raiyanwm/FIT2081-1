package com.s1.dom3.w2lec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public  static final String STR_KEY="key4str";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void handleToLowerBtn(View view){
        Intent intent=new Intent(this,Main2Activity.class);
        EditText editText=findViewById(R.id.strIn);
        intent.putExtra(MainActivity.STR_KEY,editText.getText().toString());
        startActivity(intent);
    }

    public void handleToUpperBtn(View view){
        Intent intent=new Intent(this,UprStr.class);
        EditText editText=findViewById(R.id.strIn);
        intent.putExtra(MainActivity.STR_KEY,editText.getText().toString());
        startActivity(intent);


    }

}
