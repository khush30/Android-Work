package com.example.simplechatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Chat2 extends AppCompatActivity {

    EditText tempText;

    ImageButton send;
    ImageView dp;

    AppCompatImageButton change;

    TextView pnm;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);


        pnm = (TextView) findViewById(R.id.personName);
        pnm.setText("J-Hope");
        //dp = (ImageView) findViewById(R.id.dp);
        //dp.setImageResource(R.drawable.cat1);
        change = (AppCompatImageButton) findViewById(R.id.change);

        recyclerView = (RecyclerView)findViewById(R.id.chatArea);
        //recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        //pass the values here
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getApplicationContext() , ThreadClass.thread , false);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);

        send = (ImageButton) findViewById(R.id.send_button);

        tempText = (EditText) findViewById(R.id.edit_text);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tempText.getText().toString().length() > 0) {
                    TextClass textThread = new TextClass();

                    textThread.setText(tempText.getText().toString());
                    textThread.setType(false);
                    ThreadClass.thread.add(textThread);
                    tempText.setText(null);
                }

            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext() , Chat1.class);
                startActivity(intent);

            }
        });

    }
}