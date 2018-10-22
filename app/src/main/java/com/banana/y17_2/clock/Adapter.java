package com.banana.y17_2.clock;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    MainActivity activity;
    private List<Alarm> mAlarm = new ArrayList<>();

    public Adapter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.recycler_view_row, parent, false );
        ViewHolder vh = new ViewHolder(v);


        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Alarm alarm = mAlarm.get(position);
        viewHolder.AlarmClockDay.setText(String.valueOf(alarm.getDay()));

        if (alarm.getMinute() == 00) {
            viewHolder.AlarmClockTime.setText(alarm.getHour() + ":00" );
        } else viewHolder.AlarmClockTime.setText(alarm.getHour() + ":" + alarm.getMinute());

    }


    @Override
    public int getItemCount() {
        return 4;

    }

    public void swap(List<Alarm> alarm) {
        if (alarm != null) {
            mAlarm.clear();
            mAlarm.addAll(alarm);

            notifyDataSetChanged();
        }
    }




}
