package com.example.resistrationfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class Registration extends Fragment {

    EditText nm;
    EditText phn;
    EditText email;
    EditText pass1;
    EditText pass2;

    public Registration() {
        // Required empty public constructor
    }

    public static Registration newInstance() {
        Registration fragment = new Registration();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rt = inflater.inflate(R.layout.fragment_registration, container, false);
        nm = (EditText) rt.findViewById(R.id.edit_name);
        phn = (EditText) rt.findViewById(R.id.edit_phone);
        email = (EditText) rt.findViewById(R.id.edit_email);
        pass1 = (EditText) rt.findViewById(R.id.edit_password);
        pass2 = (EditText) rt.findViewById(R.id.edit_password2);
        return rt;
    }
}