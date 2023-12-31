package com.javaee.javaee2022teamnine.controller;

import com.javaee.javaee2022teamnine.dao.ContractDao;
import com.javaee.javaee2022teamnine.enums.TimesheetFrequency;
import com.javaee.javaee2022teamnine.model.Contract;
import com.javaee.javaee2022teamnine.model.ContractStatus;
import com.javaee.javaee2022teamnine.model.TimeSheet;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.ContractService;
import com.javaee.javaee2022teamnine.util.DateService;
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
import java.sql.Date;
import java.util.List;


@WebServlet(urlPatterns = {"/"})
public class ContractController extends HttpServlet {
    ContractService contractService = new ContractServiceImpl();
    UserService userService = new UserServiceImpl();
    DateService dateService = new DateService();

    TimesheetService timesheetService = new TimesheetServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Served at: ").append(req.getContextPath());
        String action = req.getServletPath();

        switch (action) {
            case "/create":
                createContract(req, resp);
                break;
            case "/edit":
                showEditContractForm(req, resp);
                break;
            case "/update":
                updateContract(req, resp);
                break;
            case "/delete":
                deleteContract(req, resp);
                break;
            case "/contract-list":
                listContracts(req, resp);
                break;
            case "/start-contract":
                startContract(req, resp);
                break;
            case "/start":
                startContractUpdate(req, resp);
                break;
            case "/terminate-contract":
                terminateContract(req, resp);
                break;
            case "/terminate":
                terminateContractUpdate(req, resp);
                break;
            case "/view-contract":
                viewContract(req, resp);
                break;
            default:
                listUsers(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    private void createContract(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        User user = userService.getUserById(Integer.parseInt(id));

        Contract contract = new Contract();

        contract.setStatus(new ContractStatus(1));
        contract.setName(user.getFullName());
        contract.setUserId(Integer.parseInt(id));
        contract.setStartDate(dateService.dateToday());
        contract.setEndDate(dateService.add2YearsToDate());
//        contract.setFrequency(TimesheetFrequency.generateRandomTf().name());
        contract.setFrequency("WEEKLY");
        contract.setHoursPerWeek(contractService.calculateHoursPerWeek());
        contract.setVacationHours(contractService.calculateVacationHours(contract.getStartDate(), contract.getEndDate()));
        contract.setWorkingDaysPerWeek(contractService.calculateWorkingDaysPerWeek());
        contract.setVacationDaysPerYear(contractService.calculateVacationDaysPerYear());

        userService.updateUserContractStatus(userService.getUserById(Integer.parseInt(id)), true);
        ContractDao contractDao = new ContractDao();
        contractDao.createContract(contract);


        resp.sendRedirect("list");
    }

    protected void viewContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        System.out.println("has contract:"+ u.isHasContract());

        if (u.isHasContract()) {
            int userId = u.getId();
            Contract contract = contractService.getContractByUserId(userId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Contract/ContractView.jsp");
            request.setAttribute("contract", contract);
            request.setAttribute("user", u);
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Contract/ContractView.jsp");
            request.setAttribute("user", u);
            dispatcher.forward(request, response);
        }

    }


    protected void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        List<Contract> contracts = contractService.getContractList();
        request.setAttribute("listUser", users);
        request.setAttribute("listContract", contracts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Contract/ContractCreate.jsp");
        dispatcher.forward(request, response);
    }


    private void listContracts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contract> contracts = contractService.getContractList();
        request.setAttribute("listContract", contracts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Contract/ContractList.jsp");
        dispatcher.forward(request, response);

    }


    private void showEditContractForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("contract_id");
        Contract existingContract = contractService.getContractById(Integer.parseInt(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("Contract/ContractEdit.jsp");
        request.setAttribute("contract", existingContract);
        dispatcher.forward(request, response);
    }


    private void updateContract(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("contract_id"));
        String name = request.getParameter("name");
        int vacationDaysPerYear = Integer.parseInt(request.getParameter("vacation_days_per_year"));

        String startDate = request.getParameter("start_date");
        String endDate = request.getParameter("end_date");
        String hoursPerWeek = request.getParameter("hours_per_week");
        String vacationHours = request.getParameter("vacation_hours");
        String workingDaysPerWeek = request.getParameter("working_days_per_week");

//        Contract contract = new Contract(id, name, vacationDaysPerYear);
        Contract contract = new Contract();
        contract.setId(id);
        contract.setName(name);
        contract.setStartDate(Date.valueOf(startDate));
        contract.setEndDate(Date.valueOf(endDate));
        contract.setHoursPerWeek(Double.parseDouble(hoursPerWeek));
        contract.setVacationHours(Double.parseDouble((vacationHours)));
        contract.setWorkingDaysPerWeek(Integer.parseInt(workingDaysPerWeek));
        contract.setVacationDaysPerYear(vacationDaysPerYear);

        contractService.updateContract(contract);
        response.sendRedirect("contract-list");
    }


    private void deleteContract(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("contract_id"));
        Contract contract = contractService.getContractById(id);
        User user = userService.getUserById(contract.getUserId());
        userService.updateUserContractStatus(user,false);
        contractService.deleteContract(id);
        response.sendRedirect("contract-list");
    }


    private void startContract(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Contract> contracts = contractService.getContractList();
        request.setAttribute("listContractToStart", contracts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Contract/ContractStart.jsp");
        dispatcher.forward(request, response);
    }

    private void startContractUpdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("contract_id");
        Contract existingContract = contractService.getContractById(Integer.parseInt(id));

        existingContract.setStatus(new ContractStatus(2));
        existingContract.setHasTimesheet(true);



        TimeSheet timeSheet = new TimeSheet();
        List<List<Date>> dates = DateService.datesForStartAndEndOfWeek(
                (Date) existingContract.getStartDate(),
                (Date) existingContract.getEndDate()
        );

        for (List<Date> combo :
                dates) {
            if (combo.size() == 2) {
                timeSheet.setTimesheetStartDate(combo.get(0));
                timeSheet.setTimesheetEndDate(combo.get(1));
                timeSheet.setTimesheetStatus("IN_PROGRESS");
                timeSheet.setContractId(Integer.parseInt(id));
                timeSheet.setHoursDue(contractService.calculateHoursDue(5,0,existingContract.getHoursPerWeek(),existingContract.getWorkingDaysPerWeek()));

                timesheetService.createTimesheet(timeSheet);
            }
        }
        existingContract.setHoursDue(timesheetService.calculateTotalHoursDue(Integer.parseInt(id)));
        contractService.startContract(existingContract);
        response.sendRedirect("start-contract");
    }


    private void terminateContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contract> contracts = contractService.getStartedContractList();
        request.setAttribute("listContractToStart", contracts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Contract/ContractTerminate.jsp");
        dispatcher.forward(request, response);
    }

    private void terminateContractUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("contract_id");
        System.out.println("Contract Id========"+ id);
        Contract existingContract = contractService.getContractById(Integer.parseInt(id));
        User user = userService.getUserById(existingContract.getUserId());

        existingContract.setStatus(new ContractStatus(3));
        existingContract.setTerminationDate(dateService.dateToday());
        existingContract.setHasTimesheet(false);

        contractService.terminateContract(existingContract);
        userService.updateUserContractStatus(user,false);

        timesheetService.deleteTimesheetIfContractTerminated(existingContract.getId());

        response.sendRedirect("terminate-contract");
    }


}
