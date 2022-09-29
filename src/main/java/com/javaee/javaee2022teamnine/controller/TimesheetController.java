package com.javaee.javaee2022teamnine.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.util.stream.Stream;

@WebServlet(urlPatterns = {
        "/timesheet/view-timesheet",
        "/view-timesheet",
        "/archive-timesheet",
        "/archive",
        "/print-timesheet",
        "/print-pdf"
})
public class TimesheetController extends HttpServlet {
    TimesheetService timesheetService = new TimesheetServiceImpl();

    ContractService contractService = new ContractServiceImpl();

    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().append("Served at: ").append(req.getContextPath());
        String action = req.getServletPath();

        if ("/view-timesheet".equals(action)) {
            viewTimesheet(req, resp);
        } else if ("/archive-timesheet".equals(action)) {
            archiveTimesheet(req, resp);
        } else if ("/archive".equals(action)) {
            updateTimeSheet(req, resp);
        } else if ("/print-timesheet".equals(action)) {
            printTimeSheet(req, resp);
        } else if ("/print-pdf".equals(action)) {
            printTimeSheetPdf(req, resp);
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
        for (TimeSheet time : timeSheets) {
            System.out.println(time.getContractId());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("Timesheet/TimesheetView.jsp");

        request.setAttribute("user", u);
        request.setAttribute("contract", contract);
        request.setAttribute("listTimesheet", timeSheets);

        dispatcher.forward(request, response);
    }

    private void archiveTimesheet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TimeSheet> signedTimeSheet = timesheetService.getSignedTimeSheet();
        HashMap<TimeSheet, User> timeSheetUser = new HashMap<>();
        RequestDispatcher dispatcher = request.getRequestDispatcher("Timesheet/ArchiveTimeSheetView.jsp");

        for (TimeSheet sheet : signedTimeSheet) {

            int contractID = sheet.getContractId();
            Contract contract = contractService.getContractById(contractID);

            int userId = contract.getUserId();
            User user = userService.getUserById(userId);

            timeSheetUser.put(sheet, user);
        }
        System.out.println(timeSheetUser);

        request.setAttribute("signedTimeSheets", timeSheetUser);
        dispatcher.forward(request, response);

    }

    private void updateTimeSheet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timeSheetId = request.getParameter("id");
        timesheetService.updateTimeSheetByID(Integer.parseInt(timeSheetId));

        response.sendRedirect("archive-timesheet");
    }


    private void printTimeSheet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        List<Contract> contracts = contractService.getStartedContractList();

        RequestDispatcher dispatcher = request.getRequestDispatcher("Timesheet/TimeSheetPrint.jsp");

        request.setAttribute("listUsers", users);
        request.setAttribute("listContract", contracts);

        dispatcher.forward(request, response);

    }

    private void printTimeSheetPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("ts_contract_id");
        Contract existingContract = contractService.getContractById(Integer.parseInt(id));

        List<TimeSheet> timesheet = timesheetService.getTimesheetByContractId(Integer.parseInt(id));

        for (TimeSheet ts : timesheet) {
            if (Integer.parseInt(id) == ts.getContractId()) {
                response.setContentType("application/pdf");
                response.setHeader(
                        "Content-disposition",
                        "inline; filename='Downloaded.pdf'");

                try {

                    Document document = new Document();

                    PdfWriter.getInstance(
                            document, response.getOutputStream());

                    document.open();
                    Font regular = new Font(Font.FontFamily.HELVETICA, 12);
                    Font bold = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);

                    document.add(new Paragraph("TSS Team Nine: Your Timesheet\n", bold));
                    document.add(new Paragraph("User ID: " + existingContract.getUserId() + "\n" +
                            "Contract ID:" + id + "\n\n\n"));
                    PdfPTable table = new PdfPTable(4);
                    addTableHeader(table);
                    addRows(table, timesheet);

                    document.add(table);
                    document.close();
                } catch (DocumentException | IOException de) {
                    throw new IOException(de.getMessage());
                }
            }
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Timesheet Status", "Timesheet Start Date", "Timesheet End Date", "Contract ID")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<TimeSheet> timeSheets) {
        for (TimeSheet timeSheet : timeSheets) {
            table.addCell("" + timeSheet.getTimesheetStatus());
            table.addCell("" + timeSheet.getTimesheetStartDate());
            table.addCell("" + timeSheet.getTimesheetEndDate());
            table.addCell("" + timeSheet.getContractId());
        }
//        table.addCell("row 1, col 2");
    }


}