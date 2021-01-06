package com.example.warehouseinventoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.warehouseinventoryapp.provider.Item;
import com.example.warehouseinventoryapp.provider.ItemViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    //ArrayList<Item> itemList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ItemRecyclerAdapter adapter;

    private ItemViewModel mItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        recyclerView = findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //receiving data from the MainActivity
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String dbString = bundle.getString("itemList");
//        createItemObjects(dbString);

        adapter = new ItemRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        setUpDatabase();
    }

    /*private void createItemObjects(String dbString){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Item>>(){}.getType();
        itemList = gson.fromJson(dbString,type);
    }*/

    private void setUpDatabase(){
        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        mItemViewModel.getAllItem().observe(this, newData -> {
            adapter.setItem(newData);
            adapter.notifyDataSetChanged();
            Log.d("DEBUG", Integer.toString(adapter.getItemCount()));
        });
    }

}