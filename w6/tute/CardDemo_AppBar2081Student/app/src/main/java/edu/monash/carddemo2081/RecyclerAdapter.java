package edu.monash.carddemo2081;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] clubNames = {
            "Arsenal", "Bournemouth", "Burnley", "Chelsea", "Crystal Palace",
            "Everton", "Hull", "Leicester", "Liverpool", "Manchester City",
            "Manchester United", "Middlebrough", "Southhampton", "Stoke", "Sunderland",
            "Swansea", "Tottenham", "Watford", "West Bromwich Albion", "West Ham"
    };

    private String[] grounds = { "Emirates Stadium", "Dean Court", "Turf Moor", "Stamford Bridge", "Selhurst Park",
            "Goodison Park", "KCOM Stadium", "King Power Stadium", "Anfield", "Etihad Stadium",
            "Old Trafford", "Riverside Stadium", "St Mary's Stadium", "bet365 Stadium", "Stadium of Light",
            "Liberty Stadium", "White Hart Lane", "Vicarage Road", "The Hawthorns", "London Stadium"
    };

    private int[] groundCapacities = {
            60432, 11700, 22546, 41798, 26309,
            39571, 25400, 32500, 54167, 55000,
            75635, 33746, 32689, 27740, 48707,
            20937, 36284, 21977, 26445, 60000
    };

    private int[] leaguePositions = {
            6, 11, 13, 1, 16,
            7, 18, 15, 4, 3,
            5, 19, 10, 9, 20,
            17, 2, 14, 8, 12
    };

    private int[] images = {
            R.drawable.arsenal,
            R.drawable.bournemouth,
            R.drawable.burnley,
            R.drawable.chelsea,
            R.drawable.crystal_palace,
            R.drawable.everton,
            R.drawable.hull,
            R.drawable.leicester,
            R.drawable.liverpool,
            R.drawable.manchester_city,
            R.drawable.manchester_united,
            R.drawable.middlesbrough,
            R.drawable.southhampton,
            R.drawable.stoke,
            R.drawable.sunderland,
            R.drawable.swansea,
            R.drawable.tottenham,
            R.drawable.watford,
            R.drawable.west_bromwich_albion,
            R.drawable.west_ham
    };

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false); //CardView inflated as RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.itemImage.setImageResource(images[position]);
        viewHolder.itemTitle.setText(clubNames[position]);

        //a class declared in a method (so called local or anonymous class can only access the method's local variables if they are declared final (1.8 or are effectively final)
        //this has to do with Java closures
        // see https://docs.oracle.com/javase/tutorial/java/javaOO/localclasses.html and https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
        final int fPosition = position;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { //set back to itemView for students
            @Override public void onClick(View v) {

                Snackbar.make(v, "Item at position " + fPosition + " was clicked!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return clubNames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public ImageView itemImage;
        public TextView itemTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);

        }
    }

}
