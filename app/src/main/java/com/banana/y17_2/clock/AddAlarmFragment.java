package com.banana.y17_2.clock;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddAlarmFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Context context = getContext();
        View view = inflater.inflate(R.layout.add_alarm_fragment_layout, container, false);

        return view;
    }
}
