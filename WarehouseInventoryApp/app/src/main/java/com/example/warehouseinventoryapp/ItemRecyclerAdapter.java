package com.example.warehouseinventoryapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {
    ArrayList<Item> data = new ArrayList<>();

    public void setData(ArrayList<Item> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); //CardView inflated as RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);
        Log.d("WarehouseInventory","onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText(data.get(position).getItemName());
        holder.itemQuantity.setText(data.get(position).getItemQuantity());
        holder.itemDescription.setText(data.get(position).getItemDescription());
        holder.itemCost.setText(data.get(position).getItemCost());
        holder.itemIsFrozen.setText(data.get(position).isItemIsFrozen());
        Log.d("WarehouseInventory","onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemCost;
        public TextView itemDescription;
        public TextView itemQuantity;
        public TextView itemIsFrozen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemCost = itemView.findViewById(R.id.itemCost);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            itemIsFrozen = itemView.findViewById(R.id.itemFrozen);
        }
    }
}
