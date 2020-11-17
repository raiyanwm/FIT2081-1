package edu.monash.javarevision2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class MoreListenerCodeActivity
        extends AppCompatActivity
        implements View.OnClickListener { //***Activity is listener which handles click event

    private final String TAG = "WHO_IS_LISTENING";

    private FrameLayout f1, f2, f3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_listener_code);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDisplay();
                Snackbar.make(view, "More work done!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        f1 = (FrameLayout) findViewById(R.id.frameLayout1);
        f2 = (FrameLayout) findViewById(R.id.frameLayout2);
        f3 = (FrameLayout) findViewById(R.id.frameLayout3);

        initDisplay();

        //***using the activity as the listener
        f1.setOnClickListener(this);
        f2.setOnClickListener(this);

        //***using another top level (not nested) class as the listener
        OuterMenuListener listener = new OuterMenuListener();
        f3.setOnClickListener(listener);
    }


    private void initDisplay(){
        f1.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        f2.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        f3.setBackgroundColor(getResources().getColor(R.color.colorDefault));
    }

    public void onClick(View v){ //*** we promised
        if (v.getId() == R.id.frameLayout1)
            v.setBackgroundColor(Color.RED);
        else if (v.getId() == R.id.frameLayout2)
            v.setBackgroundColor(Color.WHITE);

        Log.i(TAG, "event handling by an Activity");
    }

}







