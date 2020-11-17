package edu.monash.javarevision1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class JavaRevision1Activity extends AppCompatActivity {
    private final String TAG = "INHERITANCE_REVISION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_revision1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payrollSystemTest();
                Snackbar.make(view, "Work Done", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    private void payrollSystemTest(){
        // create subclass objects
        SalariedEmployee salariedEmployee = new SalariedEmployee( "John", "Smith", "111-11-1111", 800.00);
        HourlyEmployee hourlyEmployee = new HourlyEmployee( "Karen", "Price", "222-22-2222", 16.75, 40);
        CommissionEmployee commissionEmployee = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);
        BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);


        // process each employee subtype object
        Log.i(TAG, "***Process Employee subtype objects (reference and object same type)***");

        //objects are pointed at by a reference of the same class, the implicit toString of each subclass executes because of overriding
        //System.out.printf( "%s\n%s: $%,.2f\n\n", salariedEmployee, "earned", salariedEmployee.earnings());
        Log.i(TAG, String.format("%s\n%s: $%,.2f", salariedEmployee, "earned", salariedEmployee.earnings())); Log.i(TAG," ");
        Log.i(TAG, String.format("%s\n%s: $%,.2f", hourlyEmployee, "earned", hourlyEmployee.earnings())); Log.i(TAG," ");
        Log.i(TAG, String.format("%s\n%s: $%,.2f", commissionEmployee, "earned", commissionEmployee.earnings())); Log.i(TAG," ");
        Log.i(TAG, String.format("%s\n%s: $%,.2f", basePlusCommissionEmployee, "earned", basePlusCommissionEmployee.earnings()));
        Log.i(TAG," "); Log.i(TAG," ");



        // create four-element Employee array, objects are pointed at by references of the ancestor Employee class
        Employee[] employees = new Employee[ 4 ]; //an array of Employee pointers pointing at objects of subclasses of Employee

        // initialize array with Employees
        employees[ 0 ] = salariedEmployee;              //type SalariedEmployee subclass of Employee
        employees[ 1 ] = hourlyEmployee;                //type HourlyEmployee subclass of Employee
        employees[ 2 ] = commissionEmployee;            //type CommissionEmployee subclass of Employee
        employees[ 3 ] = basePlusCommissionEmployee;    //type basePlusCommissionEmployee subclass of CommissionEmployee

        // process array of employee subtype objects POLYMORHICALLY
        Log.i(TAG, "***Process Employee subtype objects polymorphically (reference is supertype)***");
        //using for each loop
        for ( Employee currentEmployee : employees ){
            Log.i(TAG, currentEmployee.toString() ); // Q. Which toString()? A. the toString() appropriate to the subclass of the object pointed at by currentEmployee

            // no special treatment for BasePlusCommissionEmployee required - we are just showing we can do it if required using instanceOff
            if ( currentEmployee instanceof BasePlusCommissionEmployee ){

                // downcast Employee reference to BasePlusCommissionEmployee reference because Employee does not recognise get/set BaseSalary neither should it
                BasePlusCommissionEmployee bcEmployee = ( BasePlusCommissionEmployee ) currentEmployee; //only works at runtime if currentEmployee is pointing at BasePlusCommisionEmployee which we have ensured

                bcEmployee.setBaseSalary( 1.10 * bcEmployee.getBaseSalary() );

                //((BasePlusCommissionEmployee)currentEmployee).getBaseSalary(); //same as previous line but no wasted down cast variable

                Log.i(TAG, String.format("<downcast>new base salary with 10%% increase is: $%,.2f\n", bcEmployee.getBaseSalary() ));
            }

            Log.i(TAG, String.format("earned $%,.2f\n\n", currentEmployee.earnings() )); //Q. Which earnings()? A. the earnings() appropriate to the subclass of the object pointed at by currentEmployee
            Log.i(TAG," ");
        }

//        //using indexed for loop
//        for (int i = 0; i < employees.length; i++){
//            Log.i(TAG, employees[i].toString() ); // Q. Which toString()? A. the toString() appropriate to the subclass of the object pointed at by currentEmployee
//
//            // no special treatment for BasePlusCommissionEmployee required - we are just showing we can do it if required using instanceOff
//            if ( employees[i] instanceof BasePlusCommissionEmployee ){
//
//                // downcast Employee reference to BasePlusCommissionEmployee reference because Employee does not recognise get/set BaseSalary neither should it
//                BasePlusCommissionEmployee bcEmployee = ( BasePlusCommissionEmployee ) employees[i]; //only works at runtime if currentEmployee is pointing at BasePlusCommisionEmployee which we have ensured
//
//                bcEmployee.setBaseSalary( 1.10 * bcEmployee.getBaseSalary() );
//
//                //((BasePlusCommissionEmployee)currentEmployee).getBaseSalary(); //same as previous line but no wasted down cast variable
//
//                Log.i(TAG, String.format("<downcast>new base salary with 10%% increase is: $%,.2f\n", bcEmployee.getBaseSalary() ));
//            }
//
//            Log.i(TAG, String.format("earned $%,.2f\n\n", employees[i].earnings() )); //Q. Which earnings()? A. the earnings() appropriate to the subclass of the object pointed at by currentEmployee
//            Log.i(TAG," ");
//        }

        // get type name of each object's class in the employees array
        Log.i(TAG," ");Log.i(TAG," ");
        Log.i(TAG, "***Get type name of each object's class (not reference type) in the employees array***");
        for ( int j = 0; j < employees.length; j++ )
            Log.i(TAG, String.format("Employee %d is a %s\n", j, employees[ j ].getClass().getName() ));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_java_revision1, menu);
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
