package com.example.volleyball;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlayerList extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editNewPlayer;
    CheckBox newSetterCheckBox;
    Button subBtn;
    Button viewAllBtn;
    Button updateBtn;
    Button deleteBtn;
    EditText updateEditText;
    ListView listView;


    ArrayList<String> playersArrayList;
    ArrayAdapter adapter;


    private static final String TAG = "NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDB = new DatabaseHelper(this);

        setContentView(R.layout.player_list);

        //cast variables in oncreate
        editNewPlayer = findViewById(R.id.addPlayerEditText);
        subBtn = findViewById(R.id.submit);
        newSetterCheckBox = findViewById(R.id.isSetterCheckBox);
        viewAllBtn = findViewById(R.id.viewAllPlayers);
        updateBtn = findViewById(R.id.updatePlayers);
        updateEditText = findViewById(R.id.playersEditText);
        deleteBtn = findViewById(R.id.deletePlayer);
        playersArrayList = new ArrayList<>();
        listView = findViewById(R.id.playerListView);

        //call addData
        AddData();
        viewAll();
        updateData();
        deleteData();
        viewAllList();




    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String text = listView.getItemAtPosition(position).toString();
            Toast.makeText(PlayerList.this, ""+text, Toast.LENGTH_LONG).show();

        }
    });

    }

    public void AddData() {

        subBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        boolean isInserted = myDB.insertData(editNewPlayer.getText().toString(), newSetterCheckBox.isChecked());
                        editNewPlayer.setText(null); //resets text input to blank
                        if (isInserted == true)
                            Toast.makeText(PlayerList.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PlayerList.this, "DATA ERROR. NOT INSERTED", Toast.LENGTH_LONG).show();


                    }
                }
        );


    }

    public void viewAll() {
        viewAllBtn.setOnClickListener((View v) -> {
            Cursor result = myDB.getAllData();
            if (result.getCount() == 0) { //result count
                //show msg
                showMessage("Error,", "NO DATA!");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (result.moveToNext()) {
                buffer.append(result.getString(0) + ". " + result.getString(1) + "\n");

            }
            showMessage("List of Players", buffer.toString());
            //show all data
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData() {
        updateBtn.setOnClickListener((View v) -> {
            boolean isUpdate = myDB.updateData(updateEditText.getText().toString(), editNewPlayer.getText().toString(), newSetterCheckBox.getText().toString());
            if (isUpdate == true) {
                Toast.makeText(PlayerList.this, "Data Updated", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(PlayerList.this, "DATA ERROR. NOT Updated", Toast.LENGTH_LONG).show();


            }
        });

    }

    public void deleteData() {
        deleteBtn.setOnClickListener((View v) -> {
            Integer deletedRows = myDB.deleteData(updateEditText.getText().toString());
            if (deletedRows > 0) {
                Toast.makeText(PlayerList.this, "Data Deleted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(PlayerList.this, "DATA ERROR. NOT DELETED", Toast.LENGTH_LONG).show();


            }


        });

    }

    public void viewAllList() {

        Cursor result = myDB.getAllDataViewList();
        if (result.getCount() == 0) { //result count
            //show msg
            showMessage("Error,", "NO DATA!");
            return;
        } else {
            while (result.moveToNext()) {
                playersArrayList.add(result.getString(1));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, playersArrayList);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);
        }
    }

    
}




