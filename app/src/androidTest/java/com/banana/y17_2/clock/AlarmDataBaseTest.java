package com.banana.y17_2.clock;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AlarmDataBaseTest {

    @Test
    public void addAlarms() {
        final Context context = InstrumentationRegistry.getContext();
        final AlarmDataBase alarmDataBase = new AlarmDataBase();
        alarmDataBase.initialize(context);
        alarmDataBase.addAlarm(new Alarm(0, "Test Alarm 0", 0));

        final List<Alarm> alarms = alarmDataBase.getmAlarm();
        assertEquals(1, alarms.size());

    }

    @Test
    public void testSaveLoad() {
        final Context context = InstrumentationRegistry.getContext();
        final AlarmDataBase alarmDataBase = new AlarmDataBase();
        alarmDataBase.initialize(context);
        alarmDataBase.addAlarm(new Alarm(0, "Test Alarm 0", 0));
        alarmDataBase.clear(); //очищаем бд
        alarmDataBase.load(); //сохраняем
        final List<Alarm> alarms = alarmDataBase.getmAlarm();
        assertEquals(alarms.size(), 1);
        final Alarm alarm = alarms.get(0);
        assertEquals(alarm.getTitle(), "Test Alarm 0");



    }
}