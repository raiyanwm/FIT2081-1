package edu.monash.fit2081.callsfilter;

import android.Manifest;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> wantedPermissions = new ArrayList<>();

        wantedPermissions.add(Manifest.permission.CALL_PHONE);
        wantedPermissions.add(Manifest.permission.READ_CALL_LOG);
        wantedPermissions.add(Manifest.permission.READ_PHONE_STATE);
        wantedPermissions.add(Manifest.permission.PROCESS_OUTGOING_CALLS);


        ActivityCompat.requestPermissions(this, wantedPermissions.toArray(new String[wantedPermissions.size()]), 0);

    }
}
