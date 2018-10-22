package com.banana.y17_2.clock;

public class Alarm {
    final String title;
    private final int hour;
    private final int minute;
    final long id;
    final int day;

    public Alarm(long id, String title, int hour, int minute, int day) {
        this.title = title;
        this.day = day;
        this.hour =  hour;
        this.minute =  minute;
        this.id = id;

    }


    public String getTitle() {
        return title;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int  getDay() {
        return day;
    }

    public long getId() {
        return id;
    }

}
