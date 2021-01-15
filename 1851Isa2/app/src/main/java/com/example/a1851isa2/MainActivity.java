package com.example.a1851isa2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<CharacterInfo> season1 , season2 , season5 , season3 ,season4;

    ListView list1 , list2 , list3 , list4 , list5;

    Button t1 , t2 , t3 , t4 , t5;

    Boolean f1 , f2 , f3 , f4 , f5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        season1 = new ArrayList<>();
        season2 = new ArrayList<>();
        season3 = new ArrayList<>();
        season4 = new ArrayList<>();
        season5 = new ArrayList<>();

        list1 = (ListView) findViewById(R.id.season1);
        list2 = (ListView) findViewById(R.id.season2);
        list3 = (ListView) findViewById(R.id.season3);
        list4 = (ListView) findViewById(R.id.season4);
        list5 = (ListView) findViewById(R.id.season5);

        t1 = (Button) findViewById(R.id.t1);
        t2 = (Button) findViewById(R.id.t2);
        t3 = (Button) findViewById(R.id.t3);
        t4 = (Button) findViewById(R.id.t4);
        t5 = (Button) findViewById(R.id.t5);

        f1 = f2 = f3 = f4 = f5 = false;

        Async async = new Async(getApplicationContext() , season1 , season2 , season3 , season4 , season5);
        async.execute();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f1 == false){
                    MyAdapter adapter1 = new MyAdapter(getApplicationContext() , android.R.layout.simple_list_item_1 , season1 );
                    list1.setAdapter(adapter1);
                    Toast.makeText(getApplicationContext() , "Breaking Bad Season 1 Cast" , Toast.LENGTH_SHORT).show();
                    list1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.GONE);
                    t3.setVisibility(View.GONE);
                    t4.setVisibility(View.GONE);
                    t5.setVisibility(View.GONE);
                    f1 = true;
                }
                else {
                    list1.setVisibility(View.GONE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    f1 = false;
                }
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f2 == false){
                    MyAdapter adapter2 = new MyAdapter(getApplicationContext() , android.R.layout.simple_list_item_1 , season2 );
                    list2.setAdapter(adapter2);
                    Toast.makeText(getApplicationContext() , "Breaking Bad Season 2 Cast" , Toast.LENGTH_SHORT).show();
                    list2.setVisibility(View.VISIBLE);
                    t1.setVisibility(View.GONE);
                    t3.setVisibility(View.GONE);
                    t4.setVisibility(View.GONE);
                    t5.setVisibility(View.GONE);
                    f2 = true;
                }
                else {
                    list2.setVisibility(View.GONE);
                    t1.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    f2 = false;
                }
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f3 == false){
                    MyAdapter adapter3 = new MyAdapter(getApplicationContext() , android.R.layout.simple_list_item_1 , season3 );
                    list3.setAdapter(adapter3);
                    Toast.makeText(getApplicationContext() , "Breaking Bad Season 3 Cast" , Toast.LENGTH_SHORT).show();
                    list3.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.GONE);
                    t1.setVisibility(View.GONE);
                    t4.setVisibility(View.GONE);
                    t5.setVisibility(View.GONE);
                    f3 = true;
                }
                else {
                    list3.setVisibility(View.GONE);
                    t2.setVisibility(View.VISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    f3 = false;
                }
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f4 == false){
                    MyAdapter adapter4 = new MyAdapter(getApplicationContext() , android.R.layout.simple_list_item_1 , season4 );
                    list4.setAdapter(adapter4);
                    Toast.makeText(getApplicationContext() , "Breaking Bad Season 4 Cast" , Toast.LENGTH_SHORT).show();
                    list4.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.GONE);
                    t3.setVisibility(View.GONE);
                    t1.setVisibility(View.GONE);
                    t5.setVisibility(View.GONE);
                    f4 = true;
                }
                else {
                    list4.setVisibility(View.GONE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    t5.setVisibility(View.VISIBLE);
                    f4 = false;
                }
            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f5 == false){
                    MyAdapter adapter5 = new MyAdapter(getApplicationContext() , android.R.layout.simple_list_item_1 , season5 );
                    list5.setAdapter(adapter5);
                    Toast.makeText(getApplicationContext() , "Breaking Bad Season 5 Cast" , Toast.LENGTH_SHORT).show();
                    list5.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.GONE);
                    t3.setVisibility(View.GONE);
                    t4.setVisibility(View.GONE);
                    t1.setVisibility(View.GONE);
                    f5 = true;
                }
                else {
                    list5.setVisibility(View.GONE);
                    t2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.VISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    f5 = false;
                }
            }
        });

    }

    //adapter to display the cast under respective season
    private class MyAdapter extends ArrayAdapter<CharacterInfo> {

        ArrayList<CharacterInfo> cast;
        Context context;

        public MyAdapter(Context applicationContext, int simple_list_item_1, ArrayList<CharacterInfo> season) {
            super(applicationContext , simple_list_item_1 , season);
            this.context = applicationContext;
            this.cast = season;
        }

        @Override
        public View getView(int position , View convertView , ViewGroup parent){
            ViewHolder tempViewHolder = null;
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if(convertView == null)
                convertView = inflater.inflate(R.layout.cast_record , null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.castName);
            viewHolder.cid = (TextView) convertView.findViewById(R.id.castId);
            viewHolder.nick = (TextView) convertView.findViewById(R.id.castNickname);
            viewHolder.dob = (TextView) convertView.findViewById(R.id.castDob);
            viewHolder.status = (TextView) convertView.findViewById(R.id.castStatus);
            viewHolder.portrayed = (TextView) convertView.findViewById(R.id.castPortrayed);
            viewHolder.occupation = (TextView) convertView.findViewById(R.id.castOccupation);
            viewHolder.castImg = (ImageView) convertView.findViewById(R.id.castImage);
            viewHolder.edited = (EditText) convertView.findViewById(R.id.editNickname);
            viewHolder.done = (Button) convertView.findViewById(R.id.done);
            viewHolder.edit = (ImageButton) convertView.findViewById(R.id.edit);
            viewHolder.area = (RelativeLayout) convertView.findViewById(R.id.editArea);
            viewHolder.info = (LinearLayout) convertView.findViewById(R.id.info);

            System.out.println("here here" + cast.get(position).char_id);
            viewHolder.name.setText(cast.get(position).char_name);
//            viewHolder.cid.setText(cast.get(position).char_id);
            viewHolder.nick.setText(" " + cast.get(position).nickname);
            viewHolder.dob.setText(" " + cast.get(position).birthday);
            viewHolder.status.setText(" " + cast.get(position).status);
            viewHolder.portrayed.setText(" " + cast.get(position).portrayed);

            String occupations = "";

            for (int i = 0 ; i < cast.get(position).occupations.size() - 1; i++)
                occupations += cast.get(position).occupations.get(i) + "\n";
            occupations += cast.get(position).occupations.get(cast.get(position).occupations.size() - 1);
            viewHolder.occupation.setText(occupations);

            try{

                URL url = new URL(cast.get(position).imageUrl);
                Glide.with(context).load(url).into(viewHolder.castImg);

            }catch (Exception e){

            }

                viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext() , "You can only edit the nickname!" , Toast.LENGTH_SHORT).show();

                        viewHolder.area.setVisibility(View.VISIBLE);
                        viewHolder.edited.setVisibility(View.VISIBLE);
                        viewHolder.done.setVisibility(View.VISIBLE);

                        viewHolder.edited.setHint(cast.get(position).nickname);

                        viewHolder.edit.setVisibility(View.INVISIBLE);
                        viewHolder.info.setVisibility(View.GONE);

                        viewHolder.done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(viewHolder.edited.getText().toString().length() == 0)
                                    Toast.makeText(getApplicationContext() , "No Changes Made!" , Toast.LENGTH_SHORT).show();
                                else
                                    cast.get(position).nickname = viewHolder.edited.getText().toString();

                                viewHolder.done.setVisibility(View.GONE);
                                viewHolder.edited.setText(null);
                                viewHolder.edited.setVisibility(View.GONE);
                                viewHolder.edit.setVisibility(View.VISIBLE);
                                viewHolder.area.setVisibility(View.GONE);
                                viewHolder.info.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                });


            return convertView;
        }

    }

    public class ViewHolder{

        TextView cid , name , nick , dob , status , portrayed , occupation;
        ImageView castImg;
        ImageButton edit;
        EditText edited;
        RelativeLayout area;
        LinearLayout info;
        Button done;

    }
}