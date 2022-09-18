package com.javaee.javaee2022teamnine.dao;

import com.javaee.javaee2022teamnine.model.Contract;
import com.javaee.javaee2022teamnine.util.DBConnectionService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContractDao {
    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }

    public void createContract(Contract contract) {
//        ContractStatus status = contract.getStatus();
        int status = contract.getStatus().getId();
        String name = contract.getName();
        Date startDate = (Date) contract.getStartDate();
        Date endDate = (Date) contract.getEndDate();
        double hoursPerWeek = contract.getHoursPerWeek();
        double vacationHours = contract.getVacationHours();
        int workingDaysPerWeek = contract.getWorkingDaysPerWeek();
        int vacationDaysPerYear = contract.getVacationDaysPerYear();

        String query = "Insert into contract(c_status, name, start_date, end_date," +
                " hours_per_week, vacation_hours, working_days_per_week, vacation_days_per_year) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?)";
//        String query = "Insert into contract(c_status, name) values(?, ?)";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, status);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, String.valueOf(startDate));
            preparedStatement.setString(4, String.valueOf(endDate));
            preparedStatement.setDouble(5, hoursPerWeek);
            preparedStatement.setDouble(6, vacationHours);
            preparedStatement.setInt(7, workingDaysPerWeek);
            preparedStatement.setInt(8, vacationDaysPerYear);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
