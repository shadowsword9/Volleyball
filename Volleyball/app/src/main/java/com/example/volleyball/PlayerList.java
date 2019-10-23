package com.example.volleyball;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayerList extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editNewPlayer;
    CheckBox newSetterCheckBox;
    Button subBtn;
    Button viewAllBtn;
    Button updateBtn;
    Button deleteBtn;
    EditText updateEditText;


    private ArrayList<Player> playersArrayList;
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

        //call addData
        AddData();
        viewAll();
        updateData();
        deleteData();


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
    public void AddData(){

        subBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        boolean isInserted = myDB.insertData(editNewPlayer.getText().toString(), newSetterCheckBox.isChecked());

                        if (isInserted == true)
                            Toast.makeText(PlayerList.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PlayerList.this,"DATA ERROR. NOT INSERTED",Toast.LENGTH_LONG).show();


                    }
                }
        );



    }

    public void viewAll(){
        viewAllBtn.setOnClickListener((View v) -> {
            Cursor result = myDB.getAllData();
                if(result.getCount() == 0) { //result count
                    //show msg
                    showMessage("Error,", "NO DATA!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append(result.getString(0) + ". " +result.getString(1)+ "\n");

                }
                showMessage("List of Players", buffer.toString());
                //show all data
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData(){
        updateBtn.setOnClickListener((View v) -> {
            boolean isUpdate = myDB.updateData(updateEditText.getText().toString(), editNewPlayer.getText().toString(), newSetterCheckBox.getText().toString());
            if  (isUpdate == true) {
                Toast.makeText(PlayerList.this, "Data Updated", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(PlayerList.this,"DATA ERROR. NOT Updated",Toast.LENGTH_LONG).show();


            }
        });

    }
    public void deleteData(){
        deleteBtn.setOnClickListener((View v) -> {
            Integer deletedRows = myDB.deleteData(updateEditText.getText().toString());
            if (deletedRows > 0){
                Toast.makeText(PlayerList.this, "Data Deleted", Toast.LENGTH_LONG).show();
            }else{
            Toast.makeText(PlayerList.this,"DATA ERROR. NOT DELETED",Toast.LENGTH_LONG).show();


        }



        });

    }
    }






