package com.fit2081.scrollableoptionsmenus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public void clearButtonHandler(View v){
        ((EditText)findViewById(R.id.text_id)).setText("");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id == R.id.action_Logout) {
            Toast.makeText(this,"Logout...",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_settings) {
            Toast.makeText(this,"Settings...",Toast.LENGTH_SHORT).show();
       }
        return true;
    }
}
