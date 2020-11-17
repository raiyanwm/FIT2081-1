package fit2081.monash.edu.w3t1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declare three keys to be public and static.
    // Static inorder to access them from outside the class using the class name
    public static final String KEY_1 = "key1";
    public static final String KEY_2 = "key2";
    public static final String KEY_3 = "Key3";

    //Declare refereces to the views. Class level in order to access them from multiple places
    EditText nameEt;
    EditText ageEt;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get access to the views using their ids
        nameEt = findViewById(R.id.nameId);
        ageEt = findViewById(R.id.ageId);
        seekBar = findViewById(R.id.seekBar);
    }

    // this callback will get executed each time the button gets a click/tap
    public void handleNextBtn(View view) {

        // lets retrieve the data from the views
        String nameSt = nameEt.getText().toString();
        //Note: age should int, please change it..
        String ageSt = ageEt.getText().toString();
        int seekValue = seekBar.getProgress();
        // create an intent to start another activity
        Intent intent = new Intent(this, Second.class);
        //add my three values to the bundle of the intent using three keys I created earlier
        intent.putExtra(KEY_1, nameSt);
        intent.putExtra(KEY_2, ageSt);
        intent.putExtra(KEY_3, seekValue);
        // Start the second activity
        startActivity(intent);

    }
}
