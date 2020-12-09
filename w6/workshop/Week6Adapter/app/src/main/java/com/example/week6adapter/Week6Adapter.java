package com.example.week6adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Week6Adapter extends RecyclerView.Adapter<Week6Adapter.ViewHolder> {

    ArrayList<PersonName> names = new ArrayList<PersonName>();

    public Week6Adapter(ArrayList<PersonName> names) {
        this.names = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.week6_name_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.d("week6App","onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.firstname_id.setText(names.get(position).getFirstName());
        holder.lastname_id.setText(names.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView firstname_id;
        public TextView lastname_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname_id = itemView.findViewById(R.id.firstname_id);
            lastname_id = itemView.findViewById(R.id.lastname_id);
        }
    }
}
