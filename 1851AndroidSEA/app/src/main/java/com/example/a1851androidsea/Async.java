package com.example.a1851androidsea;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Async extends AsyncTask<Void , Void , Void> {

    Context context;
    ArrayList<Stats> states;

    public Async(Context applicationContext, ArrayList<Stats> states) {
        this.context = applicationContext;
        this.states = states;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        String response = "" , buffer1 = "" , buffer2 = "";

        Log.e("ent" , "Entered");

        try{
            URL url = new URL("https://covid-api.mmediagroup.fr/v1/cases?country=India");
            Log.e("check", "url read");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            Log.e("check", "connection setup");

            try {
               InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                response = convertStreamToString(in);

//                Log.e("2nd try", response);
            }catch (MalformedURLException e) {
                Log.e("try", "MalformedURLException: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e("try", "ProtocolException: " + e.getMessage());
            } catch (IOException e) {
                Log.e("try", "IOException: " + e.getMessage());
            } catch (Exception e) {
                Log.e("try", "Exception: " + e.getMessage());
            }


            JSONObject jsonObject1 = new JSONObject(response);
            JSONObject s1;
            Stats temp;
            Iterator<?> keys = jsonObject1.keys();
            while(keys.hasNext()){
                String key = (String) keys.next();
                if(key.matches("All") || key.matches("Unknown"))
                    continue;
                s1 = jsonObject1.getJSONObject(key);
                temp = new Stats();
                temp.stateName = key;
                temp.latitude = s1.getDouble("lat");
                temp.longitude = s1.getDouble("long");
                temp.confirmed = s1.getDouble("confirmed");
                temp.recovered = s1.getDouble("recovered");
                temp.deaths = s1.getDouble("deaths");
                temp.updated = s1.getString("updated");
                temp.recoveryPercent = temp.recovered * 100 / temp.confirmed;
                temp.deathPercent = temp.deaths * 100 / temp.confirmed;
                List.states.add(temp);
            }

            Collections.sort(List.states, new Comparator<Stats>() {
                @Override
                public int compare(Stats o1, Stats o2) {
                    return o2.recoveryPercent.compareTo(o1.recoveryPercent);
                }
            });



        }catch (Exception e){

            Log.e("catch" , "reading jaina" );

        }

        return null;
    }

    private String convertStreamToString(InputStream in) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();

    }

}
