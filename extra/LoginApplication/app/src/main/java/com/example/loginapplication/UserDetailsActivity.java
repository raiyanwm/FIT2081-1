package com.example.loginapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class UserDetailsActivity extends AppCompatActivity {

    // class variables to store user info
    String username, password; // these we need to get from previous activity

    // TextView to set welcome message
    TextView txtv_welcome;

    int user_age; // age we will get on current activity
    String user_state; // user state province we will get from current activity
    Boolean has_ios, has_android; // to store mobile device info

    // to get reference to checkboxes
    CheckBox chk_ios, chk_android;

    // to get reference to states Spinner (Drop down)
    Spinner spinner_state;

    // button to submit additional info
    Button btn_save_details;

    // edit text to get age in number
    EditText edt_age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // same as previous activity get references to the UI elements
        txtv_welcome = findViewById(R.id.txtv_welcome_message);

        // to get user age in integer
        edt_age = findViewById(R.id.edt_age);

        // get links to other UI elements as well
        chk_ios = findViewById(R.id.chk_ios);
        chk_android = findViewById(R.id.chk_android);

        // Spinner for states
        spinner_state = findViewById(R.id.spinner_states);
        // the submit button
        btn_save_details = findViewById(R.id.btn_save_details);

        // start: get data from previous activity
        // get the intent
        Intent login_intent = getIntent();

        // get bundle object having our login data
        Bundle bundle = login_intent.getExtras();

        if (bundle != null) {
            username = bundle.getString("username");
            password = bundle.getString("password");
        }
        // end: get data from previous activity

        // set the welcome message using the username above received
        txtv_welcome.setText("Welcome " + username + "!");


        // another way to define a click function for a button
        // unlike the last activity where we made activity as click listener
        btn_save_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get users age
                String str_age = edt_age.getText().toString();
                if (!str_age.isEmpty()) {
                    user_age = Integer.parseInt(str_age);
                }

                // get selected state
                user_state = spinner_state.getSelectedItem().toString();

                // get different mobile devices as Boolean flags
                has_ios = chk_ios.isChecked();
                has_android = chk_android.isChecked();

                // successful login take to next activity
                Intent intent_display_details = new Intent(getApplicationContext(), DisplayDetails.class);

                // put extra info like the last activity
                intent_display_details.putExtra("username", username);
                intent_display_details.putExtra("password", password);
                intent_display_details.putExtra("age", user_age);
                intent_display_details.putExtra("state", user_state);

                // set the boolean flags in extras as well
                intent_display_details.putExtra("has_ios", has_ios);
                intent_display_details.putExtra("has_android", has_android);

                // remove display details activity from history
                intent_display_details.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // start the display details activity using startActivity()
                startActivity(intent_display_details);

                // to end the current activity
                finish();
            }
        });

    }
}
