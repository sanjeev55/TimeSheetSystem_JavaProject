package com.javaee.javaee2022teamnine.controller;


import com.javaee.javaee2022teamnine.dao.RegisterDao;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;
import com.javaee.javaee2022teamnine.util.ReminderUtility;
import com.javaee.javaee2022teamnine.util.UserService;
import com.javaee.javaee2022teamnine.util.impl.UserServiceImpl;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    DBConnectionService dbService = new DBConnectionService();

    UserService userService = new UserServiceImpl();

    public DBConnectionService getDbService() {
        return dbService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public RegisterController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Served at: ").append(req.getContextPath());

        RequestDispatcher dispatcher = req.getRequestDispatcher("Register.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String tos = request.getParameter("tos");
        String federalState = request.getParameter("federal_state");
        String role = request.getParameter("roles");

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(userService.generateMD5(password));
        user.setDob(Date.valueOf(dob));
        user.setTos(Boolean.parseBoolean(tos));
        user.setFederalState(federalState);
        user.setRole(role);
        user.setHasContract(false);

        RegisterDao registerDao = new RegisterDao();

        registerDao.registerUser(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    static{
        runCronJob();
    }
    public static void runCronJob() {
        ReminderUtility.runTimer();
    }
}
