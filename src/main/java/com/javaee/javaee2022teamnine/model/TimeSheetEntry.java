package com.javaee.javaee2022teamnine.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name  = "timesheet_entry")

public class TimeSheetEntry implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timesheet_entry_id")
    private int timesheetEntryId;

    @Column(name = "timesheed_id")
    private  int timesheetId;

    @Column(name = "report_type")
    private String reportType;

    @Column(name = "description")
    private String description;

    @Column(name = "hours_due")
    private double hoursDue;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "is_signed")
    private Boolean isSigned;

    public TimeSheetEntry(){

    }

    public TimeSheetEntry(int timesheetEntryId, int timesheetId, String reportType, String description, double hoursDue, Time startTime, Time endTime, Date entryDate, Boolean isSigned){
        this.timesheetEntryId = timesheetEntryId;
        this.timesheetId = timesheetId;
        this.reportType = reportType;
        this.description = description;
        this.hoursDue = hoursDue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.entryDate = entryDate;
        this.isSigned = isSigned;

    }

    public int getTimesheetEntryId() {
        return timesheetEntryId;
    }

    public void setTimesheetEntryId(int timesheetEntryId) {
        this.timesheetEntryId = timesheetEntryId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHoursDue() {
        return hoursDue;
    }

    public void setHoursDue(double hoursDue) {
        this.hoursDue = hoursDue;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public int getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(int timesheetId) {
        this.timesheetId = timesheetId;
    }

    public Boolean getSigned() {
        return isSigned;
    }

    public void setSigned(Boolean signed) {
        isSigned = signed;
    }
}
