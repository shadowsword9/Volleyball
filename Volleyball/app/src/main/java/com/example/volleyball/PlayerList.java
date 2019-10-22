package com.example.volleyball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PlayerList extends AppCompatActivity {

    private ArrayList<Player> playersArrayList;
    private static final String TAG = "NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_list);

        playersArrayList = new ArrayList<>();
//        playersArrayList.add(new Player(1,"Steven","yes"));
//        playersArrayList.add(new Player(2,"Bob","no"));
//        playersArrayList.add(new Player(3,"James","no"));



        for (int i = 0; i< playersArrayList.size(); i++){

            Log.d(TAG, "onCreate: "+ playersArrayList.get(i).getName());
        }

        PlayersAdapter adapter = new PlayersAdapter(this, playersArrayList);
        // Attach the adapter to a ListView

        ListView listView = (ListView) findViewById(R.id.playerListView);
        listView.setAdapter(adapter);

        // Add item to adapter
        Player newPlayer = new Player(4,"Link", "yes");
        Player newPlayer1 = new Player(5,"Steven", "no");
        Player newPlayer2 = new Player(6,"Pikachu", "no");
        adapter.add(newPlayer);
        adapter.add(newPlayer1);
        adapter.add(newPlayer2);
    }
}