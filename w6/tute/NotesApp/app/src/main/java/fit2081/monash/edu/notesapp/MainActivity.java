package fit2081.monash.edu.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rV;
    EditText theNoteEt;
    RecyclerView.LayoutManager layoutManager;

    MyAdaptor adaptor;

    //The data source
    ArrayList<String> ar = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create a reference to the edit text
        theNoteEt = findViewById(R.id.note_id);

        rV = findViewById(R.id.rv_id);
        //The Recycler View needs a layout manager
        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        rV.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager

        //Create a new adaptor and pass your data source to it
        adaptor=new MyAdaptor(ar);

        //Link the adaptor to the recycler View
        rV.setAdapter(adaptor);



    }

    // this method is the handler for the add note button
    public void handleAddNoteBtn(View v) {
        String theNoteString = theNoteEt.getText().toString();
        ar.add(theNoteString);
        // ask the adaptor to refresh the recycler view
        adaptor.notifyDataSetChanged();
    }


}
