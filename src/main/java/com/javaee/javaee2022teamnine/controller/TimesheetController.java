package com.javaee.javaee2022teamnine.controller;

import com.javaee.javaee2022teamnine.model.Contract;
import com.javaee.javaee2022teamnine.model.TimeSheet;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.TimesheetService;
import com.javaee.javaee2022teamnine.util.impl.TimesheetServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/timesheet/view-timesheet", "/view-timesheet"})
public class TimesheetController extends HttpServlet {
    TimesheetService timesheetService = new TimesheetServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().append("Served at: ").append(req.getContextPath());
        String action = req.getServletPath();

        if ("/view-timesheet".equals(action)) {
            System.out.println("action--" + action);
            viewTimesheet(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }


    private void viewTimesheet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TimeSheet> timeSheets = timesheetService.listTimesheet();
        request.setAttribute("listTimesheet", timeSheets);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Timesheet/TimesheetView.jsp");
        dispatcher.forward(request, response);
    }
}