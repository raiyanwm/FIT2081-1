package edu.monash.javarevision1p5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class JavaRevision1p5Activity extends AppCompatActivity {
    private final String TAG = "INTERFACE_REVISION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_revision1p5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doWork();
                Snackbar.make(view, "Work done!", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });
    }

    private void doWork(){
        // create six-element Payable array
        Payable payableObjects[] = new Payable[ 6 ];

        // populate array with objects that implement Payable
        payableObjects[ 0 ] = new Invoice( "01234", "seat", 2, 375.00 );
        payableObjects[ 1 ] = new Invoice( "56789", "tire", 4, 79.95 );

        payableObjects[ 2 ] = new SalariedEmployee( "John", "Smith", "111-11-1111", 800.00);
        payableObjects[ 3 ] = new HourlyEmployee( "Karen", "Price", "222-22-2222", 16.75, 40);
        payableObjects[ 4 ] = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);
        payableObjects[ 5 ] = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);

        // process array of Payable subtype objects (Invoices and Employee subtypes) POLYMORHICALLY
        Log.i(TAG, "***Process Payable subtype objects polymorphically (reference is 'super' Interface type)***");
        //using for each loop
        for ( Payable currentPayable : payableObjects ){
           // output currentPayable and its appropriate payment amount
            Log.i(TAG, String.format("%s \n%s: $%,.2f\n\n", currentPayable.toString(), "payment due", currentPayable.getPaymentAmount()));
            Log.i(TAG, " ");
        }

//        //using indexed for loop
//        for ( int i = 0; i < payableObjects.length; i++) {
//            // output currentPayable and its appropriate payment amount
//            Log.i(TAG, String.format("%s \n%s: $%,.2f\n\n", payableObjects[i].toString(), "payment due", payableObjects[i].getPaymentAmount()));
//            Log.i(TAG, " ");
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_java_revision1p5, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
