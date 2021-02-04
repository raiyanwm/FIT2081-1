package com.example.warehouseinventoryapp;

import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AccountVerification {
    Hashtable<String, String> accounts = new Hashtable<>();
    public AccountVerification(){
        accounts.put("David", encrypt("Ok"));
        accounts.put("Test", encrypt("No"));
        accounts.put("Admin", encrypt("Admin"));
    }
    protected boolean checkValidity(String user, String password){
        return accounts.get(user).equals(encrypt(password));
    }

    public String encrypt(String string) {
        string += (int)(string.charAt(0)) + (int)(string.charAt(string.length()-1));
        return string;
    }
}
