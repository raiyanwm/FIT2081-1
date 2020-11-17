package edu.monash.javarevision2;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

/**
 * Created by stephen huxford on 6/02/2017.
 */


public class OuterMenuListener implements View.OnClickListener{ //could be a top level class in its own .java file if set visibility to public
    private final String TAG = "WHO_IS_LISTENING";

    public void onClick(View v){
        if (v.getId() == R.id.frameLayout3)
            v.setBackgroundColor(Color.BLUE);
        Log.i(TAG, "event handling by an external top level class");

    }
}

