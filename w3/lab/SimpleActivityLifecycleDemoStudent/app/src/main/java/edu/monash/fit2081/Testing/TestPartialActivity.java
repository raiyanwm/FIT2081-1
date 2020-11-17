package edu.monash.fit2081.Testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by shuxford on 5/3/2015.
 */
public class TestPartialActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_partial_layout);

        Intent intent = getIntent();
        int passedData = intent.getIntExtra(Lifecycles.EXTRA_DATA_1, -1);

        TextView extraDataDisplay = (TextView) findViewById(R.id.intentExtrasTextView);

        extraDataDisplay.setText("passed extra\n" + passedData);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        super.onCreateOptionsMenu(menu);
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.test_menu, menu);
//
//        return true;
//    }
}
