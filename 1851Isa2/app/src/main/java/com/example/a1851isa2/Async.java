package com.example.a1851isa2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class Async extends AsyncTask<Void , Void , Void> {

    Context context;

    ArrayList<CharacterInfo> season1 , season2 , season3 , season4 , season5;

    public Async(Context applicationContext, ArrayList<CharacterInfo> season1, ArrayList<CharacterInfo> season2, ArrayList<CharacterInfo> season3, ArrayList<CharacterInfo> season4, ArrayList<CharacterInfo> season5) {
        this.context = applicationContext;
        this.season1 = season1;
        this.season2 = season2;
        this.season3 = season3;
        this.season4 = season4;
        this.season5 = season5;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        String buffer1 = "" , buffer2 = "";

        try{

            URL url = new URL("https://www.breakingbadapi.com/api/characters?limit=10");
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            while (buffer1 != null){
                buffer1 = bufferedReader.readLine();
                buffer2 += buffer1;
            }

            JSONArray jsonArray = new JSONArray(buffer2);

            for (int i = 0 ; i < jsonArray.length() ; i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                CharacterInfo tempChar = new CharacterInfo();
                tempChar.char_id = (int) jsonObject.get("char_id");
                tempChar.char_name = jsonObject.getString("name");
                tempChar.birthday = jsonObject.getString("birthday");
                tempChar.status = jsonObject.getString("status");
                tempChar.nickname = jsonObject.getString("nickname");
                tempChar.portrayed = jsonObject.getString("portrayed");
                tempChar.imageUrl = jsonObject.getString("img");
                //jsonObject.get("occupation").toString();
                JSONArray occupatn = new JSONArray(jsonObject.getString("occupation"));
                for(int j = 0 ; j < occupatn.length() ; j++)
                {
                    tempChar.occupations.add(occupatn.get(j).toString());
                }
/*
                JSONArray category = new JSONArray(jsonObject.getString("category"));
                for(int j = 0 ; j < category.length() ; j++)
                {
                    tempChar.category.add(category.get(j).toString());
                }

 */
/*
                JSONArray bcs_seasons = new JSONArray(jsonObject.getString("better_call_saul_appearance"));
                for(int j = 0 ; j < bcs_seasons.length() ; j++)
                {
                    System.out.println("checking season" + bcs_seasons.get(j));

                    tempChar.bcs_seasons.add(bcs_seasons.get(j));


                }

 */

                JSONArray seasons = new JSONArray(jsonObject.getString("appearance"));
                for(int j = 0 ; j < seasons.length() ; j++)
                {
                    if (seasons.get(j).equals(1))
                        season1.add(tempChar);
                    else if (seasons.get(j).equals(2))
                        season2.add(tempChar);
                    else if(seasons.get(j).equals(3))
                        season3.add(tempChar);
                    else if(seasons.get(j).equals(4))
                        season4.add(tempChar);
                    else
                        season5.add(tempChar);
                }

            }

            for (int i = 0 ; i < season1.size() ; i++)
                System.out.println(season1.get(i).char_id + " " + season1.get(i).char_name + " " + season1.get(i).birthday + "\n" + season1.get(i).imageUrl);

        }catch (Exception e){

        }

        return null;
    }
}
