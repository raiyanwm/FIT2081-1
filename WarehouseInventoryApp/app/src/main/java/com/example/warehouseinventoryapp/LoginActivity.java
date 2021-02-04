package com.example.warehouseinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Base64;


public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
    }

    public void loginClick (View v){
        Intent intent = new Intent(this, MainActivity.class);
        String userName = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (checkAccount(userName, password)) {
            intent.putExtra("username", userName);
            startActivity(intent);
        }
    }

    private boolean checkAccount(String username, String password){
        AccountVerification accountVerification = new AccountVerification();
        if (accountVerification.checkValidity(username, password))
            Log.d("DEBUG",username);
        else {
            Toast.makeText(getBaseContext(), "Wrong username/passowrd", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}