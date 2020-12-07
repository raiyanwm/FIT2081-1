package edu.monash.fit2081.livestockapp;

import com.google.android.material.snackbar.Snackbar;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<StockItem> data;


    public RecyclerAdapter(ArrayList<StockItem> _data) {
        super();
        data = _data;
        Log.d("stock", "got data with size=" + _data.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false); //CardView inflated as RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Log.d("stock", "Bind a view for pos" + position);
        viewHolder.itemTitle.setText(data.get(position).getItemTitle());
        viewHolder.itemOpen.setText(data.get(position).getItemOpen());
        viewHolder.itemClose.setText(data.get(position).getItemClose());
        viewHolder.itemVolume.setText(data.get(position).getItemVolume());


        //a class declared in a method (so called local or anonymous class can only access the method's local variables if they are declared final (1.8 or are effectively final)
        //this has to do with Java closures
        // see https://docs.oracle.com/javase/tutorial/java/javaOO/localclasses.html and https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
        final int fPosition = position;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { //set back to itemView for students
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "Item at position " + fPosition + " was clicked!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public TextView itemTitle;
        public TextView itemOpen;
        public TextView itemClose;
        public TextView itemVolume;


        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemTitle = itemView.findViewById(R.id.item_title);
            itemOpen = itemView.findViewById(R.id.item_open);
            itemClose = itemView.findViewById(R.id.item_close);
            itemVolume = itemView.findViewById(R.id.item_volume);
        }
    }


}
