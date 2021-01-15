package com.example.resistrationfragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class Details extends Fragment {

    public Details() {
        // Required empty public constructor
    }

    public static Details newInstance() {
        Details fragment = new Details();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    Spinner spinner;

    EditText dob;

    Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rt = inflater.inflate(R.layout.fragment_details, container, false);

        spinner = (Spinner) rt.findViewById(R.id.district);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext() , R.array.districts , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //spinner.setOnItemSelectedListener();

        dob = (EditText) rt.findViewById(R.id.dob);

        calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth) {

                        String date = dayOfMonth + "/" + (month1+1) + "/" + year1;
                        dob.setText(date);

                    }
                }, year , month , day);

                datePickerDialog.show();

            }
        });

        return rt;
    }



}