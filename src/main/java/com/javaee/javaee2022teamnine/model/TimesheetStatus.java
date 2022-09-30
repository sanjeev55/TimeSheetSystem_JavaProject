package com.javaee.javaee2022teamnine.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "timesheet_status")
public class TimesheetStatus implements Serializable {
    @Id
    @Column(name = "ts_status_id")
    private int id;

    @Column(name = "timesheet_status")
    private String timesheetStatus;

    public TimesheetStatus(int id, String timesheetStatus) {
        this.id = id;
        this.timesheetStatus = timesheetStatus;
    }

    public TimesheetStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimesheetStatus() {
        return timesheetStatus;
    }

    public void setTimesheetStatus(String timesheetStatus) {
        this.timesheetStatus = timesheetStatus;
    }
}
