<%@ page import="com.javaee.javaee2022teamnine.model.Contract" %>
<%@ page import="java.util.List" %>
<%@ page import="com.javaee.javaee2022teamnine.model.User" %><%--
  Created by IntelliJ IDEA.
  User: BMS-PC
  Date: 9/17/2022
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contracts List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>/Dashboard.jsp" class="navbar-brand"> Time Sheet System Application </a>
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

            <% } else if (u != null && u.getRole().equals("Secretary")) { %>
            <li><a href="<%=request.getContextPath()%>/users-list" class="nav-link">Create Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/contract-list" class="nav-link">Edit/Delete Contract</a>
            <li><a href="<%=request.getContextPath()%>/print-timesheet" class="nav-link">Print Timesheet</a>
            <li><a href="<%=request.getContextPath()%>/archive-timesheet" class="nav-link">Archive Timesheet</a>
            </li>

            <% } else if (u != null && u.getRole().equals("Supervisor")) { %>
            <li><a href="<%=request.getContextPath()%>/sign-timesheet" class="nav-link">Sign Timesheet</a></li>

            <% } else { %>
            <li><a href="<%=request.getContextPath()%>/view-contract?id=<%=u.getId()%>" class="nav-link">View Contract</a></li>
            <li><a href="#" class="nav-link">Report Work</a></li>
            <li><a href="#" class="nav-link">Sign Timesheet</a></li>
            <% } %>
        </ul>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-info btn-">
            <%--            <span class="glyphicon glyphicon-log-out"></span>--%>
            Logout
        </a>
    </nav>
</header>
<br/>
<div class="row">
    <div class="container">
        <h3 class="text-center">Edit/ Delete Contract</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <%
                List<Contract> contracts = (List<Contract>) request.getAttribute("listContract");
                if (contracts.size() != 0) {
            %>
            <thead>
            <tr>
                <th>Contract ID</th>
                <th>Contract Holders Name</th>
                <th>Start Date</th>
                <th>End Date</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Contract contract : contracts) {
            %>
            <tr>
                <td>
                    <%=contract.getId()%>
                </td>
                <td><%=contract.getName()%>
                </td>
                <td><%=contract.getStartDate()%>
                </td>
                <td><%=contract.getEndDate()%>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/edit?contract_id=<%=contract.getId()%>">
                        <button class="btn" style="background-color: #008CBA; color: white;">
                            Edit
                        </button>
                    </a>
                    <a href="${pageContext.request.contextPath}/delete?contract_id=<%=contract.getId()%>">
                        <button class="btn" style="background-color: #f44336; color: white;">
                            Delete
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
                No contracts to <span class="badge badge-info">EDIT</span> /
                <span class="badge badge-danger">DELETE</span> !
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
