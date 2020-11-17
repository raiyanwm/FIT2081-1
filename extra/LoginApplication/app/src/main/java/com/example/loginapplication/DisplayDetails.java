package com.example.loginapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayDetails extends AppCompatActivity {

    String username, password; // these we need to get from previous activity
    String user_state;
    int user_age;
    Boolean has_ios, has_android;

    TextView txtv_username, txtv_password, txtv_state, txtv_age, txtv_mobiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the layout for current activity
        setContentView(R.layout.activity_display_details);

        // add reference to view UI elements
        txtv_username = findViewById(R.id.txtv_username);
        txtv_password = findViewById(R.id.txtv_password);
        txtv_state = findViewById(R.id.txtv_state);
        txtv_age = findViewById(R.id.txtv_age);
        txtv_mobiles = findViewById(R.id.txtv_mobiles);


        // start: get data from previous activity
        // get the intent
        Intent details_intent = getIntent();

        // get bundle object having our login data
        Bundle bundle = details_intent.getExtras();

        username = bundle.getString("username");
        password = bundle.getString("password");
        user_state = bundle.getString("state");

        // get age in integer variable
        user_age = bundle.getInt("age");

        // get mobile devices flags
        has_ios = bundle.getBoolean("has_ios");
        has_android = bundle.getBoolean("has_android");
        // end: get data from previous activity

        // set values for respective UI elements
        txtv_username.setText(username);
        txtv_password.setText(password);
        txtv_state.setText(user_state);
        txtv_age.setText(user_age + "");

        // set the android devices
        if (has_ios && has_android) {
            // has both
            txtv_mobiles.setText("Apple iPhone and Android Device");
        } else {

            // has only ios
            if (has_ios) {
                txtv_mobiles.setText("Apple iPhone");
            }

            // has only android
            if (has_android) {
                txtv_mobiles.setText("Android Device");
            }
        }
    }
}
