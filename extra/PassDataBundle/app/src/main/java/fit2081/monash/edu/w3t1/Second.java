package fit2081.monash.edu.w3t1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Second extends AppCompatActivity {
    TextView nameTv;
    TextView ageTv;
    TextView seekBarTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // get the intent that is created by MainActivity
        Intent intent = getIntent();
        // get its bundle
        Bundle bundle = intent.getExtras();
        // Now retrieve your three values
        //Note: you should find more descrve names
        String v1 = bundle.getString(MainActivity.KEY_1);
        String v2 = bundle.getString(MainActivity.KEY_2);
        int v3 = bundle.getInt(MainActivity.KEY_3);
        // create referees to your text view to display the three values
        nameTv=findViewById(R.id.name_tv);
        ageTv=findViewById(R.id.age_tv);
        seekBarTv=findViewById(R.id.seekbar_tv);
        nameTv.setText(v1);
        ageTv.setText(v2);
        //setText is expecting a string!!!so..convert int to string
        seekBarTv.setText(Integer.toString(v3));


    }
}
