package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Score_board extends AppCompatActivity implements AsyncResponse {

    MyRecyclerViewAdapter adapter;
    ListView listView;
    Button back_button;
    Scores[] game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        listView = (ListView) findViewById(R.id.listView);

        back_button=findViewById(R.id.back_buttton);

        GetScore getScore = new GetScore(this);

        getScore.execute();

        getScore.score = this;
        MyAdapter myAdapter = new MyAdapter(this , android.R.layout.simple_list_item_1 , game);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Score_board.this, "exiting..", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Score_board.this,MainPage.class);
                startActivity(intent);
                //finish();
            }
        });



        //recyclerView=findViewById(R.id.recyclerView);


        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

    }

    @Override
    public void processFinish(String output) {
//        Toast.makeText(this, "scoreboard"+output, Toast.LENGTH_SHORT).show();

        int i, j = 0 , k=0;
        String scoredata[] = new String[4];
        for( i = 0 ; i < output.length() ; i++)
        {
            if(output.charAt(i) == '$'){
//                Log.i("loop" , userData.substring(k , i));
                scoredata[j] = output.substring(k , i);
                j++;
                k = i+1;
            }
        }
        scoredata[j] = output.substring(k , output.length());

        i=0;

        game = new Scores[scoredata.length];
        String temp[] = new String[3];
        while(scoredata[i] != null){

            Log.e("score 222",scoredata[i]);
            temp = scoredata[i].split("/");

            game[i].username = temp[0];
            game[i].level = Integer.parseInt(temp[2]);
            game[i].score = Long.parseLong(temp[1]);
            i++;

        }

        // set up the RecyclerView
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new MyRecyclerViewAdapter(this,game );
//        recyclerView.setAdapter(adapter);

    }

    private class MyAdapter extends ArrayAdapter<Scores> {

        Scores[] game;
        Context context;

        public MyAdapter(Context context, int simple_list_item_1, Scores[] game) {

            super(context , simple_list_item_1 , game);
            this.context = context;
            this.game = game;

        }

        @Override
        public View getView(int position , View convertView , ViewGroup parent){
            ViewHolder temp = null;
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if(convertView == null)
                convertView = inflater.inflate(R.layout.eachuser_score , null);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.username = (TextView) convertView.findViewById(R.id.scusername);
            viewHolder.scores = (TextView) convertView.findViewById(R.id.scscore);
            viewHolder.level = (TextView) convertView.findViewById(R.id.sclevels);

            viewHolder.username.setText(game[position].username);
            viewHolder.level.setText(game[position].level);
            viewHolder.scores.setText((int) game[position].score);

            return convertView;

        }

    }

    public class ViewHolder{

        TextView username , level , scores;

    }

}