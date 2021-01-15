package com.example.activitychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView area3 = (TextView) findViewById(R.id.area3);
        final EditText text3 = (EditText) findViewById(R.id.text3);
        Button button3 = (Button) findViewById(R.id.button3);

        Intent i = getIntent();
        String text = i.getStringExtra("msg2");
        area3.setText(text);
        text3.setHint(text);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = text3.getText().toString();

                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("msg3" , string);

                startActivity(intent);

            }
        });

    }
}