package com.example.workshop10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View frameLayout = findViewById(R.id.week10id);
        View constraintLayout = findViewById(R.id.const_week_10);

        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                float xCoord = event.getX();
                float yCoord = event.getY();
                String message = String.format("(X,Y) coordinates = (%.2f, %.2f)", xCoord, yCoord);
                switch (action){
                    case (MotionEvent.ACTION_UP ):
                        Snackbar mySnackBar= Snackbar.make(frameLayout,message, Snackbar.LENGTH_SHORT);
                        mySnackBar.show();
                        break;
                }
                return true;
            }
        });

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                float xCoord = event.getRawX();
                String message = String.format("Distance: %.2f", Math.abs(xCoord));
                Toast myToast= Toast.makeText(getBaseContext(),message,Toast.LENGTH_SHORT);
                switch (action){
                    case (MotionEvent.ACTION_UP ):
                        myToast.show();
                        break;
                    case (MotionEvent.ACTION_DOWN):
                        myToast.show();
                        break;
                    case (MotionEvent.ACTION_MOVE ):
                        myToast.show();
                        break;
                }
                return true;
            }
        });
    }
}