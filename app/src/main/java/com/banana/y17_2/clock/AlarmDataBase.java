package com.banana.y17_2.clock;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class AlarmDataBase {
    private List<Alarm> mAlarm = new ArrayList<>();
    private Context mContext;


    public void initialize(Context context) {
        this.mContext = context;
    }


    public void load() {
        //TODO
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        final String s = preferences.getString("ALARMS", "");
        final Gson gson = new Gson();
        mAlarm = gson.fromJson(s, new TypeToken<List<Alarm>>(){}.getType());

    }


    public void save() {
        //TODO
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = preferences.edit();
        final Gson gson = new Gson();
        final String s = gson.toJson(mAlarm, new TypeToken<List<Alarm>>(){}.getType());

        editor.putString("ALARMS", s);
        editor.apply();


    }


    public void addAlarm(Alarm alarm) {
        //TODO

        mAlarm.add(alarm);

        save();
    }


    public void removeAlarm(long id) {
        //TODO
        boolean needsSave = false;
        for(int i = 0; i < mAlarm.size(); i++) {
            if(mAlarm.get(i).getId() == id) {
                mAlarm.remove(i);
                needsSave = true;
                break;
            }
        }
        if (needsSave) {
            save();
        }

    }

    public List<Alarm> getmAlarm() {
        return mAlarm;
    }

    public void clear() {
        mAlarm.clear();
    }
}
