<%@ page import="com.javaee.javaee2022teamnine.model.Contract" %>
<%@ page import="java.util.List" %>
<%@ page import="com.javaee.javaee2022teamnine.model.User" %><%--
  Created by IntelliJ IDEA.
  User: BMS-PC
  Date: 9/19/2022
  Time: 9:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start Contracts</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>/Dashboard.jsp" class="navbar-brand"> Time Sheet System
                Application </a>
        </div>
        <ul class="navbar-nav mr-auto">
            <%
                User u = (User) session.getAttribute("user");
                if (u != null && u.getRole().equals("Assistant")) {
            %>
            <li><a href="<%=request.getContextPath()%>/users-list" class="nav-link">Create Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/contract-list" class="nav-link">Edit/Delete Contract</a>
            </li>
            <li><a href="<%=request.getContextPath()%>/start-contract" class="nav-link">Start Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/terminate-contract" class="nav-link">Terminate
                Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else if (u != null && u.getRole().equals("Secretary")) { %>
            <li><a href="<%=request.getContextPath()%>/users-list" class="nav-link">Create Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/contract-list" class="nav-link">Edit/Delete Contract</a>
            <li><a href="<%=request.getContextPath()%>/print-timesheet" class="nav-link">Print Timesheet</a>
            <li><a href="<%=request.getContextPath()%>/archive-timesheet" class="nav-link">Archive Timesheet</a>
            </li>
            <li><a href="<%=request.getContextPath()%>/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else if (u != null && u.getRole().equals("Supervisor")) { %>
            <li><a href="<%=request.getContextPath()%>/sign-timesheet" class="nav-link">Sign Timesheet</a></li>
            <li><a href="<%=request.getContextPath()%>/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else { %>
            <li><a href="#" class="nav-link">View Contract</a></li>
            <li><a href="#" class="nav-link">Report Work</a></li>
            <li><a href="#" class="nav-link">Sign Timesheet</a></li>
            <li><a href="<%=request.getContextPath()%>/view-timesheet" class="nav-link">View Timesheet</a>
            </li>
            <% } %>
        </ul>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-info btn-">
            Logout
        </a>
    </nav>
</header>
<br/>
<div class="row">
    <div class="container">
        <h3 class="text-center">Start Contract</h3>
        <hr>
        <br/>
        <table class="table table-bordered" id="tbT">
            <%
                List<Contract> contracts = (List<Contract>) request.getAttribute("listContractToStart");
                if (contracts.size() != 0) {
            %>
            <thead>
            <tr>
                <th>Contract ID</th>
                <th>Contract Holders Name</th>
                <th>Contract Status</th>
                <th>Start Date</th>
                <th>End Date</th>
            </tr>
            </thead>
            <% for (Contract contract : contracts) {
            %>
            <tbody>
            <tr>
                <td>
                    <%=contract.getId()%>
                </td>
                <td><%=contract.getName()%>
                </td>
                <td>
                    <center>
                        <span class="badge badge-primary">PREPARED</span>
                    </center>
                </td>
                <td><%=contract.getStartDate()%>
                </td>
                <td><%=contract.getEndDate()%>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/start-contract?contract_id=<%=contract.getId()%>">
                        <button class="btn dell" style="background-color: #008CBA; color: white;">
                            Start Contract
                        </button>
                    </a>

                </td>
            </tr>
            </tbody>
            <%
                }
            } else {
            %>
            <div class="alert alert-success" role="alert">
                No contracts to <span class="badge badge-info">START</span> !
                Wait for contracts to be <span class="badge badge-success">CREATED</span> !
            </div>
            <%
                }
            %>
        </table>
    </div>
</div>
</body>
</html>
