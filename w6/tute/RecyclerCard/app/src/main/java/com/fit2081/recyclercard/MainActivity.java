package com.fit2081.recyclercard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> data = new ArrayList<>();
    String suits[] = {"Hearts", "Diamonds", "Clubs", "Spade"};
    String cards[] = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    Button btn;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.add_item);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        recyclerView = findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager


        adapter = new MyRecyclerAdapter();
        adapter.setData(data);
        recyclerView.setAdapter(adapter);

    }

    public void addItem() {
        Random random = new Random();
        int randCard = random.nextInt(cards.length);
        int randSuit = random.nextInt(suits.length);
        Item item = new Item(suits[randSuit], cards[randCard]);
        data.add(item);
        adapter.notifyDataSetChanged();

    }

}
