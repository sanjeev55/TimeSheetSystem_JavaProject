package com.javaee.javaee2022teamnine.util;

import com.javaee.javaee2022teamnine.model.User;

import java.sql.*;


public class DBConnectionService {
    Connection connection;
    Statement statement;


    public DBConnectionService() {
        try {
            String url = "jdbc:mariadb://localhost:3306/javaee";
            String user = "root";
            String password = "%password%";

            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            String userTable = "users";

            String sql = "CREATE TABLE IF NOT EXISTS " + userTable
                    + " (id INTEGER not NULL AUTO_INCREMENT PRIMARY KEY , "
                    + " fullName VARCHAR(255), "
                    + " username VARCHAR(255), "
                    + " password VARCHAR(255), "
                    + " dob DATE, "
                    + " tos BOOLEAN, "
                    + " role VARCHAR(10))";

            String roleSql = "CREATE TABLE IF NOT EXISTS roles " +
                    "(id int NOT NULL, " +
                    "role VARCHAR(10)," +
                    "PRIMARY KEY (id))";

            String roleInsert = "INSERT IGNORE roles(id, role) VALUES " +
                    "(1, 'EMPLOYEE')," +
                    "(2, 'ASSISTANT')," +
                    "(3, 'SECRETARY')," +
                    "(4, 'SUPERVISOR')";

            statement.executeUpdate(sql);
            statement.executeUpdate(roleSql);
            statement.executeUpdate(roleInsert);
            System.out.println("Table " + userTable + " has been created!");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User registerUser(User user) {
        String insertQuery = "Insert into users(fullname, username, password, tos, role) values(?, ?, ?, ? ,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(insertQuery);
//            ps.setInt(1, user.getId());
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
//            ps.setDate(5, user.getDob());
            ps.setBoolean(4, user.isTos());
            ps.setString(5, user.getRole());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
