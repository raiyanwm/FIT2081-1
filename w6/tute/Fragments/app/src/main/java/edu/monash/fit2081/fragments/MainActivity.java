package edu.monash.fit2081.fragments;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleBtn1(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.frag1,new Fragment1()).addToBackStack("f1").commit();

    }
    public void handleBtn2(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.frag1,new Fragment2()).addToBackStack("f2").commit();

    }
    public void handleBtn3(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.frag1,new Fragment3()).addToBackStack("f3").commit();

    }
}
