package com.personal.hoasproject;

public class StopWatchModel {
    private int hours;
    private int minutes;
    private int seconds;

    public StopWatchModel() {
        this.restartStopWatch();
    }

    public void restartStopWatch() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    public String getTime() {
        String time;
        if (this.hours < 10) {
            time = "0" + this.hours;
        } else {
            time = String.valueOf(this.hours);
        }

        if (this.minutes < 10) {
            time = time + ":0" + this.minutes;
        } else {
            time = time + ":" + this.minutes;
        }

        if (this.seconds < 10) {
            time = time + ":0" + this.seconds;
        } else {
            time = time + ":" + this.seconds;
        }

        return time;
    }

    public void timeTick() {
        if (this.seconds < 59) {
            ++this.seconds;
        } else if (this.minutes < 59) {
            ++this.minutes;
            this.seconds = 0;
        } else if (this.hours < 24) {
            ++this.hours;
            this.minutes = 0;
            this.seconds = 0;
        } else {
            this.hours = 0;
            this.minutes = 0;
            this.seconds = 0;
        }

    }
}
