package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button go_to_next;
    private EditText nme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go_to_next = (Button) findViewById(R.id.next);
        nme = (EditText) findViewById(R.id.name_to_be_passed);

        go_to_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(MainActivity.this , MainActivity2.class);

                String msg = nme.getText().toString();

                Intent i = new Intent(getApplicationContext() , MainActivity2.class);
                i.putExtra("msg" , msg);
                startActivity(i);
            }
        });
    }
}