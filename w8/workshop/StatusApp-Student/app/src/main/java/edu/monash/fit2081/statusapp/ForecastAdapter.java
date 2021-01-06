package edu.monash.fit2081.statusapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ForecastAdapter extends ArrayAdapter<ForecastStatus> {

    public ForecastAdapter(Context context, ArrayList<ForecastStatus> status) {
        super(context, 0, status);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ForecastStatus forecastStatus = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.forecast_item, parent, false);

        // Lookup view for data population
        TextView timeStamp = convertView.findViewById(R.id.timestamp);
        TextView status = convertView.findViewById(R.id.status);
        // Populate the data into the template view using the data object
        timeStamp.setText(forecastStatus.getTimeStamp());
        status.setText(forecastStatus.getStatus());
        // Return the completed view to render on screen
        return convertView;
    }

}
