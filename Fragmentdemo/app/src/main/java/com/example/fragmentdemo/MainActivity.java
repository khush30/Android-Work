package com.example.fragmentdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager;

        TabLayout tabs;


        viewPager = (ViewPager) findViewById(R.id.myArea);

        tabs = (TabLayout) findViewById(R.id.tabs);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentPagerAdapter fragmentPagerAdapter = new MyPagerAdapter(fragmentManager);

        viewPager.setAdapter(fragmentPagerAdapter);

    }

    static class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            if(position == 0)
                return Registration.newInstance();
            else if (position == 1 )
                return Details.newInstance();

            return null;

        }

        @Override
        public int getCount() {
            return 2 ;
        }
    }
}