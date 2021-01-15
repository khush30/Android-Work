package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    Button red , green , yellow , blue;

    LinearLayout layout;

    ScrollView scroll;

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout) findViewById(R.id.display_area);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        red = (Button) findViewById(R.id.red);
        green = (Button) findViewById(R.id.green);
        blue = (Button) findViewById(R.id.blue);
        yellow = (Button) findViewById(R.id.yellow);

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tile = getLayoutInflater().inflate(R.layout.red_tile , null);

                layout.addView(tile);

            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tile = getLayoutInflater().inflate(R.layout.blue_tile , null);

                layout.addView(tile);

            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tile = getLayoutInflater().inflate(R.layout.green_tile , null);

                layout.addView(tile);

            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tile = getLayoutInflater().inflate(R.layout.yellow_tile, null);

                layout.addView(tile);

            }
        });

    }
}