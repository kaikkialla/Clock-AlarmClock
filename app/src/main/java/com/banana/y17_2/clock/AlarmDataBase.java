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

        if(changeListener != null) {
            changeListener.onChange(mAlarm);
        }

    }


    public void save() {
        //TODO
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = preferences.edit();
        final Gson gson = new Gson();
        final String s = gson.toJson(mAlarm, new TypeToken<List<Alarm>>(){}.getType());

        editor.putString("ALARMS", s);
        editor.apply();

        if(changeListener != null) {
            changeListener.onChange(mAlarm);
        }


    }

    private ChangeListener changeListener = null;

    interface ChangeListener{
        void onChange(List<Alarm> alarms);
    }

    public void setOnChangeListener(ChangeListener listener) {
        changeListener = listener;
    }



    public void addAlarm(Alarm alarm) {
        mAlarm.add(alarm);

        if(changeListener != null) {
            changeListener.onChange(mAlarm);
        }

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

        if(changeListener != null) {
            changeListener.onChange(mAlarm);
        }
    }


    public List<Alarm> getAlarm() {
        return mAlarm;
    }


    public void clear() {
        mAlarm.clear();
        if(changeListener != null) {
            changeListener.onChange(mAlarm);
        }
    }



    
}
