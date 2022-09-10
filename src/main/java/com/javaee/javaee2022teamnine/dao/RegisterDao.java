package com.javaee.javaee2022teamnine.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.javaee.javaee2022teamnine.model.User;


public class RegisterDao {

    Connection connection;
    public String registerUser(User user)
    {
        String fullName = user.getFullName();
        String email = user.getEmail();
        String password = user.getPassword();
        Date dob = user.getDob();
        String role = user.getRole();
        Boolean tos = user.getTos();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        try
        {

            String query = "insert into users(SlNo,fullName,Email,password,dob,tos,role) values (NULL,?,?,?,?,?,?,)"; //Insert user details into the table 'USERS'
            preparedStatement = connection.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, String.valueOf(dob));
            preparedStatement.setString(5, String.valueOf(tos));
            preparedStatement.setString(6, role);

            int i= preparedStatement.executeUpdate();

            if (i!=0)  //Just to ensure data has been inserted into the database
                return "SUCCESS";
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
    }
}