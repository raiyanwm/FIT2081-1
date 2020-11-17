package edu.monash.fit2081.Testing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;


//!!! read  http://developer.android.com/training/basics/activity-lifecycle/recreating.html
public class Lifecycles extends Activity {
    public static final String TAG = "LIFE_CYCLE_TRACING";
    public static final String SP_FILE_NAME = "Testing01PreferencesFile";
    public static final String EXTRA_DATA_1 = "ExtrasData";

    SharedPreferences persistentData;

    private EditText viewDataEditText;
    private EditText nonViewDataEditText;
    private EditText persistentDataEditText;

    private String nonViewState;
    private String persistentState;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.main);

        viewDataEditText = (EditText) findViewById(R.id.viewDataEditText);
        viewDataEditText.setShowSoftInputOnFocus(false);
        nonViewDataEditText = (EditText) findViewById(R.id.nonViewDataEditText);
        nonViewDataEditText.setShowSoftInputOnFocus(false);
        persistentDataEditText = (EditText) findViewById(R.id.persistentDataEditText);
        persistentDataEditText.setShowSoftInputOnFocus(false);

        persistentData = getSharedPreferences(SP_FILE_NAME, MODE_PRIVATE);

    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
}

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");

    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putString(EXTRA_DATA_1,nonViewState);
    }

    //only gets executed if inState != null so no need to check for null Bundle
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        Log.i(TAG, "onRestoreInstanceState");
        nonViewState = inState.getString(EXTRA_DATA_1);
    }


    //==============================================================================================
    public void setNonViewStateInstVar (View view) throws IOException{
        nonViewState = nonViewDataEditText.getText().toString();
        Log.i(TAG, "nonViewState instance var = " + nonViewState);
    }

    public void getNonViewStateInstVar (View view) throws IOException {
        nonViewDataEditText.setText(nonViewState);
        Log.i(TAG, "nonViewState instance var = " + nonViewState);
    }

    //==============================================================================================
    public void setPersistentInstVarVal (View view) throws IOException{
        persistentState = persistentDataEditText.getText().toString();
        Log.i(TAG, "set persistent instance var val = " + persistentState);
    }

    public void getPersistentInstVarVal (View view) throws IOException {
        persistentDataEditText.setText(persistentState);
        Log.i(TAG, "got persistent instance var val = " + persistentState);
    }

    public void setPersistentData (View view) throws IOException{
        saveSharedPreferences();
        Log.i(TAG, "set persistent data = " + persistentState);
    }

    public void getPersistentData (View view) throws IOException {
        restoreSharedPreferences();
        Log.i(TAG, "got persistent data = " + persistentState);
    }

    //==============================================================================================

    public void doToast() {
        Toast toast = Toast.makeText(this, "Toasts have no effect", Toast.LENGTH_LONG);
        toast.show();
    }

    public void doDialog() {
        final String[] possibleChoices = getResources().getStringArray(R.array.testList); // final required !!!!!

        // create a new AlertDialog Builder and set its title
        AlertDialog.Builder choicesBuilder = new AlertDialog.Builder(this);

        choicesBuilder.setTitle("Dialogs have no effect");

        // add possibleChoices's items to the Dialog and set the behaviour when one of the items is clicked
        // setItems(...) is new we usually just setMessage(...)
        choicesBuilder.setItems(R.array.testList,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // this executes after any item click then Android closes dialog immediately
                        // update guessRows to match the user's choice

                        //just a test so do nothing
                    }
                }
        ); // this is the end of the call to setItems(...)

        // create an AlertDialog from the Builder
        AlertDialog choicesDialog = choicesBuilder.create();
        choicesDialog.show(); // show the Dialog
    }
    public void doPartialActivity() {
        Intent testingIntent = new Intent(Lifecycles.this, TestPartialActivity.class);

        int dataToPass = 1000001; //you can pass a lot more than integers i.e. there are many overloads
        testingIntent.putExtra(EXTRA_DATA_1, dataToPass);

        startActivity(testingIntent);
    }

    public void doFullActivity() {
        Intent testingIntent = new Intent(Lifecycles.this, TestFullActivity.class);
        startActivity(testingIntent);
    }

    public void doFinish(){
        finish();
    }

    //==============================================================================================

    private void saveSharedPreferences(){
        SharedPreferences.Editor edit = persistentData.edit();
        edit.putString("persistentData", persistentState);
        edit.apply();
    }

    private void restoreSharedPreferences(){
        persistentState = persistentData.getString("persistentData",null);
    }


    //==============================================================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemMessage:
                //do nothing
                return true;

            case R.id.menuItemToast:
                doToast();
                return true;

            case R.id.menuItemDialog:
                doDialog();
                return true;

            case R.id.menuItemPartialScreen:
                doPartialActivity();
                return true;

            case R.id.menuItemFullScreen:
                doFullActivity();
                return true;

            case R.id.menuItemfinish:
                doFinish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

