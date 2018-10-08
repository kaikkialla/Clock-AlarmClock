package com.banana.y17_2.clock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout, new AnalogClockFragment(), null).commit();
        }
    }
}
