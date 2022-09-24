/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.javaee.javaee2022teamnine.controller;

import com.javaee.javaee2022teamnine.dao.ResetDao;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reset-password")
public class ResetController extends HttpServlet {
    DBConnectionService dbService = new DBConnectionService();

        public DBConnectionService getDbService() {
            return dbService;
        }
        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
        }
        public ResetController(){
        }
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Served at: ").append(req.getContextPath());

        RequestDispatcher dispatcher = req.getRequestDispatcher("resetpw.jsp");
        dispatcher.forward(req, resp);
        }
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            ResetDao resetDao = new ResetDao();
            resetDao.resetPassword(user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ResetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }


}

