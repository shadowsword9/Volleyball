package com.example.volleyball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addPlayerBtn = findViewById(R.id.addPlayer);
        Button calc = findViewById(R.id.calculate);

        addPlayerBtn.setOnClickListener(c->{
            Intent nextPage = new Intent(MainActivity.this, PlayerList.class );
            startActivity(nextPage);
        });

        calc.setOnClickListener(c->{
            Intent nextPage = new Intent(MainActivity.this, Calculate.class );
            startActivity(nextPage);
        });
    }
}
