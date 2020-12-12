package com.example.warehouseinventoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CardActivity extends AppCompatActivity {

    ArrayList<String> itemList;
    ArrayList<Item> data;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ItemRecyclerAdapter adapter;
    Button btnClearList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        recyclerView = findViewById(R.id.my_recycler_view);
        data = new ArrayList<>();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        btnClearList = findViewById(R.id.btnClearList);
        //receiving data from the MainActivity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        itemList = bundle.getStringArrayList("itemList");
        createItemObjects();
        adapter = new ItemRecyclerAdapter();
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
    }

    private void createItemObjects(){
        String itemName, itemDescription, itemQuantity, itemCost, itemIsFrozen;
        StringTokenizer stringTokenizer;
        for (String item : itemList){
            stringTokenizer = new StringTokenizer(item);
            itemName = stringTokenizer.nextToken(";");
            itemQuantity = stringTokenizer.nextToken(";");
            itemCost = stringTokenizer.nextToken(";");
            itemDescription = stringTokenizer.nextToken(";");
            itemIsFrozen = stringTokenizer.nextToken(";");
            Item newItem = new Item(itemName,itemQuantity,itemCost,itemDescription,itemIsFrozen);
            data.add(newItem);
        }
    }

    public void btnClearListClicked(View v){
        adapter.clearData();
        adapter.notifyDataSetChanged();
    }

}