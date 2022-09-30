<%@ page import="com.javaee.javaee2022teamnine.model.User" %>
<%@ page import="com.javaee.javaee2022teamnine.model.Contract" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: BMS-PC
  Date: 9/29/2022
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Print Timesheet</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
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
            <li><a href="<%=request.getContextPath()%>/view-contract" class="nav-link">View Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/users-list" class="nav-link">Create Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/contract-list" class="nav-link">Edit/Delete Contract</a>
            </li>
            <li><a href="<%=request.getContextPath()%>/start-contract" class="nav-link">Start Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/terminate-contract" class="nav-link">Terminate
                Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else if (u != null && u.getRole().equals("Secretary")) { %>
            <li><a href="<%=request.getContextPath()%>/view-contract" class="nav-link">View Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/users-list" class="nav-link">Create Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/contract-list" class="nav-link">Edit/Delete Contract</a>
            <li><a href="<%=request.getContextPath()%>/print-timesheet" class="nav-link">Print Timesheet</a>
            <li><a href="<%=request.getContextPath()%>/archive-timesheet" class="nav-link">Archive Timesheet</a>
            </li>
            <li><a href="<%=request.getContextPath()%>/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else if (u != null && u.getRole().equals("Supervisor")) { %>
            <li><a href="<%=request.getContextPath()%>/view-contract" class="nav-link">View Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/sign-timesheet-employee" class="nav-link">Sign Timesheet</a></li>
            <li><a href="<%=request.getContextPath()%>/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else { %>
            <li><a href="<%=request.getContextPath()%>/view-contract" class="nav-link">View Contract</a></li>
            <li><a href="#" class="nav-link">Report Work</a></li>
            <%if (u.isHasContract()) {%>
            <li><a href="<%=request.getContextPath()%>/view-timesheet" class="nav-link">View Timesheet</a>
            </li>
            <%}%>
            <% } %>
        </ul>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-info btn-">
            Logout
        </a>
    </nav>
</header>

<div class="row">

    <div class="container">
        <br/>
        <h3 class="text-center">Your Time Sheets</h3>
        <hr>
        <br>
        <i>Logged in as:</i> <b><%=u.getEmail()%>
    </b>
        <br/>
        <br/>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Action</th>
            </tr>
            </thead>
            <%
                List<Contract> contracts = (List<Contract>) request.getAttribute("listContract");
                List<User> users = (List<User>) request.getAttribute("listUsers");

                for (User user : users) {
                    for (Contract contract : contracts) {
                        if ((contract.getUserId() == user.getId()) && (contract.isHasTimesheet())) {
            %>

            <tbody>
            <tr>
                <td>
                    <%=user.getFullName()%>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/print-pdf?ts_contract_id=<%=contract.getId()%>">
                        <button class="btn dell" style="background-color: #4CAF50; color: white;">
                            Print Timesheet
                        </button>
                    </a>
                </td>
            </tr>
            </tbody>

            <%
                        }
                    }
                }
            %>
        </table>
    </div>
</div>

</body>
</html>
