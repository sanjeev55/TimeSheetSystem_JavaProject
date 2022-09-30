<%@ page import="com.javaee.javaee2022teamnine.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.javaee.javaee2022teamnine.model.TimeSheet" %>
<%@ page import="com.javaee.javaee2022teamnine.model.Contract" %>
<%@ page import="com.javaee.javaee2022teamnine.model.TimeSheetEntry" %>
<%--
  Created by IntelliJ IDEA.
  User: Sanjeev
  Date: 9/28/2022
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java EE TSS - Sign TimeSheet</title>
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
            <li><a href="<%=request.getContextPath()%>/sign-time-employee" class="nav-link">Sign Timesheet</a></li>
            <li><a href="<%=request.getContextPath()%>/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else { %>
            <li><a href="<%=request.getContextPath()%>/view-contract" class="nav-link">View Contract</a></li>
            <li><a href="#" class="nav-link">Report Work</a></li>
            <%if (u.isHasContract()){%>
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
<br/>
<div class="row">

    <div class="container">
        <h3 class="text-center">Your Timesheet for a Week</h3>
        <hr>
        <br>
        <%=u.getEmail()%>
        <table class="table table-bordered">
            <%
                List<TimeSheetEntry> timeSheetEntryList = (List<TimeSheetEntry>) request.getAttribute("timeSheetEntryList");

            %>
            <thead>
            <tr>
                <th>Timesheet Entry Id</th>
                <th>Timesheet Id</th>
                <th>Report Type</th>
                <th>Description</th>
                <th>Hours Due</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Date</th>
                <th>Sign</th>

            </tr>
            </thead>
            <tbody>
            <%
                for (TimeSheetEntry timeSheetEntry : timeSheetEntryList) {
            %>
            <tr>
                <td>
                    <%=timeSheetEntry.getTimesheetEntryId()%>
                </td>
                <td>
                    <%=timeSheetEntry.getTimesheetId()%>
                </td>
                <td><%=timeSheetEntry.getReportType()%>
                </td>
                <td><%=timeSheetEntry.getDescription()%>
                </td>
                <td><%=timeSheetEntry.getHoursDue()%>
                </td>
                <td><%=timeSheetEntry.getStartTime()%>
                </td>
                <td><%=timeSheetEntry.getEndTime()%>
                </td>
                <td><%=timeSheetEntry.getEntryDate()%>
                </td>
                <td>
                    <%if (!timeSheetEntry.getSigned()) {%>
                    <a href="${pageContext.request.contextPath}/sign-timesheet?timesheetEntryId=<%=timeSheetEntry.getTimesheetEntryId()%>">
                        <button class="btn" style="background-color: #4CAF50; color: white;">
                            Sign
                        </button>
                    </a>
                    <%} else {%>
                    <a href="#">
                        <button class="btn" style="background-color: #4CAF50; color: white;" disabled>
                            Signed!!
                        </button>
                    </a>
                    <%}%>

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
