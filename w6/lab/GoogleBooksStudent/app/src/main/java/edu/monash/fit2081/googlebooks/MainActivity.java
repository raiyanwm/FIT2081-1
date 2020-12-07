package edu.monash.fit2081.googlebooks;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager
    }

    public void handleSearchBtn(View view) {

    }


    private void makeRequest(String keywords) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + keywords;


        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.GET, url, null,
                                      new Response.Listener<JSONObject>() {
                                          @Override
                                          public void onResponse(JSONObject response) {
                                              try {
                                                  //Create a list of books
                                                  ArrayList<GoogleBook> dataItems = new ArrayList<>();
                                                  // retrieve the list of books from the response
                                                  JSONArray bookList = response.getJSONArray("items");
                                                  JSONObject aBook;
                                                  GoogleBook googleBook;
                                                  // iterate through the list of books
                                                  for (int i = 0; i < bookList.length(); i++) {
                                                      // try..catch is merely used to ignore a book if it has an issue such as missing author or pub date
                                                      try {
                                                          //
                                                          aBook = bookList.getJSONObject(i);
                                                          JSONObject volumeInfo = aBook.getJSONObject("volumeInfo");
                                                          String author = volumeInfo.getJSONArray("authors").getString(0);
                                                          String title = volumeInfo.getString("title");
                                                          String publishedDate = volumeInfo.getString("publishedDate");
                                                          googleBook = new GoogleBook(title, author, publishedDate);
                                                          dataItems.add(googleBook);
                                                      } catch (Exception e) {
                                                      }

                                                  }
                                                  // Now: all the data items are in the array list, send it to the recycler adapter to create views.
                                                  //Create a new RecyclerAdaptor and send your data to it (hint: via the constructor)
                                                  // assign (i.e. set) the adapter to the recycler view
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
