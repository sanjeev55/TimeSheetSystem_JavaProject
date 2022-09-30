/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javaee.javaee2022teamnine.dao;

import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prajwolthapa
 */
public class ReminderDao {
    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }


    public List<User> getAllInProgressReminderUsers() {
        String query = "SELECT u.* FROM \n" +
                "users u \n" +
                "inner join contract c \n" +
                "inner join contract_status cs \n" +
                "inner join timesheet t \n" +
                "inner join timesheet_frequency tf\n" +
                "inner join timesheet_status ts\n" +
                "on u.has_contract = c.contract_id\n" +
                "and c.c_status = cs.contract_status_id\n" +
                "and c.contract_id = t.contract_id\n" +
                "and c.frequency = tf.timesheet_frequency\n" +
                "and t.timesheet_status = ts.timesheet_status\n" +
                "and ts.timesheet_status = \"IN_PROGRESS\"\n" +
                "and upper(cs.contract_status) = \"STARTED\"\n" +
                "and CURDATE() between t.timesheet_start_date  and t.timesheet_end_date\n" +
                ";";
        return getUserDetailsFromDB(query);

    }

    public List<User> getAllSignedByEmployeeReminderUsers() {
        String query = "SELECT u.* FROM \n" +
                "users u \n" +
                "inner join contract c \n" +
                "inner join contract_status cs \n" +
                "inner join timesheet t \n" +
                "inner join timesheet_frequency tf\n" +
                "inner join timesheet_status ts\n" +
                "on u.has_contract = c.contract_id\n" +
                "and c.c_status = cs.contract_status_id\n" +
                "and c.contract_id = t.contract_id\n" +
                "and c.frequency = tf.timesheet_frequency\n" +
                "and t.timesheet_status = ts.timesheet_status\n" +
                "and ts.timesheet_status = \"SIGNED_BY_EMPLOYEE\"\n" +
                "and upper(cs.contract_status) = \"STARTED\"\n" +
                "and CURDATE() between t.timesheet_start_date  and t.timesheet_end_date\n" +
                ";";

        return getUserDetailsFromDB(query);

    }

    public List<User> getAllSignedBySupervisorReminderUsers() {
        String query = "SELECT u.* FROM \n" +
                "users u \n" +
                "inner join contract c \n" +
                "inner join contract_status cs \n" +
                "inner join timesheet t \n" +
                "inner join timesheet_frequency tf\n" +
                "inner join timesheet_status ts\n" +
                "on u.has_contract = c.contract_id\n" +
                "and c.c_status = cs.contract_status_id\n" +
                "and c.contract_id = t.contract_id\n" +
                "and c.frequency = tf.timesheet_frequency\n" +
                "and t.timesheet_status = ts.timesheet_status\n" +
                "and ts.timesheet_status = \"SIGNED_BY_SUPERVISOR\"\n" +
                "and upper(cs.contract_status) = \"STARTED\"\n" +
                "and CURDATE() between t.timesheet_start_date  and t.timesheet_end_date\n" +
                ";";

        return getUserDetailsFromDB(query);

    }

    public List<User> getSupervisorAndAssistantUsers() {
        String query = "SELECT u.* FROM \n" +
                "javaee_team_nine.users u where\n" +
                "upper(u.role) in (\"ASSISTANT\", \"SUPERVISOR\");";
        return getUserDetailsFromDB(query);

    }

    public List<User> getSecretaryUsers() {
        String query = "SELECT u.* FROM \n" +
                "javaee_team_nine.users u where\n" +
                "upper(u.role) = \"SECRETARY\";";
        return getUserDetailsFromDB(query);

    }

    public List<User> getUserDetailsFromDB(String query) {

        List<User> dataList = new ArrayList<>();

        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("resultSet++++" + resultSet);

            //Converting resultSet to ArrayList
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("fullName");
                String username = resultSet.getString("username");
                String role = resultSet.getString("role");
                boolean hasContract = resultSet.getBoolean("has_contract");

                User user = new User(id, fullName, username, role, hasContract);

                dataList.add(user);
            }

            //Print ArrayList
            System.out.println(dataList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;

    }

}


