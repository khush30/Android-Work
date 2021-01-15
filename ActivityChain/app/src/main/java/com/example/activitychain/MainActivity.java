package com.example.activitychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView area1 = (TextView) findViewById(R.id.area1);
        final EditText text1 = (EditText) findViewById(R.id.text1);
        Button button1 = (Button) findViewById(R.id.button1);

        Intent i = getIntent();
        String text = i.getStringExtra("msg3");
        area1.setText(text);
        text1.setHint(text);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = text1.getText().toString();

                Intent intent = new Intent(getApplicationContext() , MainActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("msg1" , string);

                startActivity(intent);

            }
        });
    }
}