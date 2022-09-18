package com.javaee.javaee2022teamnine.controller;


import com.javaee.javaee2022teamnine.dao.LoginDao;
import com.javaee.javaee2022teamnine.dao.RegisterDao;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.DBConnectionService;

import java.io.IOException;
import java.sql.Date;
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
    /*
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Served at: ").append(req.getContextPath());

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    } */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      HttpSession session = request.getSession();
      request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		LoginDao logindao = LoginDao.getInstance();
		boolean loginResult = logindao.login(email, password);
		
		if (loginResult == true) {
			request.setAttribute("loginResult", "true");
			HttpSession session1 = request.getSession();
			session1.setAttribute("sessionID", email);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("loginResult", null);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
		}
		
	}
	




    }

