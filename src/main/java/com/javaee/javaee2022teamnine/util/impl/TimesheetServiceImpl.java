package com.javaee.javaee2022teamnine.util.impl;

import com.javaee.javaee2022teamnine.model.TimeSheet;
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
        int hoursDue = (int) timeSheet.getHoursDue();

        String query = "Insert into javaee_team_nine.timesheet(timesheet_start_date, timesheet_end_date, timesheet_status," +
                "contract_id, hours_due) " +
                "values(?, ?, ?, ?, ?)";

//        String query = "Insert into contract(c_status, name) values(?, ?)";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, String.valueOf(startDate));
            preparedStatement.setString(2, String.valueOf(endDate));
            preparedStatement.setString(3, timesheetStatus);
            preparedStatement.setInt(4, contractId);
            preparedStatement.setInt(5, hoursDue);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TimeSheet> listTimesheet() {
        List<TimeSheet> timesheetList = new ArrayList<>();

        String query = "select * from javaee_team_nine.timesheet;";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            //Converting resultSet to ArrayList
            while (resultSet.next()) {
                int id = resultSet.getInt("timesheet_id");
                String status = resultSet.getString("timesheet_status");
                Date startDate = resultSet.getDate("timesheet_start_date");
                Date endDate = resultSet.getDate("timesheet_end_date");
                int contractId = resultSet.getInt("contract_id");

                TimeSheet timeSheet = new TimeSheet(id, status, startDate, endDate, contractId);

                timesheetList.add(timeSheet);
            }

            //Print ArrayList
//            System.out.println(timesheetList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timesheetList;
    }

    @Override
    public List<TimeSheet> getSignedTimeSheet() {
        List<TimeSheet> timeSheetList = new ArrayList<>();

        String query = "Select * from javaee_team_nine.timesheet where timesheet_status = ? or timesheet_status = ? ORDER BY timesheet_id";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "SIGNED_BY_SUPERVISOR");
            preparedStatement.setString(2, "ARCHIVED");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("timesheet_id");
                String status = resultSet.getString("timesheet_status");
                Date startDate = resultSet.getDate("timesheet_start_date");
                Date endDate = resultSet.getDate("timesheet_end_date");
                int contractId = resultSet.getInt("contract_id");

                TimeSheet timeSheet = new TimeSheet(id, status, startDate, endDate, contractId);

                timeSheetList.add(timeSheet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeSheetList;
    }

    @Override
    public void updateTimeSheetByID(int id) {
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE javaee_team_nine.timesheet SET timesheet_status = ? where timesheet_id = ?");
            preparedStatement.setString(1, "ARCHIVED");
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean deleteTimesheetIfContractTerminated(int contractId) {
        boolean rowUpdated = false;

        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM javaee_team_nine.timesheet WHERE contract_id = ? AND timesheet_status = ?;");
            preparedStatement.setInt(1, contractId);
            preparedStatement.setString(2, "IN_PROGRESS");

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public List<TimeSheet> getTimesheetByContractId(int contractId) {
        List<TimeSheet> timeSheetList = new ArrayList<>();

        String query = "Select * from javaee_team_nine.timesheet where contract_id = ?;";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, contractId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("timesheet_id");
                String status = resultSet.getString("timesheet_status");
                Date startDate = resultSet.getDate("timesheet_start_date");
                Date endDate = resultSet.getDate("timesheet_end_date");
                int cId = resultSet.getInt("contract_id");
                Double hoursDue = resultSet.getDouble("hours_due");

                TimeSheet timeSheet = new TimeSheet(id, status, startDate, endDate, cId, hoursDue);

                timeSheetList.add(timeSheet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeSheetList;
    }

    @Override
    public double calculateTotalHoursDue(int id) {
        double totalHoursDue = 0;
        List <TimeSheet> timeSheets = getTimesheetByContractId(id);
        for (TimeSheet time: timeSheets){
            totalHoursDue = totalHoursDue + time.getHoursDue();
        }
        return totalHoursDue;
    }
}
