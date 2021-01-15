package com.example.activitychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView area2 = (TextView) findViewById(R.id.area2);
        final EditText text2 = (EditText) findViewById(R.id.text2);
        Button button2 = (Button) findViewById(R.id.button2);

        Intent i = getIntent();
        String text = i.getStringExtra("msg1");
        area2.setText(text);
        text2.setHint(text);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = text2.getText().toString();

                Intent intent = new Intent(getApplicationContext() , MainActivity3.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("msg2" , string);

                startActivity(intent);

            }
        });

    }
}