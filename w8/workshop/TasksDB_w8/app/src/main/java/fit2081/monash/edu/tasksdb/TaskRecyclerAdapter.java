package fit2081.monash.edu.tasksdb;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fit2081.monash.edu.tasksdb.provider.Task;
import fit2081.monash.edu.tasksdb.provider.TaskViewModel;


public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskRecyclerAdapter.ViewHolder> {

    List<Task> data;
    TaskViewModel taskViewModel;

    private DeleteListener deleteListener;

    public void setDeleteListener(DeleteListener mCallback) {
        this.deleteListener = mCallback;
    }


    public TaskRecyclerAdapter(List<Task> data, TaskViewModel taskViewModel) {
        this.data = data;
        this.taskViewModel = taskViewModel;
    }

    public void setData(List<Task> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRecyclerAdapter.ViewHolder holder, int position) {
        holder.nameTv.setText(data.get(position).getName());
        holder.descTv.setText(data.get(position).getDescription());

        holder.deleteBtn.setOnClickListener(view -> {
            deleteListener.onClick(data.get(position).getId());
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView descTv;
        Button deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.list_task_name);
            descTv = itemView.findViewById(R.id.list_task_desc);
            deleteBtn = itemView.findViewById(R.id.list_delete_task);
        }
    }
}
