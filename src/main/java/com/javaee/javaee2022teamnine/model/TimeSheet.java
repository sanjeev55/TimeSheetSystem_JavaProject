package com.javaee.javaee2022teamnine.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "timesheet")
public class TimeSheet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timesheet_id")
    private int timesheetId;

    @Column(name = "timesheet_status")
    private String timesheetStatus;

    @Column(name = "timesheet_start_date")
    private Date timesheetStartDate;

    @Column(name = "timesheet_end_date")
    private Date timesheetEndDate;

    @Column(name = "contract_id")
    private int contractId;

    @Column(name = "hours_due")
    private double hoursDue;

    @Column(name = "signed_by_employee")
    private Date signedByEmployee;

    @Column(name = "signed_by_supervisor")
    private Date signedBySupervisor;


    public TimeSheet() {
    }

    public TimeSheet(int timesheetId, String timesheetStatus, Date timesheetStartDate, Date timesheetEndDate) {
        this.timesheetId = timesheetId;
        this.timesheetStatus = timesheetStatus;
        this.timesheetStartDate = timesheetStartDate;
        this.timesheetEndDate = timesheetEndDate;
    }

    public TimeSheet(int id, String status, Date startDate, Date endDate, int contractId) {
        this.timesheetId = id;
        this.timesheetStatus = status;
        this.timesheetStartDate = startDate;
        this.timesheetEndDate = endDate;
        this.contractId = contractId;
    }

    public TimeSheet(int id, String status, Date startDate, Date endDate, int cId, double hoursDue) {
        this.timesheetId = id;
        this.timesheetStatus = status;
        this.timesheetStartDate = startDate;
        this.timesheetEndDate = endDate;
        this.contractId = cId;
        this.hoursDue = hoursDue;
    }

    public int getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(int timesheetId) {
        this.timesheetId = timesheetId;
    }

    public String getTimesheetStatus() {
        return timesheetStatus;
    }

    public void setTimesheetStatus(String timesheetStatus) {
        this.timesheetStatus = timesheetStatus;
    }

    public Date getTimesheetStartDate() {
        return timesheetStartDate;
    }

    public void setTimesheetStartDate(Date timesheetStartDate) {
        this.timesheetStartDate = timesheetStartDate;
    }

    public Date getTimesheetEndDate() {
        return timesheetEndDate;
    }


    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public void setTimesheetEndDate(Date timesheetEndDate) {
        this.timesheetEndDate = timesheetEndDate;
    }

    public double getHoursDue() {
        return hoursDue;
    }

    public void setHoursDue(double hoursDue) {
        this.hoursDue = hoursDue;
    }

    public Date getSignedByEmployee() {
        return signedByEmployee;
    }

    public void setSignedByEmployee(Date signedByEmployee) {
        this.signedByEmployee = signedByEmployee;
    }

    public Date getSignedBySupervisor() {
        return signedBySupervisor;
    }

    public void setSignedBySupervisor(Date signedBySupervisor) {
        this.signedBySupervisor = signedBySupervisor;
    }
}
