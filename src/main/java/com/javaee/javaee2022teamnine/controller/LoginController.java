package com.javaee.javaee2022teamnine.controller;


import com.javaee.javaee2022teamnine.dao.LoginDao;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;
import com.javaee.javaee2022teamnine.util.UserService;
import com.javaee.javaee2022teamnine.util.impl.UserServiceImpl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    DBConnectionService dbService = new DBConnectionService();
    UserService userService = new UserServiceImpl();

    public DBConnectionService getDbService() {
        return dbService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public LoginController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Served at: ").append(req.getContextPath());

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = userService.generateMD5(request.getParameter("password"));

        LoginDao logindao = new LoginDao();

        User user = logindao.login(email, password);
        String destinationPage = "index.jsp";

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            destinationPage = "Dashboard.jsp";
        } else {
            String message = "Invalid email/password";
            request.setAttribute("message", message);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
        dispatcher.forward(request, response);
    }
}