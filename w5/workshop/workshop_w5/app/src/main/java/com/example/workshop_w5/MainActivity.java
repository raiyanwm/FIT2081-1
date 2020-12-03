package com.example.workshop_w5;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code
        List<String> unitCode= new ArrayList<>();
        unitCode.add("FIT2081");
        unitCode.add("FIT1051");
        unitCode.add("FIT2095");
        ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.unitlist,unitCode);

        ListView unitList = (ListView) findViewById(R.id.unitslist);
        unitList.setAdapter(adapter);

    }
}