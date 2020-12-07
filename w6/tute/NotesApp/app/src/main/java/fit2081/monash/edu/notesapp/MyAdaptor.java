package fit2081.monash.edu.notesapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {

    ArrayList<String> data = new ArrayList<>();

    //The constructor is required to receive the data source
    public MyAdaptor(ArrayList<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_layout, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int pos) {
        myViewHolder.theNoteTv.setText(data.get(pos));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView theNoteTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            theNoteTv = itemView.findViewById(R.id.note_tv_id);
        }
    }
}
