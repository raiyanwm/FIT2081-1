package edu.monash.fit2081.livestockapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    String STOCK_SYMBOL = "GOOGL"; // Google
    /*
    NFLX: Netflix
    AAPL: Apple
    AMZN: Amazon
    MSFT: Microsoft
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager
        makeRequest();
    }


    private void makeRequest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + STOCK_SYMBOL + "&interval=15min&apikey=PU8GPVJCDYMBBAQF";


        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.GET, url,null,
                                      new Response.Listener<JSONObject>() {
                                          @Override
                                          public void onResponse(JSONObject response) {
                                              try {
                                                  //Get the symbol of the stock; it should be GOOGL
                                                  String stockSymbol = response.getJSONObject("Meta Data").getString("2. Symbol");
                                                  //Update the collapsible toolbar
                                                  ((TextView) findViewById(R.id.stock_symbol)).setText(stockSymbol);

                                                  //Now lets parse the data
                                                  JSONObject listItems = response.getJSONObject("Time Series (15min)");
                                                  Iterator<?> keys = listItems.keys();
                                                  // Declare the list of items which will be sent to the array adapter
                                                  ArrayList<StockItem> dataItems = new ArrayList<>();
                                                  // for each key in the object
                                                  //IMPORTANT: each element is an object has a key. In other words, it is not an array of elements. Refer to the API
                                                  while (keys.hasNext()) {
                                                      // get the key
                                                      String key = (String) keys.next();
                                                      // get the object of the key
                                                      JSONObject value = (JSONObject) listItems.get(key);
                                                      // now: get the values of the required keys only
                                                      String itemOpen = "Open: " + value.getString("1. open");
                                                      String itemClose = "Close: " + value.getString("4. close");
                                                      String itemVolume = "Volume: " + value.getString("5. volume");
                                                      //Generate a new stock object and pass the four values to its constructor
                                                      StockItem stockItem = new StockItem("Date/Time: " + key, itemOpen, itemClose, itemVolume);
                                                      // add to the array list
                                                      dataItems.add(stockItem);
                                                  }
                                                  // Now: all the data items are in the array list, send it to the recycler adapter to create views.
                                                  adapter = new RecyclerAdapter(dataItems);
                                                  // assign the adapter to the recycler view
                                                  recyclerView.setAdapter(adapter);
                                              } catch (Exception e) {
                                                  Log.d("stock", e.getMessage());
                                              }
                                          }
                                      }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("stock", error.getMessage());
                    }
                });
        // due to long response time, we need to add a long delay time
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
