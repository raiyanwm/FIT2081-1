package edu.monash.fit2081.googlebooks;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<GoogleBook> bookArrayList;

    public RecyclerAdapter(ArrayList<GoogleBook> bookArrayList){
        super();
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); //CardView inflated as RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);
        Log.d("GoogleBook","onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bookAuthor.setText(bookArrayList.get(position).getAuthors());
        holder.bookTitle.setText(bookArrayList.get(position).getBookTitle());
        holder.bookPublishedDate.setText(bookArrayList.get(position).getPublishedDate());
        Log.d("GoogleBook","onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bookAuthor, bookTitle, bookPublishedDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookPublishedDate = itemView.findViewById(R.id.bookPublishedDate);
        }
    }
}
