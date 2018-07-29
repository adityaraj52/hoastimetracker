package com.personal.hoasproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class TimeTracker extends JFrame {
    private HoasStopWatchModel HoasstopWatchModel = new HoasStopWatchModel();
    private HoasStopWatchPanel hoasstopWatchPanelPanel;

    public TimeTracker() {
        this.initialization();
    }

    private void initialization() {
        this.hoasstopWatchPanelPanel = new HoasStopWatchPanel(new HoasStopWatchListener());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Working Hours", this.hoasstopWatchPanelPanel);
        this.add(tabbedPane, "Center");
        this.setTitle("HoAS Personal Time Tracker");
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimeTracker();
            }
        });
    }

    class HoasStopWatchListener implements ActionListener {
        private Timer swingTimerTicker;
        private HoasStopWatchModel hoasStopWatchModel = new HoasStopWatchModel();

        HoasStopWatchListener() {
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Start") {
                TimeTracker.this.hoasstopWatchPanelPanel.setCommandText("Stop");
                TimeTracker.this.hoasstopWatchPanelPanel.setDisplay(TimeTracker.this.HoasstopWatchModel.getTime());
                hoasStopWatchModel.setStartTime(TimeTracker.this.HoasstopWatchModel.getTime());
                this.swingTimerTicker = new Timer(1000, this);
                this.swingTimerTicker.start();
            }

            if (e.getActionCommand() == "Stop") {
                hoasStopWatchModel.setEndTime(TimeTracker.this.HoasstopWatchModel.getTime());
                TimeTracker.this.hoasstopWatchPanelPanel.setCommandText("Start");
                this.swingTimerTicker.stop();
            }

            if (e.getActionCommand() == "Reset") {
                TimeTracker.this.HoasstopWatchModel.restartStopWatch();
                TimeTracker.this.hoasstopWatchPanelPanel.setDisplay(TimeTracker.this.HoasstopWatchModel.getTime());
            }

            if (e.getActionCommand() == "UpdateTimeTrackerFile") {
                try {
                    hoasStopWatchModel.printDetails();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (e.getSource() == this.swingTimerTicker) {
                TimeTracker.this.HoasstopWatchModel.timeTick();
                TimeTracker.this.hoasstopWatchPanelPanel.setDisplay(TimeTracker.this.HoasstopWatchModel.getTime());
            }

        }
    }
}
