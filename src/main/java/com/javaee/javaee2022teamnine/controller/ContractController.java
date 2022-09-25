package com.javaee.javaee2022teamnine.controller;

import com.javaee.javaee2022teamnine.dao.ContractDao;
import com.javaee.javaee2022teamnine.enums.TimesheetFrequency;
import com.javaee.javaee2022teamnine.model.Contract;
import com.javaee.javaee2022teamnine.model.ContractStatus;
import com.javaee.javaee2022teamnine.model.User;
import com.javaee.javaee2022teamnine.util.ContractService;
import com.javaee.javaee2022teamnine.util.DateService;
import com.javaee.javaee2022teamnine.util.UserService;
import com.javaee.javaee2022teamnine.util.impl.ContractServiceImpl;
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
import java.util.Random;


@WebServlet(urlPatterns = {"/"})
public class ContractController extends HttpServlet {
    ContractService contractService = new ContractServiceImpl();
    UserService userService = new UserServiceImpl();
    DateService dateService = new DateService();

    String role = "EMPLOYEE";

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
            case "/terminate-contract":
                terminateContract(req, resp);
                break;
            case "/view-contract":
                viewContract(req, resp);
                break;
            default:
                listUsers(req, resp);
                break;
        }


        /*switch (user.getRole()) {
            case "SUPERVISOR": {
                RequestDispatcher dispatcher = req.getRequestDispatcher("Contract/ContractSupervisor.jsp");

                if (req.getParameter("createContract") != null) {
                    req.getRequestDispatcher("Contract/CRUD/Create.jsp").forward(req, resp);
                }
                dispatcher.forward(req, resp);
                break;
            }
            case "ASSISTANT": {
                RequestDispatcher dispatcher = req.getRequestDispatcher("Contract/ContractAssistant.jsp");
//                processRequest(req, resp);
                try {
                    if ("/create".equals(action)) {
                        createContract(req, resp);
                    } else if ("/list".equals(action)){
                        listUsers(req, resp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dispatcher.forward(req, resp);
                break;
            }
            case "SECRETARY": {
                RequestDispatcher dispatcher = req.getRequestDispatcher("Contract/ContractSecretary.jsp");
                dispatcher.forward(req, resp);
                break;
            }
            case "EMPLOYEE": {
                RequestDispatcher dispatcher = req.getRequestDispatcher("Contract/ContractEmployee.jsp");
                dispatcher.forward(req, resp);
                break;
            }
            default:
                RequestDispatcher dispatcher = req.getRequestDispatcher("Contract.jsp");
                dispatcher.forward(req, resp);
        }*/

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Test: driver code
//        User user = new User("Biraj Man Singh", "bmsingh@uni-koblenz.de", "SUPERVISOR");
        User user = new User("Biraj Man Singh", "bmsingh@uni-koblenz.de", "ASSISTANT");
//        User user = new User("Biraj Man Singh", "bmsingh@uni-koblenz.de", "SECRETARY");
//        User user = new User("Biraj Man Singh", "bmsingh@uni-koblenz.de", "EMPLOYEE");

        doGet(req, resp);
    }


    private void createContract(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String id = req.getParameter("id");
        // 7
        User user = userService.getUserById(Integer.parseInt(id));
        Contract contract = new Contract();

        contract.setStatus(new ContractStatus(1));
        contract.setName(user.getFullName());
        contract.setUserId(Integer.parseInt(id));
//        contract.setStartDate(new Date());
        contract.setStartDate(dateService.dateToday());
        contract.setEndDate(dateService.add2YearsToDate());
        contract.setFrequency(TimesheetFrequency.generateRandomTf().name());
//        contract.setFrequency("MONTHLY");
        contract.setHoursPerWeek(contractService.calculateHoursPerWeek());
        contract.setVacationHours(contractService.calculateVacationHours(contract.getStartDate(), contract.getEndDate()));
        contract.setWorkingDaysPerWeek(contractService.calculateWorkingDaysPerWeek());
        contract.setVacationDaysPerYear(contractService.calculateVacationDaysPerYear());

        ContractDao contractDao = new ContractDao();
        contractDao.createContract(contract);

        resp.sendRedirect("list");
    }

    protected void viewContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        int userId = u.getId();

        Contract contract = contractService.getContractByUserId(userId);
        System.out.println(contract.getStatus().getContractStatus());
        RequestDispatcher dispatcher = request.getRequestDispatcher("Contract/ContractView.jsp");
        request.setAttribute("contract", contract);
        request.setAttribute("user", u);
        dispatcher.forward(request, response);

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

//        Contract contract = new Contract(id, name, startDate, endDate, hoursPerWeek, vacationHours, workingDaysPerWeek, vacationDaysPerYear);
        contractService.updateContract(contract);
        response.sendRedirect("contract-list");
    }


    private void deleteContract(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("contract_id"));
        contractService.deleteContract(id);
        response.sendRedirect("contract-list");
    }


    private void startContract(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Contract> contracts = contractService.getContractList();
        request.setAttribute("listContractToStart", contracts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Contract/ContractStart.jsp");
        dispatcher.forward(request, response);

        String id = request.getParameter("contract_id");
        Contract existingContract = contractService.getContractById(Integer.parseInt(id));

//        if (existingContract.getStatus().getId() == 1) {
        existingContract.setStatus(new ContractStatus(2));
//        }

        contractService.startContract(existingContract);
        response.sendRedirect("start-contract");
    }


    private void terminateContract(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contract> contracts = contractService.getStartedContractList();
        request.setAttribute("listContractToStart", contracts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Contract/ContractTerminate.jsp");
        dispatcher.forward(request, response);

        String id = request.getParameter("contract_id");
        Contract existingContract = contractService.getContractById(Integer.parseInt(id));

//        if (existingContract.getStatus().getId() == 1) {
        existingContract.setStatus(new ContractStatus(3));
        existingContract.setTerminationDate(dateService.dateToday());
//        }

        contractService.terminateContract(existingContract);
        response.sendRedirect("terminate-contract");
    }


}
