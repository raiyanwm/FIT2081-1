package edu.monash.fit2081.callsfilter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyCallsReceiver extends BroadcastReceiver {
    Context self;
    static TelephonyManager telephonyManager;

    public void onReceive(Context context, Intent intent) {


        self = context;

        if (telephonyManager == null) {
            telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            MyPhoneStateListener PhoneListener = new MyPhoneStateListener();
            telephonyManager.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }



    private class MyPhoneStateListener extends PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == 1) {
                showToast("Number:=" + incomingNumber);
            }
        }
    }

    void showToast(String msg) {
        Toast.makeText(self, msg, Toast.LENGTH_LONG).show();
    }
}
