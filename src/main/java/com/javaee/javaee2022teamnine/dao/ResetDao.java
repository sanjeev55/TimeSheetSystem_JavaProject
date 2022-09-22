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
 *
 * @author ayush
 */
public class ResetDao {
 DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }
    public String resetpass(User user) throws SQLException{
        String email = user.getEmail();
        String password = user.getPassword();
        String query = "update users SET password= ? where email= ?";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
     return null;
    }
}
