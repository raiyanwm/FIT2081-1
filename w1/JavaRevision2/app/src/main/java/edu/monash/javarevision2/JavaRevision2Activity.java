package edu.monash.javarevision2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class JavaRevision2Activity extends AppCompatActivity {
    private final String TAG = "WHO_IS_LISTENING";

    private FrameLayout f1, f2, f3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_revision2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDisplay();

                Snackbar.make(view, "Display Cleared", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        f1 = (FrameLayout) findViewById(R.id.frameLayout1);
        f2 = (FrameLayout) findViewById(R.id.frameLayout2);
        f3 = (FrameLayout) findViewById(R.id.frameLayout3);

        initDisplay();

        //***instantiating a named listener object of a named class for setOnClickListener
        InnerMenuListener listener = new InnerMenuListener();
        f1.setOnClickListener(listener);

        //***instantiating an anonymous instance of a listener object (of a named class) in-place
        f2.setOnClickListener(new InnerMenuListener() );

        //***instantiating an anonymous instance of a listener object (of an anonymous class declared in-place) in-place
        f3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                v.setBackgroundColor(Color.BLUE);
                Log.i(TAG, "event handling by an anonymous nested class");
            }
        });
    }

    private void initDisplay(){
        f1.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        f2.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        f3.setBackgroundColor(getResources().getColor(R.color.colorDefault));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_java_revision2, menu);
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

    private class InnerMenuListener implements View.OnClickListener{ //could be a top level class in its own .java file if set visibility to public
        public void onClick(View v){
            //// TODO: 5/02/2017 code response
            if (v.getId() == R.id.frameLayout1) {
                v.setBackgroundColor(Color.RED);
                Log.i(TAG, "event handling by a nested class (named listener)");
            }
            else if (v.getId() == R.id.frameLayout2) {
                v.setBackgroundColor(Color.WHITE);
                Log.i(TAG, "event handling by a nested class (anonymous listener)");
            }
        }
    }

    //event handling the easy way - no explicit listener (is the Activity automatically wired?)
    public void more(View v){
        Log.i(TAG, "event handling by a property/attribute of a button View widget");
        Intent i = new Intent(this, MoreListenerCodeActivity.class);
        startActivity(i);
    }
}
