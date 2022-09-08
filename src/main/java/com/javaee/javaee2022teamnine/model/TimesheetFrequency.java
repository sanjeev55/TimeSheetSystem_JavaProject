package com.javaee.javaee2022teamnine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timesheet_frequency")
public class TimesheetFrequency {
    @Id
    private int id;

    @Column(name = "timesheet_frequency")
    private String timesheetFrequency;

    public TimesheetFrequency() {
    }

    public TimesheetFrequency(int id, String timesheetFrequency) {
        this.id = id;
        this.timesheetFrequency = timesheetFrequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimesheetFrequency() {
        return timesheetFrequency;
    }

    public void setTimesheetFrequency(String timesheetFrequency) {
        this.timesheetFrequency = timesheetFrequency;
    }
}
