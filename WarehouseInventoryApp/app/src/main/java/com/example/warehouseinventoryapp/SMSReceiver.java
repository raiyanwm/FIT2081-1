package com.example.warehouseinventoryapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
    private static final String TAG = "MESSAGE_LOG";
    public static final String SMS_FILTER = "SMS_ITEM_MESSAGE";
    public static final String SMS_MSG_KEY = "SMS_ITEM_INFO";

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        Intent msgIntent = new Intent();
        msgIntent.setAction(SMS_FILTER);
        msgIntent.putExtra(SMS_MSG_KEY,messages[0].getDisplayMessageBody());
        context.sendBroadcast(msgIntent);
        Log.i(TAG,"Message received");
    }
}