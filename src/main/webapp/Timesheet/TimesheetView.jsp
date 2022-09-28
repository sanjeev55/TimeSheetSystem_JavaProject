<%@ page import="com.javaee.javaee2022teamnine.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.javaee.javaee2022teamnine.model.TimeSheet" %>
<%--
  Created by IntelliJ IDEA.
  User: BMS-PC
  Date: 9/28/2022
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java EE TSS - Your Timesheet</title>
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
            <li><a href="<%=request.getContextPath()%>/timesheet/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else if (u != null && u.getRole().equals("Secretary")) { %>
            <li><a href="<%=request.getContextPath()%>/users-list" class="nav-link">Create Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/contract-list" class="nav-link">Edit/Delete Contract</a>
            <li><a href="<%=request.getContextPath()%>/print-timesheet" class="nav-link">Print Timesheet</a>
            <li><a href="<%=request.getContextPath()%>/archive-timesheet" class="nav-link">Archive Timesheet</a>
            </li>
            <li><a href="<%=request.getContextPath()%>/timesheet/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else if (u != null && u.getRole().equals("Supervisor")) { %>
            <li><a href="<%=request.getContextPath()%>/sign-timesheet" class="nav-link">Sign Timesheet</a></li>
            <li><a href="<%=request.getContextPath()%>/timesheet/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else { %>
            <li><a href="#" class="nav-link">View Contract</a></li>
            <li><a href="#" class="nav-link">Report Work</a></li>
            <li><a href="#" class="nav-link">Sign Timesheet</a></li>
            <li><a href="<%=request.getContextPath()%>/timesheet/view-timesheet" class="nav-link">View Timesheet</a>
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
        <h3 class="text-center">Create Contract</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <%
                List<TimeSheet> timeSheets = (List<TimeSheet>) request.getAttribute("listUser");

            %>
            <thead>
            <tr>
                <th>Timesheet Id</th>
                <th>Status</th>
                <th>Start Date</th>
                <th>End Date</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (TimeSheet timeSheet : timeSheets) {
//                    if (!timeSheet.isHasContract()) {
            %>
            <tr>
                <td>
                    <%=timeSheet.getTimesheetId()%>
                </td>
                <td><%=timeSheet.getTimesheetStatus()%>
                </td>
                <td><%=timeSheet.getTimesheetStartDate()%>
                </td>
                <td><%=timeSheet.getTimesheetEndDate()%>
                </td>
                <%--<td>
                    <a href="${pageContext.request.contextPath}/create?id=<%=t.getId()%>">
                        <button class="btn" style="background-color: #4CAF50; color: white;">
                            Create Contract
                        </button>
                    </a>
                </td>--%>
            </tr>
            <%
                }
            %>
            <%--
                        <div class="alert alert-success" role="alert">
                            No contracts to <span class="badge badge-success">CREATE</span> !
                            You will be able to <span class="badge badge-success">CREATE</span> new contracts after new users have
                            registered to the TSS!
                        </div>
            --%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
