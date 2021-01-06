package com.example.warehouseinventoryapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehouseinventoryapp.provider.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {
    private List<Item> mItems;

    public void setItem(List<Item> mItems){
        this.mItems = mItems;
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
        holder.itemName.setText(mItems.get(position).getItemName());
        holder.itemQuantity.setText(Integer.toString(mItems.get(position).getItemQuantity()));
        holder.itemDescription.setText(mItems.get(position).getItemDescription());
        holder.itemCost.setText(Double.toString(mItems.get(position).getItemCost()));
        holder.itemIsFrozen.setText(Boolean.toString(mItems.get(position).isItemIsFrozen()));
        holder.itemId.setText(Integer.toString(mItems.get(position).getId()));
        //holder.totalCost.setText(Double.toString(mItems.get(position).getTotalCost()));
        Log.d("WarehouseInventory","onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        if (mItems == null)
            return 0;
        else
            return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemCost;
        public TextView itemDescription;
        public TextView itemQuantity;
        public TextView itemIsFrozen;
        public TextView totalCost;
        public TextView itemId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemCost = itemView.findViewById(R.id.itemCost);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            itemIsFrozen = itemView.findViewById(R.id.itemFrozen);
            itemId = itemView.findViewById(R.id.itemId);
            //totalCost = itemView.findViewById(R.id.totalCost);
        }
    }
}
