package com.example.webclient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    static TextView textView;
    Button show;
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList arrayList = new ArrayList();


        textView = (TextView) findViewById(R.id.listview);
        show = (Button) findViewById(R.id.show);

        Async async = new Async();
        async.execute();
        /*
        String text = "";
        for (int i = 0 ; i < async.arrayList.size() ; i++)
            text += arrayList.get(i);
        */

        //textView.setText(async.data);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "Total salary to be paid: Rs. " + async.salary.toString() + "/-", Toast.LENGTH_SHORT).show();
            }
        });


       // MyAdapter listViewAdapter = new MyAdapter(this , android.R.layout.simple_list_item_1 , records);

        //listView.setAdapter(listViewAdapter);

    }
    public static class Async extends AsyncTask<Void, Void, Void> {
        String data="";
        Double salary=0.0;
        ArrayList arrayList = new ArrayList();

        @Override
        protected Void doInBackground(Void... voids) {
            String buffer = "",line="";
            Double salaryObject ;

            try {
                URL url = new URL("https://api.jsonbin.io/b/5f98044c30aaa01ce619982d");
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                while(line!=null){
                    line = bufferedReader.readLine();
                    buffer += line;
                }
                JSONArray jsonArray  = new JSONArray(buffer);

                for(int i= 0; i<jsonArray.length();i++){
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    arrayList.add( "Employee Code:\t" + jsonObject.get("empCode") + "\nFirst Name:\t" + jsonObject.get("firstName") + "\nLast Name:\t" + " " + jsonObject.get("lastName") + "\n" + "Salary:\t\t" + jsonObject.get("salary")+"\n");

                    salaryObject = jsonObject.getDouble("salary");
                    salary += salaryObject;
                }
                Collections.sort(arrayList);
                for(int i=0; i<arrayList.size(); i++){
                    data= data+ arrayList.get(i) + "\n";
                }
            } catch (Exception e){
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MainActivity.textView.setText(this.data);
        }


    }

}