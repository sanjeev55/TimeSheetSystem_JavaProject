package com.javaee.javaee2022teamnine.dao;

import java.sql.*;


import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;


public class RegisterDao {

    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }

    public String registerUser(User user) {
        String fullName = user.getFullName();
        String email = user.getEmail();
        String password = user.getPassword();
        Date dob = user.getDob();
        String federalState = user.getFederalState();
        String role = user.getRole();
        boolean tos = user.isTos();

        String query = "Insert into javaee_team_nine.users(fullname, username, password, dob, tos, federalState, role) " +
                "values(?, ?, ?, ?, ?, ? ,?)";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, String.valueOf(dob));
            preparedStatement.setBoolean(5, tos);
            preparedStatement.setString(6, federalState);
            preparedStatement.setString(7, role);

            int i = preparedStatement.executeUpdate();

            if (i != 0)  //Just to ensure data has been inserted into the database
                return "SUCCESS";
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
    }
}