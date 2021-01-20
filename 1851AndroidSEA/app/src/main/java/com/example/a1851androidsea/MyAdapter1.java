package com.example.a1851androidsea;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter1 extends ArrayAdapter<Stats> {
    ArrayList<Stats> cast;
    Context context;
    Boolean flag;

    public MyAdapter1(Context applicationContext, int simple_list_item_1, ArrayList<Stats> season, boolean b) {
        super(applicationContext , simple_list_item_1 , season);
        this.context = applicationContext;
        this.cast = season;
        this.flag = b;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.lay , null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.state = (TextView) convertView.findViewById(R.id.state);
        viewHolder.percent = (TextView) convertView.findViewById(R.id.percent);

        if(flag)
        {

            viewHolder.state.setText(cast.get(position).stateName);
            viewHolder.percent.setText(String.valueOf(cast.get(position).recoveryPercent));
            if(cast.get(position).recoveryPercent > 97)
                viewHolder.percent.setTextColor(context.getResources().getColor(R.color.green));
            else
                viewHolder.percent.setTextColor(context.getResources().getColor(R.color.red));

        }
        else
        {
            if(cast.get(position).deathPercent > 1)
            {
                viewHolder.state.setText(cast.get(position).stateName);
                viewHolder.percent.setText(String.valueOf(cast.get(position).deathPercent));
            }
        }
        return convertView;
    }

    public class ViewHolder{

        TextView state , percent;
        Button done;

    }
}
