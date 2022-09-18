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

//@Stateless
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


    /**
     * @return List of all Contracts
     */
    @Override
    public List<Contract> getContractList() {
        List<Contract> contractList = new ArrayList<>();

        String query = "select contract_id, c_status, name, start_date, end_date from contract;";
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
    public List<Contract> search(String name) {
        return null;
    }

    @Override
    public Contract getContract(String uuid) {
        return null;
    }

    @Override
    public String storeContract(Contract Contract) {
        return null;
    }

    @Override
    public boolean deleteContract(int contract_id) {
        boolean contractRowDeleted = false;
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM contract WHERE contract_id = ?;"
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
    public User getCurrentUser() {
        return null;
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
    public double calculateHoursDue() {
        return 0;
    }

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
    public Contract getContractById(int id) {
        String SELECT_CONTRACT_BY_ID = "select contract_id, name, vacation_days_per_year  from contract where contract_id = ?";

        Contract contract = null;
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTRACT_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int vacation_days_per_year = rs.getInt("vacation_days_per_year");
                contract = new Contract(id, name, vacation_days_per_year);
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
            String UPDATE_CONTRACT_SQL = "";
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE contract SET vacation_days_per_year = ? WHERE contract_id = ?;");
            preparedStatement.setInt(1, contract.getVacationDaysPerYear());
            preparedStatement.setInt(2, contract.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
