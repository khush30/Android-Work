package com.example.detailsform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ImageButton addPhoto;

    TextView personName , phoneNo , emailId , password , password2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPhoto = (ImageButton)findViewById(R.id.imageButton);

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext() , "Not included yet" , Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}