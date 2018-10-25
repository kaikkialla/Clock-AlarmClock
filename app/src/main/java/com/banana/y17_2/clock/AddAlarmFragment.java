package com.banana.y17_2.clock;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddAlarmFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    TextView textView;
    int hour;
    int minute;
    List<Alarm> alarms;
    private AlarmDataBase alarmDataBase;
    Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view = inflater.inflate(R.layout.add_alarm_fragment_layout, container, false);
        textView = view.findViewById(R.id.time);
        return view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker

        final Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }



    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        final Adapter adapter = new Adapter((MainActivity) getActivity());
        alarmDataBase = new AlarmDataBase();
        alarmDataBase.initialize(context);
        alarmDataBase.load();

        Log.e("a", "Alarm Added");
        alarms = new ArrayList<>();
        alarmDataBase.addAlarm(new Alarm(1, "Test Alarm", hourOfDay, minute, 1));
        adapter.swap(alarms);

    }

}



