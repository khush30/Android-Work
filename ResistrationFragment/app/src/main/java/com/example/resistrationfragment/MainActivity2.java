package com.example.resistrationfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView title = (TextView) findViewById(R.id.pageTitle);
        title.setText("Registered Users");

        AppCompatImageButton save , done , prev;

        prev = (AppCompatImageButton) findViewById(R.id.prev);
        save = (AppCompatImageButton) findViewById(R.id.save);
        done = (AppCompatImageButton) findViewById(R.id.done);

        save.setVisibility(View.INVISIBLE);
        done.setVisibility(View.INVISIBLE);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this , MainActivity.class));
            }
        });

        Cursor cursor = MainActivity.db.getUsersData();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext() , "No users registered!" , Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<ProfilePojo> users = new ArrayList<>();
        ProfilePojo tempUser = new ProfilePojo();

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            tempUser.username = cursor.getString(0);
            tempUser.gender = cursor.getString(3);
            tempUser.date = cursor.getString(6);
            tempUser.phoneNumber = cursor.getString(1);
            tempUser.district = cursor.getString(5);
            tempUser.emailId = cursor.getString(2);

            buffer.append("Name    : " + cursor.getString(0) + "\n");
            buffer.append("Gender  : " + cursor.getString(3) + "\n");
            buffer.append("DOB      : " + cursor.getString(6) + "\n");
            buffer.append("Contact : " + cursor.getString(1) + "\n");
            buffer.append("Email id : " + cursor.getString(2) + "\n");
            buffer.append("Distract : " + cursor.getString(5) + "\n");
            buffer.append("----------------------\n\n");
            users.add(tempUser);
        }



        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setCancelable(true);
        builder.setTitle("Registered Users:");
        builder.setMessage(buffer.toString());
        builder.show();

        MyAdapter listViewAdapter = new MyAdapter(this , android.R.layout.simple_list_item_1 , users);

    }

    private class MyAdapter extends ArrayAdapter<ProfilePojo> {

        public MyAdapter(@NonNull Context context, int simple_list_item_1, List<ProfilePojo> names) {
            super(context, simple_list_item_1 , names);
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder tempViewHolder = null;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.record , parent , false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.nm = (TextView) convertView.findViewById(R.id.username);
                viewHolder.delete = (ImageButton) convertView.findViewById(R.id.deleteRecord);
                viewHolder.edit = (ImageButton) convertView.findViewById(R.id.editRecord);
                viewHolder.nm.setText(getItem(position).username);

                /*
                viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getApplicationContext() , getItem(position) + " " + position , Toast.LENGTH_SHORT).show();
                        String tempName = getItem(position).username;
                        names.remove(position);
                        listView.setAdapter(new MyAdapter(MainActivity2.this , android.R.layout.simple_list_item_1 , names));


                    }
                });

                viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext() , "You can only edit the name!" , Toast.LENGTH_SHORT).show();

                        editName.setVisibility(View.VISIBLE);
                        change.setVisibility(View.VISIBLE);

                        editName.setHint(getItem(position).getPersonName());

                        listView.setVisibility(View.INVISIBLE);

                        change.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(editName.getText().toString().length() == 0)
                                    Toast.makeText(getApplicationContext() , "No Changes Made!" , Toast.LENGTH_SHORT).show();
                                else
                                    getItem(position).setPersonName(editName.getText().toString());

                                change.setVisibility(View.GONE);
                                editName.setText(null);
                                editName.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                });

                 */

                convertView.setTag(viewHolder);
            }
            else {
                tempViewHolder = (ViewHolder) convertView.getTag();
                tempViewHolder.nm = (TextView) convertView.findViewById(R.id.username);
                tempViewHolder.delete = (ImageButton) convertView.findViewById(R.id.deleteRecord);
                tempViewHolder.edit = (ImageButton) convertView.findViewById(R.id.editRecord);
                tempViewHolder.nm.setText(getItem(position).username);
               /*
                tempViewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tempName = getItem(position).getPersonName();
                        Boolean tempGender = getItem(position).getGender();
                        names.remove(position);

                        listView.setAdapter(new MyAdapter(MainActivity.this , android.R.layout.simple_list_item_1 , names));
                        long no = calculateAmount(names);
                        Toast.makeText(getApplicationContext() , "Number of girls is " + no + "\n\nTotal amount: Rs. " + (no * 50000) , Toast.LENGTH_LONG);


                        Snackbar.make(listView , tempName + " is being deleted... \nTotal amount: Rs. " + (no * 50000) , Snackbar.LENGTH_LONG)
                                .setAction("UNDO", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        names.add(position , new PersonalDetails(tempName , tempGender));
                                        listView.setAdapter(new MyAdapter(MainActivity.this , android.R.layout.simple_list_item_1 , names));
                                        long no = calculateAmount(names);
                                        Snackbar.make(listView , tempName + " is restored! \nTotal amount to be spent will be Rs. " + (no * 50000) , Snackbar.LENGTH_SHORT).show();
                                    }
                                })
                                .show();

                    }
                });

                tempViewHolder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext() , "You can only edit the name!" , Toast.LENGTH_SHORT).show();

                        editName.setVisibility(View.VISIBLE);
                        change.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.INVISIBLE);

                        editName.setHint(getItem(position).getPersonName());

                        change.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(editName.getText().toString().length() == 0)
                                    Toast.makeText(getApplicationContext() , "No Changes Made!" , Toast.LENGTH_SHORT).show();
                                else
                                    getItem(position).setPersonName(editName.getText().toString());

                                change.setVisibility(View.GONE);
                                editName.setText(null);
                                editName.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                });

                */

            }

            return convertView;
        }

    }

    public class ViewHolder{

        TextView nm;
        ImageButton delete;
        ImageButton edit;

    }

}