package com.personal.hoasproject;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HoasStopWatchModel extends StopWatchModel {

    private String startTime;
    private String endTime;
    private String totalTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public void printDetails() throws ParseException, IOException {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String workedHours = HoasStopWatchPanel.getSelectedUserStory() + "," + sdf.format(new Date()) + "," + startTime + "," + endTime + "," + getTimeDifference()/60000 + " minutes\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter("resources/HoasPersonalTimeTracker.csv", true));
        writer.append(workedHours);
        writer.close();
    }

    private long getTimeDifference() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(startTime);
        Date date2 = format.parse(endTime);
        return ((long)(date2.getTime() - date1.getTime()));
    }

}
