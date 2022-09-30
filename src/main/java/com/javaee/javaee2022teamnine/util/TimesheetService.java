package com.javaee.javaee2022teamnine.util;

import com.javaee.javaee2022teamnine.model.TimeSheet;
import com.javaee.javaee2022teamnine.model.TimeSheetEntry;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TimesheetService {
    void createTimesheet(TimeSheet timesheet);

    List<TimeSheet> listTimesheet();

    List<TimeSheet> getSignedTimeSheet();

    void updateTimeSheetByID(int id);

    void updateTimeSheetStatusAndHoursDueAndSignedByEmployeeById(int id, String status, Double hoursDue, java.sql.Date entryDate);

    boolean deleteTimesheetIfContractTerminated(int contract_id);

    List<TimeSheet> getTimesheetByContractId(int id);

    List<TimeSheetEntry> getTimesheetEntryByTimeSheetId(int id);

    List<TimeSheetEntry> addTimeSheetEntry(int timeSheetId, double hoursDue, Date date);

    TimeSheet getTimesheetById(int timeSheetId);

    TimeSheetEntry getTimesheetEntryById(int id);

    void updateTimeSheetEntryById(int id);

    List<TimeSheet> getSignedByEmployeeTimeSheets();

    void updateTimeSheetStatusAndSignedBySupervisorByID(int id, LocalDate date);
}
