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

/**
 * @author ayush
 */
public class ResetDao {
    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }

    /**
     * @param user User object to get the email and password
     * @return Updates forgotten password
     * @throws SQLException If there is mistake in the query
     */
    public boolean resetPassword(User user) throws SQLException {
        boolean rowUpdated = false;

        String email = user.getEmail();
        String password = user.getPassword();
        String query = "UPDATE javaee_team_nine.users SET password= ? WHERE username= ?";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return rowUpdated;
    }
}
