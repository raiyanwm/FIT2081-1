package com.example.week6adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Week6Adapter adapter;
    ArrayList<PersonName> PersonName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addName();
            }
        });

        recyclerView = findViewById(R.id.myRecyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Week6Adapter(PersonName);
        recyclerView.setAdapter(adapter);
    }

    public void addName (){
        PersonName personName = new PersonName("david","duong");
        PersonName.add(personName);
        adapter.notifyDataSetChanged();
    }
}