package edu.monash.constrainedlayout;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Barriers extends AppCompatActivity {
    TextView tvVariableText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barriers);

        tvVariableText = findViewById(R.id.tv_adjust);
    }

    // append the text to change the width of TextView on Left
    // this will push the barrier to the right as barrier can
    // adjust as per elements widths
    public void appendText(View v){
        tvVariableText.append(" Text");
    }

    // reset the text to default value
    public void resetText(View v){
        tvVariableText.setText("Variable width");
    }

    // method to navigate to the chains activity
    public void navigateToChainsActivity(View v){
        Intent chains_act = new Intent(getApplicationContext(), ChainsActivity.class);
        startActivity(chains_act);
    }
}
