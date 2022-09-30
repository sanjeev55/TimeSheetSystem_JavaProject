package com.javaee.javaee2022teamnine.util.impl;

import com.javaee.javaee2022teamnine.model.TimeSheet;
import com.javaee.javaee2022teamnine.model.TimeSheetEntry;
import com.javaee.javaee2022teamnine.util.DBConnectionService;
import com.javaee.javaee2022teamnine.util.TimesheetService;

import java.sql.*;
import java.time.LocalDate;
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
    public void updateTimeSheetStatusAndSignedBySupervisorByID(int id, LocalDate date){
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE javaee_team_nine.timesheet SET timesheet_status = ?, signed_by_supervisor = ? where timesheet_id = ?");
            preparedStatement.setString(1, "SIGNED_BY_SUPERVISOR");
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTimeSheetStatusAndHoursDueAndSignedByEmployeeById(int id, String status, Double hoursDue, Date entryDate) {
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE javaee_team_nine.timesheet SET timesheet_status = ?, hours_due = ?, signed_by_employee = ? where timesheet_id = ?");
            preparedStatement.setString(1, status);
            preparedStatement.setDouble(2, (hoursDue - 9));
            preparedStatement.setDate(3, entryDate);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTimeSheetEntryById(int id){
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  javaee_team_nine.timesheet_entry SET is_signed = ? where timesheet_entry_id = ?");
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
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

                TimeSheet timeSheet = new TimeSheet(id, status, startDate, endDate, cId);

                timeSheetList.add(timeSheet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeSheetList;
    }

    public List<TimeSheetEntry> getTimesheetEntryByTimeSheetId(int id){
        List<TimeSheetEntry> timeSheetEntryList = new ArrayList<>();
        String query = "Select * from javaee_team_nine.timesheet_entry where timesheet_id = ?;";
        try (Connection connection = dbService.initDB()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int timesheetEntryId = resultSet.getInt("timesheet_entry_id");
                String reportType = resultSet.getString("report_type");
                String description = resultSet.getString("description");
                Double hoursDue = resultSet.getDouble("hours_due");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                Date entryDate = resultSet.getDate("entry_date");
                Boolean isSigned = resultSet.getBoolean("is_signed");

                TimeSheetEntry timeSheetEntry = new TimeSheetEntry(timesheetEntryId, id, reportType,description,hoursDue,startTime, endTime, entryDate, isSigned);
                timeSheetEntryList.add(timeSheetEntry);

            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return timeSheetEntryList;
    }

    @Override
    public List<TimeSheetEntry> addTimeSheetEntry(int timeSheetId, double hoursDue, java.util.Date date) {
        List<TimeSheetEntry> timeSheetEntryList = new ArrayList<>();
        String query = "Insert into javaee_team_nine.timesheet_entry (timesheet_id, report_type, description, hours_due, " +
                "start_time, end_time, entry_date, is_signed) values (?,?,?,?,?,?,?,?);";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, timeSheetId);
            preparedStatement.setString(2, "WORK");
            preparedStatement.setString(3, "Sign in");
            preparedStatement.setDouble(4, (hoursDue/5) );
            preparedStatement.setTime(5, Time.valueOf("09:00:00"));
            preparedStatement.setTime(6, Time.valueOf("18:00:00"));
            preparedStatement.setDate(7, (Date) date);
            preparedStatement.setBoolean(8, false);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return timeSheetEntryList;
    }

    public TimeSheet getTimesheetById (int id){
        TimeSheet timeSheet = new TimeSheet();

        String query = "Select * from javaee_team_nine.timesheet where timesheet_id = ?;";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String status = resultSet.getString("timesheet_status");
                Date startDate = resultSet.getDate("timesheet_start_date");
                Date endDate = resultSet.getDate("timesheet_end_date");
                int cId = resultSet.getInt("contract_id");
                double hoursDue = resultSet.getDouble("hours_due");

                timeSheet = new TimeSheet(id, status, startDate, endDate, cId, hoursDue);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeSheet;
    }
    public TimeSheetEntry getTimesheetEntryById(int id){
        TimeSheetEntry timeSheetEntry = new TimeSheetEntry();

        String query = "Select * from javaee_team_nine.timesheet_entry where timesheet_entry_id = ?;";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int timeSheetId = resultSet.getInt("timesheet_id");
                String reportType = resultSet.getString("report_type");
                String description = resultSet.getString("description");
                Double hoursDue = resultSet.getDouble("hours_due");
                Time startTime = resultSet.getTime("start_time");
                Time endTime = resultSet.getTime("end_time");
                Date entryDate = resultSet.getDate("entry_date");
                Boolean isSigned = resultSet.getBoolean("is_signed");


                timeSheetEntry = new TimeSheetEntry(id, timeSheetId, reportType, description, hoursDue,startTime,endTime,entryDate, isSigned);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeSheetEntry;
    }

    public List<TimeSheet> getSignedByEmployeeTimeSheets(){
        List<TimeSheet> timeSheets = new ArrayList<>();
        String query = "Select * from javaee_team_nine.timesheet where timesheet_status =?;";
        try(Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"SIGNED_BY_EMPLOYEE");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("timesheet_id");
                String status = resultSet.getString("timesheet_status");
                Date startDate = resultSet.getDate("timesheet_start_date");
                Date endDate = resultSet.getDate("timesheet_end_date");
                int contractId = resultSet.getInt("contract_id");

                TimeSheet timeSheet = new TimeSheet(id, status, startDate, endDate, contractId);

                timeSheets.add(timeSheet);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return timeSheets;
    }


}
