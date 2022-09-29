package com.javaee.javaee2022teamnine.util.impl;

import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;
import com.javaee.javaee2022teamnine.util.UserService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> dataList = new ArrayList<>();

        String query = "select id, fullName, username, role, has_contract from javaee_team_nine.users;";
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

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

    @Override
    public User getUserById(int id){
        String query = "SELECT fullName, username, dob, role, federal_state FROM javaee_team_nine.users WHERE id = ?;";
        User user= null;
        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("resultSet++++" + resultSet);

            while (resultSet.next()){
                String fullName = resultSet.getString("fullName");
                String username = resultSet.getString("username");
                String role = resultSet.getString("role");
                String federalState = resultSet.getString("federal_state");
                Date dob = resultSet.getDate("dob");

                user = new User(id, fullName, username,role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateUserContractStatus(User existingUser, boolean hasContract) {
        boolean rowUpdated = false;

        try (Connection connection = dbService.initDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE javaee_team_nine.users SET has_contract = ? WHERE id = ?;");
            preparedStatement.setBoolean(1, hasContract);
            preparedStatement.setInt(2, existingUser.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public String generateMD5(String s) {
        MessageDigest digest = null;
        String hashtext = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] messageDigest = digest.digest(s.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hashtext;
    }
}
