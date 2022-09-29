package com.javaee.javaee2022teamnine.controller;

import com.javaee.javaee2022teamnine.model.Contract;
import com.javaee.javaee2022teamnine.model.TimeSheet;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.ContractService;
import com.javaee.javaee2022teamnine.util.TimesheetService;
import com.javaee.javaee2022teamnine.util.UserService;
import com.javaee.javaee2022teamnine.util.impl.ContractServiceImpl;
import com.javaee.javaee2022teamnine.util.impl.TimesheetServiceImpl;
import com.javaee.javaee2022teamnine.util.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/timesheet/view-timesheet", "/view-timesheet", "/archive-timesheet","/archive"})
public class TimesheetController extends HttpServlet {
    TimesheetService timesheetService = new TimesheetServiceImpl();

    ContractService contractService = new ContractServiceImpl();

    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().append("Served at: ").append(req.getContextPath());
        String action = req.getServletPath();

        if ("/view-timesheet".equals(action)) {
            System.out.println("action--" + action);
            viewTimesheet(req, resp);
        }
        else if ("/archive-timesheet".equals(action)){
            archiveTimesheet(req, resp);
        } else if ("/archive".equals(action)) {
            System.out.println("action--" + action);
            updateTimeSheet(req,resp);
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
        for (TimeSheet time: timeSheets){
            System.out.println(time.getContractId());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("Timesheet/TimesheetView.jsp");

        request.setAttribute("user", u);
        request.setAttribute("contract", contract);
        request.setAttribute("listTimesheet", timeSheets);

        dispatcher.forward(request, response);
    }

    private void archiveTimesheet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        List<TimeSheet> signedTimeSheet = timesheetService.getSignedTimeSheet();
        HashMap<TimeSheet,User> timeSheetUser=new HashMap<>();
        RequestDispatcher dispatcher = request.getRequestDispatcher("Timesheet/ArchiveTimeSheetView.jsp");

        for(TimeSheet sheet: signedTimeSheet){

            int contractID = sheet.getContractId();
            Contract contract = contractService.getContractById(contractID);

            int userId = contract.getUserId();
            User user = userService.getUserById(userId);

            timeSheetUser.put(sheet,user);
        }
        System.out.println(timeSheetUser);

        request.setAttribute("signedTimeSheets",timeSheetUser);
        dispatcher.forward(request,response);

    }

    private void updateTimeSheet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timeSheetId = request.getParameter("id");
        timesheetService.updateTimeSheetByID(Integer.parseInt(timeSheetId));

        response.sendRedirect("archive-timesheet");
    }
}