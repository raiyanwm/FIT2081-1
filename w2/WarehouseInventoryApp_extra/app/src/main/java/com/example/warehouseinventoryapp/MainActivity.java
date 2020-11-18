package com.example.warehouseinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText etItemName;
    EditText etQuantity;
    EditText etCost;
    EditText etDescription;
    EditText etSupplier;
    ToggleButton tbFrozen;
    Button btnAdd;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etItemName = findViewById(R.id.etItemName);
        etQuantity = findViewById(R.id.etQuantity);
        etCost = findViewById(R.id.etCost);
        etDescription = findViewById(R.id.etDescription);
        etSupplier = findViewById(R.id.etSupplier);
        tbFrozen = findViewById(R.id.tbFrozen);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);

    }

    public void addNewItem(View v){
        //setting up strings
        String itemName = etItemName.getText().toString();
        String description = etDescription.getText().toString(); //maybe future use?
        String supplier = etSupplier.getText().toString();
        int quantity = Integer.parseInt(etQuantity.getText().toString()); //maybe future use?
        long cost = Long.parseLong(etCost.getText().toString());

        String message = "%s is the supplier for the new item (%s) that has just been added";

        Toast.makeText(this, String.format(message, supplier, itemName),Toast.LENGTH_LONG).show();
    }

    public void clearItem(View v){
        etItemName.setText("");
        etQuantity.setText("");
        etCost.setText("");
        etDescription.setText("");
        tbFrozen.setChecked(false);
        etSupplier.setText("");
    }
}