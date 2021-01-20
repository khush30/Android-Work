package com.example.a1851androidsea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class Recovery extends AppCompatActivity {

    ListView list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        list1 = (ListView) findViewById(R.id.recList);

        MyAdapter1 adapter1 = new MyAdapter1(getApplicationContext() , android.R.layout.simple_list_item_1 , List.states , true);
        list1.setAdapter(adapter1);

    }
}