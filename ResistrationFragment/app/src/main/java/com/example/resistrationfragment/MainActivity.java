package com.example.resistrationfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBaseHelper(this);

        TextView title = (TextView) findViewById(R.id.pageTitle);
        title.setText("Registration");

        AppCompatImageButton prev = (AppCompatImageButton) findViewById(R.id.prev);
        prev.setVisibility(View.INVISIBLE);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        ViewPager viewPager = (ViewPager) findViewById(R.id.myArea);

        FragmentManager fragmentManager = getSupportFragmentManager();

        MyPagerAdapter fragmentPagerAdapter = new MyPagerAdapter(fragmentManager);

        fragmentPagerAdapter.addFragment(Registration.newInstance() , "Form 1");
        fragmentPagerAdapter.addFragment(Details.newInstance() , "Form 2");

        viewPager.setAdapter(fragmentPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        //POJO example
        //final ProfilePojo profile = new ProfilePojo();


        AppCompatImageButton button = (AppCompatImageButton) findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RelativeLayout part1 = (RelativeLayout) findViewById(R.id.part1);
                RelativeLayout part2 = (RelativeLayout) findViewById(R.id.part2);



                TextInputEditText nm , phn , eml , pswd1 , pswd2 , dob;

                TextView unm;

                RadioButton gender;
                RadioGroup gen;

                Spinner dist;

                ProfilePojo user = new ProfilePojo();

                nm = (TextInputEditText) part1.findViewById(R.id.edit_name);
                phn = (TextInputEditText) part1.findViewById(R.id.edit_phone);
                eml = (TextInputEditText) part1.findViewById(R.id.edit_email);
                pswd1 = (TextInputEditText) part1.findViewById(R.id.edit_password);
                pswd2 = (TextInputEditText) part1.findViewById(R.id.edit_password2);
                unm = (TextView) part2.findViewById(R.id.nameArea);
                gen = (RadioGroup) part2.findViewById(R.id.radioButton);
                dist = (Spinner) part2.findViewById(R.id.district);
                dob = (TextInputEditText) part2.findViewById(R.id.dob);

                unm.setText(nm.getText().toString());
                //POJO example part
                /*
                pswd1.setText(profile.password);
                nm.setText(profile.username);
                phn.setText(profile.phoneNumber);
                eml.setText(profile.emailId);
                pswd2.setText(profile.password);
                unm.setText(profile.username);
                if(profile.gender == "female")
                    gender = (RadioButton) part2.findViewById(R.id.f);
                else if(profile.gender == "male")
                    gender = (RadioButton) part2.findViewById(R.id.m);
                else
                    gender = (RadioButton) part2.findViewById(R.id.o);
                gender.setChecked(true);
                 if(profile.district == "North Goa")
                    dist.setSelection(0);
                else
                    dist.setSelection(1);
                dob.setText(profile.date);
                 */

                if(nm.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext() , "Please Enter The Name!" , Toast.LENGTH_SHORT).show();
                else if(eml.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext() , "Please Enter The Email Id" , Toast.LENGTH_SHORT).show();
                else if(pswd1.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext() , "Please Enter The Password" , Toast.LENGTH_SHORT).show();
                else if(pswd2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext() , "Please Re-Enter The Password in 2nd Field" , Toast.LENGTH_SHORT).show();
                else if(!pswd1.getText().toString().equals(pswd2.getText().toString()))
                    Toast.makeText(getApplicationContext() , "Passwords do not match!" , Toast.LENGTH_SHORT).show();
                else{
                    user.username = nm.getText().toString();
                    user.emailId = eml.getText().toString();
                    user.phoneNumber = phn.getText().toString();
                    user.district = dist.getSelectedItem().toString();
                    user.date = dob.getText().toString();
                    Toast.makeText(getApplicationContext() , user.date , Toast.LENGTH_SHORT).show();
                    gender = (RadioButton) part2.findViewById(gen.getCheckedRadioButtonId());

                    user.gender = gender.getText().toString();
                    user.password = pswd1.getText().toString();


                    if(db.insertInUsers(user)) {
                        Toast.makeText(getApplicationContext(), "User Registered Successfully!", Toast.LENGTH_SHORT).show();
                        Cursor cursor = db.getUsersData();
                        if (cursor.getCount() == 0){
                            Toast.makeText(getApplicationContext() , "No users registered!" , Toast.LENGTH_SHORT).show();
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        if(cursor.moveToLast()){
                            buffer.append("\n" + cursor.getString(0) + "\n");
                            buffer.append(cursor.getString(1) + "\n");
                            buffer.append(cursor.getString(2) + "\n");
                            buffer.append(cursor.getString(3) + "\n");
                            buffer.append(cursor.getString(5) + "\n");
                            buffer.append(cursor.getString(6) + "\n");
                            buffer.append(cursor.getString(7) + "\n");
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("Registered User:");
                        builder.setMessage(buffer.toString());
                        builder.show();
                    }else
                        Toast.makeText(getApplicationContext() , "User Registration Failed!" , Toast.LENGTH_SHORT).show();

                }



            }
        });

        AppCompatImageButton button2 = (AppCompatImageButton) findViewById(R.id.done);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*


 */


                Intent i = new Intent(MainActivity.this , MainActivity2.class);
                startActivity(i);

            }
        });


    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> stringList = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringList.get(position);
        }

        public void addFragment(Fragment fragment , String title){
            fragmentList.add(fragment);
            stringList.add(title);
        }

    }
}