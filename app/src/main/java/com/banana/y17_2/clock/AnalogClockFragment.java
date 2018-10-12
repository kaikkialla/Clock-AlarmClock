package com.banana.y17_2.clock;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.TimeZone;

public class AnalogClockFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getContext();
        View view = inflater.inflate(R.layout.analog_clock_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnalogClockView clockView1 = view.findViewById(R.id.analogClockView1);
        clockView1.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        AnalogClockView clockView2 = view.findViewById(R.id.analogClockView2);
        clockView2.setTimeZone(TimeZone.getTimeZone("GMT-8"));
    }
}
