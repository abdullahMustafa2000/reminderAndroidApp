package com.ryada.calnderproject.modules;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reminders_table")
public class ReminderModule {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String name;
    private int hour;
    private int minute;
    private int pm_am;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getPm_am() {
        return pm_am;
    }

    public void setPm_am(int pm_am) {
        this.pm_am = pm_am;
    }

    public int getId() {
        return id;
    }
}
