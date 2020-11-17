package com.example.loginapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // define button variable to access throughout this class
    Button btn_login;

    // define two edit text fields one for username and one for password
    EditText edt_username, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this line of code sets the layout for our activity
        setContentView(R.layout.activity_main);

        // get reference to your components from the view
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        // this is a test statement

        // similarly for the button
//        btn_login = findViewById(R.id.btn_login);

        // set this class as click event listener for btn_login
     //   btn_login.setOnClickListener(this);
    }

    public void handleLoginBtn(View v) {
        // just in case if we have multiple buttons on one screen
        // we can filter out using the if else or switch case
        if (v.getId() == R.id.btn_login) {
            // define strings to get username and password
            String str_username = "", str_password = "";

            // get value of user name and password
            str_username = edt_username.getText().toString();
            str_password = edt_password.getText().toString();

            // validate username & password and show toast if empty
            if (str_username.isEmpty() || str_password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Username and Password is required!", Toast.LENGTH_SHORT).show();
                return; // return the control and do not proceed further
            }

            Log.d("about_to_login user", str_username);
            Log.d("about_to_login pass", str_password);
            if ("user".equals(str_username) && "pass".equals(str_password)) {
                // successful login take to next activity
                Intent intent_user_details = new Intent(getApplicationContext(), UserDetailsActivity.class);

                // put username with intent
                intent_user_details.putExtra("username", str_username);
                // similarly set password with intent
                intent_user_details.putExtra("password", str_password);

                // start the user details activity using startActivity()
                startActivity(intent_user_details);

                // to end the current activity
                finish();

            } else {
                // invalid login, display toast message
                Toast.makeText(getApplicationContext(), "Invalid Username and Password!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
