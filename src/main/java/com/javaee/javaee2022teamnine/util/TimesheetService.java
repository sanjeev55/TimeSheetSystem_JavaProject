package com.javaee.javaee2022teamnine.util;

import com.javaee.javaee2022teamnine.model.TimeSheet;

import java.util.List;

public interface TimesheetService {
    void createTimesheet(TimeSheet timesheet);

    List<TimeSheet> listTimesheet();

    List<TimeSheet> getSignedTimeSheet();

    void updateTimeSheetByID(int id);
}
