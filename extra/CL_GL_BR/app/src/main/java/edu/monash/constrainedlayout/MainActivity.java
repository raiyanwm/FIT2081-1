package edu.monash.constrainedlayout;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // method to navigate to the barriers activity
    public void navigateToBarriersActivity(View v){
        Intent barrier_act = new Intent(getApplicationContext(), Barriers.class);
        startActivity(barrier_act);
    }
}
