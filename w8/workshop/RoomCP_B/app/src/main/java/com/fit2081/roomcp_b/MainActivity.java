package com.fit2081.roomcp_b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView tV;
    EditText etID;
    public static final String CONTENT_AUTHORITY = "fit2081.app.KE";
    public static final String STRING_URI  = "content://"+CONTENT_AUTHORITY+"/items";
    public static final Uri CONTENT_URI = Uri.parse(STRING_URI);

    private String[] itemName = {"Book","Pen","Laptop"};
    private int[] itemQuantity = {10,19,2};
    private boolean[] itemIsFrozen = {true,false};
    private double[] itemCost = {2,19.3,2.25};

    private String COLUMN_ITEMNAME = "itemName";
    private String COLUMN_ITEMDESCRIPTION = "itemDescription";
    private String COLUMN_ITEMQUANTITY = "itemQuantity";
    private String COLUMN_ITEMISFROZEN = "itemIsFrozen";
    private String COLUMN_ITEMCOST = "itemCost";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tV=findViewById(R.id.textView_id);

        Cursor result= getContentResolver().query(CONTENT_URI,null,null,null);
        tV.setText(result.getCount()+"");

    }

    public void randomInsert(View v){
        Random rand = new Random();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEMNAME,itemName[rand.nextInt(itemName.length)]);
        values.put(COLUMN_ITEMDESCRIPTION,"");
        values.put(COLUMN_ITEMQUANTITY,itemQuantity[rand.nextInt(itemQuantity.length)]);
        values.put(COLUMN_ITEMISFROZEN,itemIsFrozen[rand.nextInt(itemIsFrozen.length)]);
        values.put(COLUMN_ITEMCOST,itemCost[rand.nextInt(itemCost.length)]);

        Uri uri1 = getContentResolver().insert(CONTENT_URI,values);
        String id = uri1.getLastPathSegment();
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        Cursor result= getContentResolver().query(CONTENT_URI,null,null,null);
        tV.setText(result.getCount()+"");
    }

    public void deleteItem(View v){
        etID = findViewById(R.id.etID);
        String deleteID = etID.getText().toString();
        int uri1 = 0;
        if (deleteID != "") {
            Uri specificUri = Uri.parse(STRING_URI+"/"+deleteID);
            uri1 = getContentResolver().delete(specificUri, deleteID, null);
        }
        else
            uri1 = getContentResolver().delete(CONTENT_URI,null,null);
        Toast.makeText(this, String.valueOf(uri1), Toast.LENGTH_LONG).show();
        Cursor result= getContentResolver().query(CONTENT_URI,null,null,null);
        tV.setText(result.getCount()+"");
    }

    public void doubleQuantity(View v){
        //how to reset the id?
        int numberOfItems = Integer.parseInt(tV.getText().toString());
        //String idList[] = new String[numberOfItems];
        String selectedColumn[] = {"itemId","itemQuantity"};
        Cursor item = getContentResolver().query(CONTENT_URI,selectedColumn,null,null);
        if (item != null) {
            item.moveToFirst();
            String quantity;
            int id;
            for (int i = 0; i < item.getCount(); i++){
                id = item.getInt(item
                        .getColumnIndexOrThrow("itemId"));
                quantity = item.getString(item
                        .getColumnIndexOrThrow("itemQuantity"));

                item.moveToNext();
                updateTable(id,Integer.parseInt(quantity)*2);
                //System.out.println(quantity);
            }
            item.close();
        }
    }

    private void updateTable(int id, int doubledQuantity){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEMQUANTITY,doubledQuantity);
        getContentResolver().update(CONTENT_URI,values,"itemID ="+id,null);
    }
}