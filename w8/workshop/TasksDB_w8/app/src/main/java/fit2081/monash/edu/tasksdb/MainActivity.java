package fit2081.monash.edu.tasksdb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fit2081.monash.edu.tasksdb.provider.Task;
import fit2081.monash.edu.tasksdb.provider.TaskViewModel;


public class MainActivity extends AppCompatActivity implements DeleteListener {

    RecyclerView recyclerView;
    EditText nameEt;
    EditText descEt;

    TaskViewModel mTaskViewModel;
    TaskRecyclerAdapter adapter;
    List<Task> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Reference to the DB

        nameEt = findViewById(R.id.task_name);
        descEt = findViewById(R.id.task_desc);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        data = new ArrayList<>();
        adapter = new TaskRecyclerAdapter(data, mTaskViewModel);
        adapter.setDeleteListener(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        mTaskViewModel.getAllTasksModelView().observe(this, newData -> {
            adapter.setData(newData);
        });

    }


    public void addTaskBtnHandler(View view) {
        Task task = new Task(nameEt.getText().toString(), descEt.getText().toString());
        mTaskViewModel.insertTaskViewModel(task);
    }

    @Override
    public void onClick(int id) {
        mTaskViewModel.deleteById(id);
        adapter.notifyDataSetChanged();
    }
}
