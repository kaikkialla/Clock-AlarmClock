package com.banana.y17_2.clock;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.util.Calendar;


public class AddAlarmFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    int CurrentHour;
    int CurrentMinute;
    private AlarmDataBase alarmDataBase;
    Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view = inflater.inflate(R.layout.add_alarm_fragment_layout, container, false);
        return view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Default time initially set in dialog
        final Calendar calendar = Calendar.getInstance();
        CurrentHour = calendar.get(Calendar.HOUR_OF_DAY);
        CurrentMinute = calendar.get(Calendar.MINUTE);


        return new TimePickerDialog(getActivity(), this, CurrentHour , CurrentMinute, DateFormat.is24HourFormat(getActivity()));
    }



    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {




    }



}



