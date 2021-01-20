package com.example.a1851androidsea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button rec , death;

    ArrayList<Stats> states;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        states = new ArrayList<Stats>();

        Async async = new Async(getApplicationContext() , states);
        Log.i("hmm" , "hmmm");
        async.execute();
        Log.i("hmm" , "should be done");

        rec = (Button) findViewById(R.id.recoveryButton);
        death = (Button) findViewById(R.id.deathRateButton);

        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Recovery.class));
            }
        });

        death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Death.class));
            }
        });

    }
}