package com.banana.y17_2.clock;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;


public class AlarmClockFragment extends Fragment {

    BottomNavigationView bottomNavigationView;
    CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Context context = getContext();
        View view = inflater.inflate(R.layout.alarm_clock_fragment, container, false);
        bottomNavigationView = view.findViewById(R.id.bottom_navigation_view);
        checkBox = view.findViewById(R.id.Alarm_Clock_Status_Checkbox);
        checkBox.setTag("unchecked");

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.getTag().equals("unchecked")) {
                    checkBox.setTag("checked");
                } else if(checkBox.getTag().equals("checked")){
                    checkBox.setTag("unchecked");
                }
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_sleeplab:
                                break;
                            case R.id.action_alarm:

                                break;
                            case R.id.action_settings:

                                break;
                        }
                        return false;
                    }
                });



        return view;
    }
}
