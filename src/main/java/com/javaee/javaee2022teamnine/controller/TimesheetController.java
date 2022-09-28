package com.javaee.javaee2022teamnine.controller;

import com.javaee.javaee2022teamnine.model.Contract;
import com.javaee.javaee2022teamnine.model.TimeSheet;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.ContractService;
import com.javaee.javaee2022teamnine.util.TimesheetService;
import com.javaee.javaee2022teamnine.util.impl.ContractServiceImpl;
import com.javaee.javaee2022teamnine.util.impl.TimesheetServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/timesheet/view-timesheet", "/view-timesheet"})
public class TimesheetController extends HttpServlet {
    TimesheetService timesheetService = new TimesheetServiceImpl();

    ContractService contractService = new ContractServiceImpl();

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
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        int userId = u.getId();

        Contract contract = contractService.getContractByUserId(userId);

        List<TimeSheet> timeSheets = timesheetService.listTimesheet();

        RequestDispatcher dispatcher = request.getRequestDispatcher("Timesheet/TimesheetView.jsp");

        request.setAttribute("user", u);
        request.setAttribute("contract", contract);
        request.setAttribute("listTimesheet", timeSheets);

        dispatcher.forward(request, response);
    }
}