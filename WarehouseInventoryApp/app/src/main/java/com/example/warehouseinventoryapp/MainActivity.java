package com.example.warehouseinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.example.warehouseinventoryapp.provider.Item;
import com.example.warehouseinventoryapp.provider.ItemViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LIFE_CYCLE_TRACING";
    private static final String FILE_NAME = "ITEM_DETAILS";
    private static final  String DEBUG = "DEBUG";

    //components
    EditText etItemName;
    EditText etQuantity;
    EditText etCost;
    EditText etDescription;
    ToggleButton tbFrozen;
    Button btnAdd;
    Button btnClear;
    SharedPreferences itemDetail;

    MyBroadCastReceiver myBroadCastReceiver;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ListView listView;
    ArrayList<String> dataSource;
    //ArrayList<Item> itemList;
    ArrayAdapter<String> arrayAdapter;
    FloatingActionButton fab;

    //variables
    private String itemName,itemDescription;
    private int itemQuantity;
    private boolean itemIsFrozen;
    private float itemCost;

    //w7
    private ItemViewModel mItemViewModel;

    //w10
    boolean hasMoved = false;
    float downX = 0,upX =0, downY = 0, upY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        setUpSharePreference();
        createInstance();
        restorePreviousItem();
        setUpDatabase();

        //week 10
        View constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return touchHandler(event);
            }
        });
        View linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return touchHandler(event);
            }
        });
        Log.i(TAG,"onCreate");
    }

    private void setUpSharePreference(){
        itemDetail = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        myBroadCastReceiver = new MyBroadCastReceiver();
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));
    }

    private void createInstance(){
        /* Fields & Buttons -- W5<*/
        etItemName = findViewById(R.id.etItemName);
        etQuantity = findViewById(R.id.etQuantity);
        etCost = findViewById(R.id.etCost);
        etDescription = findViewById(R.id.etDescription);
        tbFrozen = findViewById(R.id.tbFrozen);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        /*W5*/
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        listView = findViewById(R.id.listItem);
        dataSource = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dataSource);
        fab = findViewById(R.id.fab);

        listView.setAdapter(arrayAdapter);
        setSupportActionBar(toolbar);//for backward compatibility
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav_view,R.string.close_nav_view);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new navListener());

        /*W6*/
        //itemList = new ArrayList<>();

    }

    private void restorePreviousItem(){
        etItemName.setText(itemDetail.getString("itemName",""));
        etQuantity.setText(Integer.toString(itemDetail.getInt("itemQuantity",0)));
        etCost.setText(Float.toString(itemDetail.getFloat("itemCost",0)));
        etDescription.setText(itemDetail.getString("itemDescription",""));
        tbFrozen.setChecked(itemDetail.getBoolean("itemIsFrozen",false));
    }

    /*OptionMenu section */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionsmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int optMenuId = item.getItemId();
        switch (optMenuId){
            case R.id.optSave:
                addNewItem();
                break;
            case R.id.optClear:
                clearSavedData();
                break;
            case R.id.optAddList:
                addItemToList();
                break;
            case R.id.optClearList:
                clearList();
                break;
        }
        return true;
    }

    /* Navigation Section */
    class navListener implements NavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int navMenuId = item.getItemId();
            switch (navMenuId){
                case R.id.navSave:
                    addNewItem();
                    break;
                case R.id.navClearField:
                    resetField();
                    break;
                case R.id.navAddList:
                    addItemToList();
                    break;
                case R.id.navClearList:
                    clearList();
                    break;
                case R.id.navListAll:
                    //listAll();
                    listAllItemFromDatabase();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    }

    /*Adding and Removing Items from List*/
    private void addItemToList(){
        itemName = etItemName.getText().toString();
        dataSource.add(itemName);
        arrayAdapter.notifyDataSetChanged();
    }

    private void clearList(){
        dataSource.clear();
        arrayAdapter.notifyDataSetChanged();
    }

    /*Life Cycle */
    @Override
    protected void onDestroy() {
        unregisterReceiver(myBroadCastReceiver);
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

    /* State working section */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("dataSource",dataSource);
        Log.i(TAG,"onSave");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList <String> passedData = savedInstanceState.getStringArrayList("dataSource");
        for (String element : passedData)
            dataSource.add(element);
        arrayAdapter.notifyDataSetChanged();
        Log.i(TAG,"onRestore");
    }

    /* Data handling */

    public void addNewItem(View v){
        addNewItem();
        //saveItemToItemList();
        saveItemToDatabase();
    }

    private void addNewItem(){
        getItemInfo();
        saveData();
        notifySucessfulAdd();
    }

    private void getItemInfo(){
        itemName = etItemName.getText().toString();
        itemQuantity = Integer.parseInt(etQuantity.getText().toString());
        itemCost = Float.parseFloat(etCost.getText().toString());
        itemDescription  = etDescription.getText().toString();
        itemIsFrozen = tbFrozen.isChecked();
    }

    private void notifySucessfulAdd(){
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

        //week 6
        //itemList.clear();
        //week 7
        deleteAllItem();
    }

    /* SMS section */
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

    /*Week 6*/
    /* List all added items*/
//    private void listAll(){
//        Intent intent = new Intent(this,CardActivity.class);
//        Gson gson = new Gson();
//        String dbStr = gson.toJson(itemList);
//        intent.putExtra("itemList",dbStr);
//        startActivity(intent);
//    }
//    private void saveItemToItemList(){
//        Item newItem = new Item(itemName,itemQuantity,itemCost,itemDescription,itemIsFrozen);
//        itemList.add(newItem);
//    }

    /* Week 7 */
    private void setUpDatabase(){
        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        ItemRecyclerAdapter adapter = new ItemRecyclerAdapter();
        mItemViewModel.getAllItem().observe(this, newData -> {
            adapter.setItem(newData);
            adapter.notifyDataSetChanged();
            Log.d(DEBUG, Integer.toString(adapter.getItemCount()));
        });
    }

    private  void saveItemToDatabase(){
        Item newItem = new Item(itemName,itemQuantity,itemCost,itemDescription,itemIsFrozen);
        mItemViewModel.insert(newItem);
    }

    private void deleteAllItem(){
        mItemViewModel.deleteAll();
    }

    private void listAllItemFromDatabase(){
        Intent intent = new Intent(this,CardActivity.class);
        startActivity(intent);
    }

    //week 10
    private boolean touchHandler(MotionEvent event){
        int action =event.getActionMasked();
        if (action == MotionEvent.ACTION_DOWN){
            downX = event.getX();
            downY = event.getY();
            Log.d("Gesture", "Down");
        }
        else if (action == MotionEvent.ACTION_MOVE && !hasMoved) {
            hasMoved = true;
            Log.d("Gesture", "Move");
        }
        else if (action == MotionEvent.ACTION_UP && hasMoved){
            upX = event.getX();
            upY = event.getY();
            checkSwipe();
            Log.d("Gesture", "Up");
            hasMoved = false;
        }
        return true;
    }

    private void checkSwipe(){
        float horizontalDisplacement = upX - downX;
        float verticalDisplacement = upY - downY;
        if (horizontalDisplacement < -100){
            //left
            resetField();
            Log.d("Gesture", "Left");
            Log.d("Gesture", "clear field");
        }else if (horizontalDisplacement > 100){
            //right
            addNewItem();
            saveItemToDatabase();
            Log.d("Gesture", "Right");
            Log.d("Gesture", "Save Item");
        }
        else if (verticalDisplacement < -100){
            //up
            listAllItemFromDatabase();
            Log.d("Gesture", "Move Up");
            Log.d("Gesture", "List items");
        }
    }
}