package com.javaee.javaee2022teamnine.util.impl;

import com.javaee.javaee2022teamnine.model.TimeSheet;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;
import com.javaee.javaee2022teamnine.util.TimesheetService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimesheetServiceImpl implements TimesheetService {
    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }

    @Override
    public void createTimesheet(TimeSheet timeSheet) {
        String timesheetStatus = timeSheet.getTimesheetStatus();
        int contractId = timeSheet.getContractId();
        Date startDate = (Date) timeSheet.getTimesheetStartDate();
        Date endDate = (Date) timeSheet.getTimesheetEndDate();

        String query = "Insert into javaee_team_nine.timesheet(timesheet_start_date, timesheet_end_date, timesheet_status," +
                "contract_id) " +
                "values(?, ?, ?, ?)";

//        String query = "Insert into contract(c_status, name) values(?, ?)";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, String.valueOf(startDate));
            preparedStatement.setString(2, String.valueOf(endDate));
            preparedStatement.setString(3, timesheetStatus);
            preparedStatement.setInt(4, contractId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TimeSheet> listTimesheet() {
        List<TimeSheet> timesheetList = new ArrayList<>();

        String query = "select timesheet_id, timesheet_start_date, timesheet_end_date, timesheet_status from javaee_team_nine.timesheet;";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            //Converting resultSet to ArrayList
            while (resultSet.next()) {
                int id = resultSet.getInt("timesheet_id");
                String status = resultSet.getString("timesheet_status");
                Date startDate = resultSet.getDate("timesheet_start_date");
                Date endDate = resultSet.getDate("timesheet_end_date");

                TimeSheet timeSheet = new TimeSheet(id, status, startDate, endDate);

                timesheetList.add(timeSheet);
            }

            //Print ArrayList
            System.out.println(timesheetList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timesheetList;
    }
}
