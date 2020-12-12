package com.fit2081.rooms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.rooms.provider.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private List<Customer> mCustomers;

    public CustomerAdapter() {

    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

        return new CustomerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.nameTv.setText(mCustomers.get(position).getName());
        holder.addressTv.setText(mCustomers.get(position).getAddress());

    }


    @Override
    public int getItemCount() {
        if (mCustomers == null)
            return 0;
        else
            return mCustomers.size();
    }

    public void setCustomers(List<Customer> newData) {
        mCustomers=newData;
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;
        public TextView addressTv;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name_id);
            addressTv = itemView.findViewById(R.id.address_id);

        }
    }
}
