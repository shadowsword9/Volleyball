package com.example.volleyball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayersAdapter extends ArrayAdapter<Player> {

    public PlayersAdapter(Context context, ArrayList<Player> Players) {
        super(context, 0, Players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Player Player = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_player, parent, false);
        }

        // Lookup view for data population
        TextView id = (TextView) convertView.findViewById(R.id.playerId);
        TextView name = (TextView) convertView.findViewById(R.id.playerName);
        TextView setter = (TextView) convertView.findViewById(R.id.isSetter);

        // Populate the data into the template view using the data object
        id.setText(String.valueOf(Player.getId()));
        name.setText(Player.getName());
        setter.setText(Player.getIsSetter());

        // Return the completed view to render on screen
        return convertView;

    }

}
