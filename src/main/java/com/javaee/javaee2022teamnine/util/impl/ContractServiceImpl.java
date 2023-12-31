package com.javaee.javaee2022teamnine.util.impl;

import com.javaee.javaee2022teamnine.model.Contract;
import com.javaee.javaee2022teamnine.model.ContractStatus;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.ContractService;
import com.javaee.javaee2022teamnine.util.DBConnectionService;
import com.javaee.javaee2022teamnine.util.DateService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractServiceImpl implements ContractService {
    DBConnectionService dbService = new DBConnectionService();
    DateService dateService = new DateService();

    public DBConnectionService getDbService() {
        return dbService;
    }

    @Override
    public Contract createContract(User user) {
        /*Contract contract = new Contract();

        if (user.getRole().equals("Employee")) {
            contract.setName("Employee" + user.getId() + "Contract");
            contract.setStartDate(new Date());
            contract.setStatus(new ContractStatus("PREPARED"));

            return contract;
        }
        return contract;*/
        return null;
    }


    @Override
    public List<Contract> getContractList() {
        List<Contract> contractList = new ArrayList<>();

        String query = "select contract_id, c_status, name, start_date, end_date from javaee_team_nine.contract WHERE c_status = 1;";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("resultSet++++" + resultSet);

            //Converting resultSet to ArrayList
            while (resultSet.next()) {
                int id = resultSet.getInt("contract_id");
                String name = resultSet.getString("name");
                int status = resultSet.getInt("c_status");
                Date start_date = resultSet.getDate("start_date");
                Date end_date = resultSet.getDate("end_date");

                Contract contract = new Contract(id, name, start_date, end_date);
//                Contract contract = new Contract(id, status, name, start_date, end_date);

                contractList.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contractList;
    }

    @Override
    public boolean deleteContract(int contract_id) {
        boolean contractRowDeleted = false;
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM javaee_team_nine.contract WHERE contract_id = ? AND c_status = 1;"
            );
            preparedStatement.setInt(1, contract_id);
            contractRowDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contractRowDeleted;
    }

    @Override
    public int calculateWorkingDaysPerWeek() {
        return 5;
    }

    @Override
    public int calculateVacationDaysPerYear() {
        return 20;
    }

    @Override
    public double calculateHoursPerWeek() {
        return calculateWorkingDaysPerWeek() * 9;
    }

    // todo: do this after Timesheet implementation

    @Override
    public double calculateVacationHours(java.util.Date startDate, java.util.Date endDate) {
        int totalHolidays = dateService.getHolidays(
                dateService.dateToCalendar(startDate),
                dateService.dateToCalendar(endDate));

        double vacationHours;
        vacationHours = ((totalHolidays * dateService.countMonthsBetweenDates(
                dateService.dateToCalendar(startDate), dateService.dateToCalendar(endDate))) / 12)
                * (calculateHoursPerWeek() / calculateWorkingDaysPerWeek());

        return Math.abs(vacationHours);
    }

    @Override
    public double calculateHoursDue(int wdip, int phip, double hpw, int wdpw ){
        double hoursDue = ((wdip - phip) * hpw) / wdpw;
        System.out.println("Hours due:"+hoursDue);
        return hoursDue;
    }

    @Override
    public Contract getContractById(int id) {
        String SELECT_CONTRACT_BY_ID = "select contract_id, " +
//                "c_status, " +
                "name, " +
                "start_date, " +
                "userId, " +
                "end_date, " +
                "frequency, " + // todo
                "hours_per_week," +
                "vacation_hours, " +
                "working_days_per_week, " +
//                            "hours_due = ?, " + // todo
                "vacation_days_per_year " +
                "from javaee_team_nine.contract where contract_id = ?";

        Contract contract = null;
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTRACT_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int contract_id = rs.getInt("contract_id");
                String name = rs.getString("name");
//                ContractStatus status = (ContractStatus) rs.getObject(new ContractStatus().getId());
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                double hoursPerWeek = rs.getDouble("hours_per_week");
                double vacationHours = rs.getDouble("vacation_hours");
                int workingDaysPerWeek = rs.getInt("working_days_per_week");
                int vacationDaysPerYear = rs.getInt("vacation_days_per_year");
                String frequency = rs.getString("frequency");
                int userId = rs.getInt("userId");

                contract = new Contract(contract_id, name, startDate, endDate, hoursPerWeek, vacationHours, workingDaysPerWeek, vacationDaysPerYear, frequency, userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }

    @Override
    public boolean updateContract(Contract contract) {
        boolean rowUpdated = false;

        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE javaee_team_nine.contract SET " +
                            "start_date = ?, " +
                            "end_date = ?, " +
//                            "frequency = ?, " + // todo
                            "hours_per_week= ?," +
                            "vacation_hours = ?, " +
                            "working_days_per_week = ?, " +
//                            "hours_due = ?, " + // todo
                            "vacation_days_per_year = ? WHERE contract_id = ? and c_status = 1;");
            preparedStatement.setDate(1, (Date) contract.getStartDate());
            preparedStatement.setDate(2, (Date) contract.getEndDate());
            preparedStatement.setDouble(3, contract.getHoursPerWeek());
            preparedStatement.setDouble(4, contract.getVacationHours());
            preparedStatement.setInt(5, contract.getWorkingDaysPerWeek());
            preparedStatement.setInt(6, contract.getVacationDaysPerYear());
            preparedStatement.setInt(7, contract.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public boolean startContract(Contract existingContract) {
        boolean rowUpdated = false;

        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE javaee_team_nine.contract SET c_status = ?, has_timesheet = ?, hours_due = ? WHERE contract_id = ?;");
            preparedStatement.setInt(1, existingContract.getStatus().getId());
            preparedStatement.setBoolean(2, existingContract.isHasTimesheet());
            preparedStatement.setDouble(3, existingContract.getHoursDue());
            preparedStatement.setInt(4, existingContract.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public List<Contract> getStartedContractList() {
        List<Contract> startedContractList = new ArrayList<>();

        String query = "select contract_id, c_status, name, start_date, end_date, has_timesheet, userId from javaee_team_nine.contract WHERE c_status = 2;";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("contract_id");
                String name = resultSet.getString("name");
                int status = resultSet.getInt("c_status");
                Date start_date = resultSet.getDate("start_date");
                Date end_date = resultSet.getDate("end_date");
                boolean hasTimesheet = resultSet.getBoolean("has_timesheet");
                int userId = resultSet.getInt("userId");

                Contract contract = new Contract(id, new ContractStatus(status), name, start_date, end_date, hasTimesheet, userId);

                startedContractList.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return startedContractList;
    }

    @Override
    public boolean terminateContract(Contract existingContract) {
        boolean rowUpdated = false;

        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE javaee_team_nine.contract SET c_status = ?, termination_date = ? WHERE contract_id = ?;");
            preparedStatement.setInt(1, existingContract.getStatus().getId());
            preparedStatement.setDate(2, (Date) existingContract.getTerminationDate());
            preparedStatement.setInt(3, existingContract.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public Contract getContractByUserId(int userId) {
        String query = "SELECT * FROM javaee_team_nine.contract WHERE userId = ?;";
        Contract contract = null;
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int status = resultSet.getInt("c_status");
                int contractId = resultSet.getInt("contract_id");
                String name = resultSet.getString("name");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                Date terminationDate = resultSet.getDate("termination_date");
                String frequency = resultSet.getString("frequency");
                double hpw = resultSet.getDouble("hours_per_week");
                double hoursDue = resultSet.getDouble("hours_due");
                int wdpw = resultSet.getInt("working_days_per_week");
                int vdpy = resultSet.getInt("vacation_days_per_year");


                contract = new Contract(contractId, new ContractStatus(status), name, startDate, endDate, frequency, terminationDate, hpw, hoursDue, wdpw, vdpy, userId);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }
}
