package com.banana.y17_2.clock;

public class Alarm {
    final String title;
    final long timeStamp;
    final long id;

    public Alarm(long timeStamp, String title, long id) {
        this.title = title;
        this.timeStamp = timeStamp;
        this.id = id;

    }

    public String getTitle() {
        return title;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public long getId() {
        return id;
    }

}
