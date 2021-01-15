package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView head;

    private EditText phnNo , emailId , dateOfBirth;

    private Button display;

    private TextView displayArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        head = (TextView)findViewById(R.id.hello);

        phnNo = (EditText) findViewById(R.id.phone);
        emailId = (EditText) findViewById(R.id.email);
        dateOfBirth = (EditText) findViewById(R.id.dob);

        displayArea = (TextView) findViewById((R.id.textView20));

        display = (Button) findViewById(R.id.button2);

        Intent i = getIntent();

        String msg = i.getStringExtra("msg");

        head.setText("Hello "+msg);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Your contacts details are:\n\tEmail id: " + emailId.getText().toString() + "\n\tPhone number: " + phnNo.getText().toString() + "\nDate of birth: " + dateOfBirth.getText().toString();

                displayArea.setText(data);
            }
        });
    }
}