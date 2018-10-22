package com.banana.y17_2.clock;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        viewPager = findViewById(R.id.View_Pager);
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
*/
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout, new AlarmClockFragment(), null).commit();
        }

    }
}
