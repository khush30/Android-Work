package com.example.listviewwithsnackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editName;
    Button change;
    ImageButton refresh;

    ArrayList<PersonalDetails> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        names.add(new PersonalDetails("Abhishek" , false));
        names.add(new PersonalDetails("Adam" , false));
        names.add(new PersonalDetails("Abigail" , true));
        names.add(new PersonalDetails("Abigail" , true));
        names.add(new PersonalDetails("Larry" , false));
        names.add(new PersonalDetails("Marvin" , false));
        names.add(new PersonalDetails("Charley" , false));
        names.add(new PersonalDetails("Adele" , true));
        names.add(new PersonalDetails("Hannah" , true));
        names.add(new PersonalDetails("Bella" , true));
        names.add(new PersonalDetails("Abhishek" , false));
        names.add(new PersonalDetails("Briana" , true));
        names.add(new PersonalDetails("Potter" , false));
        names.add(new PersonalDetails("Blair" , true));
        names.add(new PersonalDetails("Elaine" , true));
        names.add(new PersonalDetails("Grace" , true));
        names.add(new PersonalDetails("Stephen" , false));
        names.add(new PersonalDetails("Richard" , false));
        names.add(new PersonalDetails("Rocky" , false));
        names.add(new PersonalDetails("Keith" , false));
        names.add(new PersonalDetails("Charley" , true));
        names.add(new PersonalDetails("Charley" , false));
        names.add(new PersonalDetails("Julius" , false));
        names.add(new PersonalDetails("Lee" , false));
        names.add(new PersonalDetails("Dana" , true));
        names.add(new PersonalDetails("Elisa" , true));
        names.add(new PersonalDetails("Jasmine" , true));
        names.add(new PersonalDetails("Isabella" , true));


        listView = (ListView) findViewById(R.id.listArea);
        editName = (EditText) findViewById(R.id.editName);
        change = (Button) findViewById(R.id.done);
        refresh = (ImageButton) findViewById(R.id.refresh);

        ArrayList<PersonalDetails> tempList = new ArrayList<>();

        for(int i = 0 ; i < names.size() ; i++){
            tempList.add(names.get(i));
        }

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                names.removeAll(names);

                for(int i = 0 ; i < tempList.size() ; i++){
                    names.add(tempList.get(i));
                }

                MyAdapter newListViewAdapter = new MyAdapter(getApplicationContext() , android.R.layout.simple_list_item_1 , /*R.layout.record ,*/ names);

                listView.setAdapter(newListViewAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String gen;
                        if(names.get(position).getGender())
                            gen = "Girl";
                        else
                            gen = "Boy";
                        Toast.makeText(getApplicationContext(), names.get(position).getPersonName() + " is a " + gen , Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        MyAdapter listViewAdapter = new MyAdapter(this , android.R.layout.simple_list_item_1 , /*R.layout.record ,*/ names);

        listView.setAdapter(listViewAdapter);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        long no = calculateAmount(names);
        alertDialogBuilder.setMessage("Number of girls is " + no + "\n\nTotal amount to be spent will be Rs. " + (no * 50000));
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String gen;
                if(names.get(position).getGender())
                    gen = "Girl";
                else
                    gen = "Boy";
                Toast.makeText(getApplicationContext(), names.get(position).getPersonName() + " is a " + gen , Toast.LENGTH_SHORT).show();

            }
        });




    }

    private long calculateAmount(ArrayList<PersonalDetails> names) {

        long noOfGirls = 0;
        for (int i = 0 ; i < names.size() ; i++)
            if(names.get(i).getGender() == true)
                noOfGirls++;

        return noOfGirls;

    }

    private class MyAdapter extends ArrayAdapter<PersonalDetails>{

        public MyAdapter(@NonNull Context context, int simple_list_item_1, List<PersonalDetails> names) {
            super(context, simple_list_item_1 , names);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder tempViewHolder = null;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.record , parent , false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.nm = (TextView) convertView.findViewById(R.id.nm);
                viewHolder.gender = (TextView) convertView.findViewById(R.id.gender);
                viewHolder.delete = (ImageButton) convertView.findViewById(R.id.delete);
                viewHolder.edit = (ImageButton) convertView.findViewById(R.id.edit);
                viewHolder.nm.setText(getItem(position).getPersonName());
                if(getItem(position).getGender())
                    viewHolder.gender.setText("Female");
                else
                    viewHolder.gender.setText("Male");

                viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getApplicationContext() , getItem(position) + " " + position , Toast.LENGTH_SHORT).show();
                        String tempName = getItem(position).getPersonName();
                        Boolean tempGender = getItem(position).getGender();
                        names.remove(position);
                        listView.setAdapter(new MyAdapter(MainActivity.this , android.R.layout.simple_list_item_1 , names));
                        long no = calculateAmount(names);


                        Snackbar.make(listView , tempName + " is being deleted... \nTotal amount :Rs. " + (no * 50000) , Snackbar.LENGTH_LONG)
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

                convertView.setTag(viewHolder);
            }
            else {
                tempViewHolder = (ViewHolder) convertView.getTag();
                tempViewHolder.nm = (TextView) convertView.findViewById(R.id.nm);
                tempViewHolder.gender = (TextView) convertView.findViewById(R.id.gender);
                tempViewHolder.delete = (ImageButton) convertView.findViewById(R.id.delete);
                tempViewHolder.edit = (ImageButton) convertView.findViewById(R.id.edit);
                tempViewHolder.nm.setText(getItem(position).getPersonName());
                if(getItem(position).getGender())
                    tempViewHolder.gender.setText("Female");
                else
                    tempViewHolder.gender.setText("Male");
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

            }

            return convertView;
        }

    }

    public class ViewHolder{

        TextView nm;
        TextView gender;
        ImageButton delete;
        ImageButton edit;

    }

}