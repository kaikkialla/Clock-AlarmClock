package com.banana.y17_2.clock;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AlarmClockFragment extends Fragment {

    BottomNavigationView bottomNavigationView;
    private AlarmDataBase alarmDataBase;
    List<Alarm> alarms;
    ImageView AddAlarmButton;
    String DIALOG_TIME = "a";
    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Context context = getContext();
        View view = inflater.inflate(R.layout.alarm_clock_fragment, container, false);
        bottomNavigationView = view.findViewById(R.id.bottom_navigation_view);
        AddAlarmButton = view.findViewById(R.id.Add_Alarm_Button);


        adapter = new Adapter((MainActivity) getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));


        alarmDataBase = new AlarmDataBase();
        alarmDataBase.initialize(context);
        alarmDataBase.setOnChangeListener(new AlarmDataBase.ChangeListener() {
            @Override
            public void onChange(List<Alarm> alarms) {
                adapter.swap(alarms);
            }
        });

        AddAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, String.valueOf(System.currentTimeMillis()), Toast.LENGTH_SHORT).show();
                Log.e("current", String.valueOf(System.currentTimeMillis()));
                //alarmDataBase.addAlarm(new Alarm(5, "Test Alarm 1", 5, 12, 7));
/*
                DialogFragment newFragment  = new AddAlarmFragment();
                //newFragment.show(getActivity().getFragmentManager(), DIALOG_TIME);
                FragmentManager fragmentManager = getFragmentManager();
                newFragment.show(fragmentManager, DIALOG_TIME);
*/
            }
        });

        alarms = new ArrayList<>();
        alarms.add(new Alarm(1, "Test Alarm 1", 6, 07, 1));
        alarms.add(new Alarm(2, "Test Alarm 2", 7,00, 2));
        alarms.add(new Alarm(3, "Test Alarm 3", 23,34, 3));
        alarms.add(new Alarm(4, "Test Alarm 4", 17, 00,4));
        adapter.swap(alarms);


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

/*
    @Override
    public void onResume() {
        super.onResume();
        alarmDataBase.setOnChangeListener(new AlarmDataBase.ChangeListener() {
            @Override
            public void onChange(List<Alarm> alarms) {
                adapter.swap(alarms);
            }
        });
        adapter.swap(alarmDataBase.getAlarm());
    }
*/

}

