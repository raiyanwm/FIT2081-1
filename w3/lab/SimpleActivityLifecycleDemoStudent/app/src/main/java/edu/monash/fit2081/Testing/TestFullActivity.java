package edu.monash.fit2081.Testing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shuxford on 5/3/2015.
 */
public class TestFullActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_full_layout);

    }

    public void getSPFromOtherActivity(View v){
        SharedPreferences settings = getSharedPreferences(Lifecycles.SP_FILE_NAME, 0); // 0 means private to the Linux user ID (i.e. the application)
        String persistentState = settings.getString("persistentDataKey", ""); //returns "" (2nd param) if key not found

        //Log.i(Lifecycles.TAG, "accessed sharedpreference across an app's Activities" + persistentState);

        Toast toast = Toast.makeText(this, "accessed Shared Preferences data \nwritten by Lifecycles activity\n" + persistentState, Toast.LENGTH_LONG);

        //following 2 lines are not essential but I want to centre the text in the toast to make it look nice
        TextView toastTextView = (TextView) toast.getView().findViewById(android.R.id.message); //a toast contains a TextView for its text. For now!!! could break in future API levels
        if( toastTextView != null) toastTextView.setGravity(Gravity.CENTER);

        toast.show();

    }
}
