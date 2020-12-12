package com.fit2081.rooms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fit2081.rooms.provider.Customer;
import com.fit2081.rooms.provider.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    String[] names = {"John", "Ellen", "Michael", "Joe"};
    String[] addresses = {"Melbourne", "Sydney", "Adelaide"};
    TextView tv;


    private CustomerViewModel mCustomerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv_id);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        CustomerAdapter adapter = new CustomerAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCustomerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        mCustomerViewModel.getAllCustomers().observe(this, newData -> {
            adapter.setCustomers(newData);
            adapter.notifyDataSetChanged();
            tv.setText(newData.size() + "");
        });
    }


    public void onAddClick(View v) {
        Random rand = new Random();
        Customer c = new Customer(names[rand.nextInt(names.length)], addresses[rand.nextInt(addresses.length)]);
        mCustomerViewModel.insert(c);

    }

    public void onCountClick(View v) {
        mCustomerViewModel.deleteAll();

    }
}
