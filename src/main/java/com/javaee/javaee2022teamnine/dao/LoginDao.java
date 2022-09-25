package com.javaee.javaee2022teamnine.dao;

import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;

import java.sql.*;

public class LoginDao {


    public LoginDao() {
    }

    DBConnectionService dbService = new DBConnectionService();

    public DBConnectionService getDbService() {
        return dbService;
    }


    private static LoginDao instance = new LoginDao();

    public static LoginDao getInstance() {
        return instance;
    }


    /**
     * @param email Email entered from view
     * @param password Password entered from view
     * @return Logs in the user and redirects them to Dashboard.jsp. Maintains session
     */
    public User login(String email, String password) {
        boolean b = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try (Connection connection = dbService.initDB()) {

            String sql = "SELECT * FROM javaee_team_nine.users WHERE username=? AND password=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();


            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("fullName"));
                user.setEmail(email);
                user.setRole(rs.getString("role"));
                user.setDob(Date.valueOf(rs.getString("dob")));
                user.setFederalState(rs.getString("federal_state"));

//                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        return b;
        return user;
    }

}
