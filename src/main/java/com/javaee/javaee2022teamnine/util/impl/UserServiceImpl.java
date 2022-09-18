package com.javaee.javaee2022teamnine.util.impl;

import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;
import com.javaee.javaee2022teamnine.util.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> dataList = new ArrayList<>();

        String query = "select id, fullName, username, role from users;";
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

                User user = new User(id, fullName, username, role);

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
