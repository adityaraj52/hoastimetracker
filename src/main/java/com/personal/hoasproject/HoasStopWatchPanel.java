package com.personal.hoasproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class HoasStopWatchPanel extends JPanel {

    private JLabel stopWatchDisplay = new JLabel("00:00:00");
    private JButton startCommand = new JButton("Start");
    private JButton resetButton = new JButton("Reset");
    private List<String> description = new LinkedList<String>();
    private JComboBox userStoriesDropDownItems = new JComboBox();
    private int count = 0;
    private static String selectedUserStory = null;
    private JButton saveToFile = new JButton("UpdateTimeTrackerFile");

    public void setDisplay(String display) {
        this.stopWatchDisplay.setText(display);
    }

    public void setCommandText(String command) {
        this.startCommand.setText(command);
    }

    public HoasStopWatchPanel readUserStoriesFromPropertiesFile() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("resources/PWAUserStories"));
        } catch (IOException e) {
        }
        int i = 0;
        for (String key : properties.stringPropertyNames()) {
            description.add(key);
        }
        return this;

    }

    public HoasStopWatchPanel(ActionListener listener) {
        readUserStoriesFromPropertiesFile().setUserStories();
        this.startCommand.addActionListener(listener);
        this.resetButton.addActionListener(listener);
        this.saveToFile.addActionListener(listener);
        createGui();

    }

    public void setUserStories() {
        for (int i = 0; i < description.size(); i++)
            userStoriesDropDownItems.addItem(description.get(i));
        userStoriesDropDownItems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedUserStory = (String) ((JComboBox) e.getSource()).getSelectedItem();
            }
        });
        userStoriesDropDownItems.setSelectedIndex(-1);
    }

    public static String getSelectedUserStory() {
        return selectedUserStory;
    }

    private void createGui() {
        int gridRowNumber = 1;
        Font fontStyle = new Font("", Font.BOLD, 16);

        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gridBagLayout);

        final JLabel timerLabel = new JLabel("Timer");
        final GridBagConstraints gbc_timerLabel = new GridBagConstraints();
        gbc_timerLabel.insets = new Insets(0, 0, 5, 5);
        gbc_timerLabel.gridx = 0;
        gbc_timerLabel.gridy = gridRowNumber;
        timerLabel.setFont(fontStyle);
        add(timerLabel, gbc_timerLabel);

        final GridBagConstraints gbc_stopWatchDisplay = new GridBagConstraints();
        gbc_stopWatchDisplay.insets = new Insets(0, 0, 5, 5);
        gbc_stopWatchDisplay.gridx = 1;
        gbc_stopWatchDisplay.gridy = gridRowNumber++;
        this.stopWatchDisplay.setFont(new Font("", Font.BOLD, 40));
        add(stopWatchDisplay, gbc_stopWatchDisplay);

        final JLabel selectUserStory = new JLabel("Select User Story");
        final GridBagConstraints gbc_selectUserStory = new GridBagConstraints();
        gbc_selectUserStory.insets = new Insets(0, 0, 5, 5);
        gbc_selectUserStory.gridx = 0;
        gbc_selectUserStory.gridy = gridRowNumber;
        selectUserStory.setFont(fontStyle);
        add(selectUserStory, gbc_selectUserStory);

        final GridBagConstraints gbc_dropDownItems = new GridBagConstraints();
        gbc_dropDownItems.insets = new Insets(0, 0, 5, 0);
        gbc_dropDownItems.gridx = 1;
        gbc_dropDownItems.gridy = gridRowNumber++;
        this.userStoriesDropDownItems.setFont(fontStyle);
        this.add(this.userStoriesDropDownItems, gbc_dropDownItems);

        final GridBagConstraints gbc_startCommand = new GridBagConstraints();
        gbc_startCommand.insets = new Insets(0, -220, 5, 0);
        gbc_startCommand.gridx = 1;
        gbc_startCommand.gridy = gridRowNumber;
        this.startCommand.setFont(fontStyle);
        this.startCommand.setForeground(Color.GREEN);
        this.add(this.startCommand, gbc_startCommand);

        final GridBagConstraints gbc_stopCommand = new GridBagConstraints();
        gbc_stopCommand.insets = new Insets(0, -50, 5, 0);
        gbc_stopCommand.gridx = 1;
        gbc_stopCommand.gridy = gridRowNumber;
        this.resetButton.setForeground(Color.RED);
        this.resetButton.setFont(fontStyle);
        this.add(this.resetButton, gbc_stopCommand);

        final GridBagConstraints gbc_resetCommand = new GridBagConstraints();
        gbc_resetCommand.insets = new Insets(0, -130, 5, 0);
        gbc_resetCommand.gridx = 1;
        gbc_resetCommand.gridy = ++gridRowNumber;
        this.saveToFile.setFont(fontStyle);
        this.saveToFile.setForeground(Color.BLUE);
        this.add(this.saveToFile, gbc_resetCommand);
    }
}
