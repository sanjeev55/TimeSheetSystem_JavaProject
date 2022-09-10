package com.javaee.javaee2022teamnine.controller;


import com.javaee.javaee2022teamnine.dao.RegisterDao;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterController extends HttpServlet {

    public RegisterController() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Copying all the input parameters in to local variables
        String fullName = request.getParameter("fullname");
        System.out.println(fullName);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String tos = request.getParameter("tos");
        String role = request.getParameter("role");

        User user = new User();
        //Using Java Beans - An easiest way to play with group of related data
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setDob(Date.valueOf(dob));
        user.setTos(Boolean.parseBoolean(tos));
        user.setRole(role);


        RegisterDao registerDao = new RegisterDao();

        //The core Logic of the Registration application is present here. We are going to insert user data in to the database.
        String userRegistered = registerDao.registerUser(user);

        if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
        {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else   //On Failure, display a meaningful message to the User.
        {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/Register.jsp").forward(request, response);
        }
    }
}
