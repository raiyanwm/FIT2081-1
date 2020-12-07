package com.fit2081.recyclercard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    ArrayList<Item> data = new ArrayList<Item>();

    public void setData(ArrayList<Item> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); //CardView inflated as RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);
        Log.d("week6App","onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardTv.setText(data.get(position).getCard());
        holder.suitTv.setText(data.get(position).getSuit());
        Log.d("week6App","onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView suitTv;
        public TextView cardTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            suitTv = itemView.findViewById(R.id.suit_id);
            cardTv = itemView.findViewById(R.id.card_id);
        }
    }
}
