package com.example.warehouseinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LIFE_CYCLE_TRACING";
    private static final String FILE_NAME = "ITEM_DETAILS";

    //components
    EditText etItemName;
    EditText etQuantity;
    EditText etCost;
    EditText etDescription;
    ToggleButton tbFrozen;
    Button btnAdd;
    Button btnClear;
    SharedPreferences itemDetail;

    //variables
    private String itemName,itemDescription;
    private int itemQuantity;
    private boolean itemIsFrozen;
    private float itemCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemDetail = getSharedPreferences(FILE_NAME, MODE_PRIVATE);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));

        etItemName = findViewById(R.id.etItemName);
        etQuantity = findViewById(R.id.etQuantity);
        etCost = findViewById(R.id.etCost);
        etDescription = findViewById(R.id.etDescription);
        tbFrozen = findViewById(R.id.tbFrozen);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);

        restorePreviousItem();

        Log.i(TAG,"onCreate");
    }

    private void restorePreviousItem(){
        etItemName.setText(itemDetail.getString("itemName",""));
        etQuantity.setText(Integer.toString(itemDetail.getInt("itemQuantity",0)));
        etCost.setText(Float.toString(itemDetail.getFloat("itemCost",0)));
        etDescription.setText(itemDetail.getString("itemDescription",""));
        tbFrozen.setChecked(itemDetail.getBoolean("itemIsFrozen",false));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSave");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"onRestore");
    }

    public void addNewItem(View v){
        itemName = etItemName.getText().toString();
        itemQuantity = Integer.parseInt(etQuantity.getText().toString());
        itemCost = Float.parseFloat(etCost.getText().toString());
        itemDescription  = etDescription.getText().toString();
        itemIsFrozen = tbFrozen.isChecked();

        saveData();
        String message = "New item (%s) has been added";

        Toast.makeText(this, String.format(message, itemName),Toast.LENGTH_LONG).show();
    }

    private void saveData(){
        SharedPreferences.Editor editor =  itemDetail.edit();
        editor.putString("itemName",itemName);
        editor.putInt("itemQuantity",itemQuantity);
        editor.putFloat("itemCost",itemCost);
        editor.putString("itemDescription",itemDescription);
        editor.putBoolean("itemIsFrozen",itemIsFrozen);
        editor.apply();
    }

    public void clearItem(View v){
        resetField();
        clearSavedData();
    }

    private void resetField(){
        etItemName.setText("");
        etQuantity.setText("0");
        etCost.setText("0.0");
        etDescription.setText("");
        tbFrozen.setChecked(false);
    }

    private void clearSavedData(){
        itemName = "";
        itemQuantity = 0;
        itemCost = 0;
        itemDescription = "";
        itemIsFrozen = false;

        SharedPreferences.Editor editor =  itemDetail.edit();
        editor.clear();
        editor.apply();
    }

    public void addItemThroughSMS(String msg){
        StringTokenizer itemInfo = new StringTokenizer(msg,";");

        String itemName = itemInfo.nextToken();
        String itemQuantity = itemInfo.nextToken();
        String itemCost = String.valueOf(Double.parseDouble(itemInfo.nextToken()));
        String itemDescription = itemInfo.nextToken();
        Boolean itemIsFrozen = Boolean.parseBoolean(itemInfo.nextToken());

        etItemName.setText(itemName);
        etQuantity.setText(itemQuantity);
        etCost.setText(itemCost);
        etDescription.setText(itemDescription);
        tbFrozen.setChecked(itemIsFrozen);

    }

    class MyBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);
            addItemThroughSMS(msg);
        }
    }
}