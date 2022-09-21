package com.javaee.javaee2022teamnine.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "contract")
public class Contract implements Serializable {
    @Id
    @Column(name = "contract_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "c_status", referencedColumnName = "contract_status_id")
    private ContractStatus status;

    @Column(name = "userId")
    private int userId;

    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;
    private String frequency;

    @Column(name = "termination_date")
    private Date terminationDate;

    @Column(name = "hours_per_week")
    private double hoursPerWeek;

    @Column(name = "hours_due")
    private double hoursDue;

    @Column(name = "vacation_hours")
    private double vacationHours;

    @Column(name = "working_days_per_week")
    private int workingDaysPerWeek;

    @Column(name = "vacation_days_per_year")
    private int vacationDaysPerYear;

    public Contract(int id, ContractStatus status, String name, Date startDate, Date endDate) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Contract(String name, int vacationDaysPerYear) {
        this.name = name;
        this.vacationDaysPerYear = vacationDaysPerYear;
    }

 /*   public Contract(String name) {
        this.name = name;
    }*/

    public Contract(ContractStatus status, String name, Date startDate, Date endDate, String frequency, Date terminationDate, double hoursPerWeek, double hoursDue, int workingDaysPerWeek, int vacationDaysPerYear) {
        this.status = status;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.terminationDate = terminationDate;
        this.hoursPerWeek = hoursPerWeek;
        this.hoursDue = hoursDue;
        this.workingDaysPerWeek = workingDaysPerWeek;
        this.vacationDaysPerYear = vacationDaysPerYear;
    }

    public Contract() {
    }

    public Contract(int id, String name, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Contract(int id, String name, int vacationDaysPerYear) {
        this.id = id;
        this.name = name;
        this.vacationDaysPerYear = vacationDaysPerYear;
    }

    public Contract(int id, String name, Date startDate, Date endDate, double hoursPerWeek, double vacationHours, int workingDaysPerWeek, int vacationDaysPerYear) {
        this.id = id;
//        this.status = status;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hoursPerWeek = hoursPerWeek;
        this.vacationHours = vacationHours;
        this.workingDaysPerWeek = workingDaysPerWeek;
        this.vacationDaysPerYear = vacationDaysPerYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public double getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public double getHoursDue() {
        return hoursDue;
    }

    public void setHoursDue(double hoursDue) {
        this.hoursDue = hoursDue;
    }

    public int getWorkingDaysPerWeek() {
        return workingDaysPerWeek;
    }

    public void setWorkingDaysPerWeek(int workingDaysPerWeek) {
        this.workingDaysPerWeek = workingDaysPerWeek;
    }

    public int getVacationDaysPerYear() {
        return vacationDaysPerYear;
    }

    public void setVacationDaysPerYear(int vacationDaysPerYear) {
        this.vacationDaysPerYear = vacationDaysPerYear;
    }

    public double getVacationHours() {
        return vacationHours;
    }

    public void setVacationHours(double vacationHours) {
        this.vacationHours = vacationHours;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
