package com.s1.dom3.w2lec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UprStr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upr_str);
        Intent intent=getIntent();
        String myStr=intent.getStringExtra(MainActivity.STR_KEY);
        myStr=myStr.toUpperCase();

        TextView textView=findViewById(R.id.strInUpr);
        textView.setText(myStr);
    }
}
