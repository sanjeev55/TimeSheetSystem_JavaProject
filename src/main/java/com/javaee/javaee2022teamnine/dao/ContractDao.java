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

    /**
     * @param contract Contract object being passed from the contract controller
     *                 This method will create a new Contract object
     */
    public void createContract(Contract contract) {
//        ContractStatus status = contract.getStatus();
        int status = contract.getStatus().getId();
        String name = contract.getName();
        Date startDate = (Date) contract.getStartDate();
        Date endDate = (Date) contract.getEndDate();
        String frequency = contract.getFrequency();
        double hoursPerWeek = contract.getHoursPerWeek();
        double vacationHours = contract.getVacationHours();
        int workingDaysPerWeek = contract.getWorkingDaysPerWeek();
        int vacationDaysPerYear = contract.getVacationDaysPerYear();
        int userId = contract.getUserId();

        String query = "Insert into javaee_team_nine.contract(c_status, name, start_date, end_date, frequency," +
                " hours_per_week, vacation_hours, working_days_per_week, vacation_days_per_year, userId) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

//        String query = "Insert into contract(c_status, name) values(?, ?)";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, status);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, String.valueOf(startDate));
            preparedStatement.setString(4, String.valueOf(endDate));
            preparedStatement.setString(5, frequency);
            preparedStatement.setDouble(6, hoursPerWeek);
            preparedStatement.setDouble(7, vacationHours);
            preparedStatement.setInt(8, workingDaysPerWeek);
            preparedStatement.setInt(9, vacationDaysPerYear);
            preparedStatement.setInt(10, userId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
