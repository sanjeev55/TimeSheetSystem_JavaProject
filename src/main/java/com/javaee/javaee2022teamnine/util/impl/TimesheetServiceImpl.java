package com.javaee.javaee2022teamnine.util.impl;

import com.javaee.javaee2022teamnine.model.TimeSheet;
import com.javaee.javaee2022teamnine.util.DBConnectionService;
import com.javaee.javaee2022teamnine.util.TimesheetService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TimesheetServiceImpl implements TimesheetService {
    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }

    @Override
    public void createTimesheet(TimeSheet timeSheet) {
        String timesheetStatus = timeSheet.getTimesheetStatus();
        Date startDate = (Date) timeSheet.getTimesheetStartDate();
        Date endDate = (Date) timeSheet.getTimesheetEndDate();

        String query = "Insert into javaee_team_nine.timesheet(timesheet_start_date, timesheet_end_date, timesheet_status) " +
                "values(?, ?, ?)";

//        String query = "Insert into contract(c_status, name) values(?, ?)";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, String.valueOf(startDate));
            preparedStatement.setString(2, String.valueOf(endDate));
            preparedStatement.setString(3, timesheetStatus);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
