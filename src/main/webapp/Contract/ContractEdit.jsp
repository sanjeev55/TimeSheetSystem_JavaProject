<%@ page import="com.javaee.javaee2022teamnine.model.Contract" %>
<%@ page import="com.javaee.javaee2022teamnine.model.User" %><%--
  Created by IntelliJ IDEA.
  contract: BMS-PC
  Date: 9/18/2022
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Contract</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="#" class="navbar-brand"> Time Sheet System Application </a>
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
            <li><a href="#" class="nav-link">View Contract</a></li>
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
<%--<jsp:useBean id="contract" scope="request" class="com.javaee.javaee2022teamnine.model.Contract"/>--%>

<% Contract contract = (Contract) request.getAttribute("contract"); %>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/update" method="post">
                <br/>
                <caption>
                    <h2>
                        Edit Contract
                    </h2>
                </caption>
                <label>
                    <input type="hidden" name="contract_id" value=<%=contract.getId()%>>
                </label>

                <div>
                    <%-- name --%>
                    <fieldset class="form-group">
                        <label>Contract Holders Name</label>
                        <span class="badge badge-primary" style="float:right;">PREPARED</span>
                        <input type="text" value=<%=contract.getName()%>
                                class="form-control" name="name" required="required" readonly/>
                    </fieldset>
                </div>

                <%-- start date --%>
                <fieldset class="form-group">
                    <label>Start Date</label>
                    <input type="date" value=<%=contract.getStartDate()%>
                            class="form-control" name="start_date"/>
                </fieldset>

                <%-- end date--%>
                <fieldset class="form-group">
                    <label>End Date</label>
                    <input type="date" value=<%=contract.getEndDate()%>
                            class="form-control" name="end_date"/>
                </fieldset>

                <%-- hoursperweek --%>
                <fieldset class="form-group">
                    <label>Hours Per Week</label>
                    <input type="number" value=<%=contract.getHoursPerWeek()%>
                            class="form-control" name="hours_per_week"/>
                </fieldset>

                <%-- vacation hours--%>
                <fieldset class="form-group">
                    <label>Vacation Hours</label>
                    <input type="number" value=<%=contract.getVacationHours()%>
                            class="form-control" name="vacation_hours"/>
                </fieldset>

                <%-- working days per week --%>
                <fieldset class="form-group">
                    <label>Working Days Per Week</label>
                    <input type="number" value=<%=contract.getWorkingDaysPerWeek()%>
                            class="form-control" name="working_days_per_week"/>
                </fieldset>

                <%-- vacation days per year --%>
                <fieldset class="form-group">
                    <label>Vacation Days Per Year</label>
                    <input type="number" value=<%=contract.getVacationDaysPerYear()%>
                            class="form-control" name="vacation_days_per_year"/>
                </fieldset>
                <input type="submit" value="Update" class="btn btn-success"/>
                <%--                <button type="submit" class="btn btn-success">Update</button>--%>
                <a href="${pageContext.request.contextPath}/contract-list">
                    <button class="btn btn-danger">Cancel</button>
                </a>
            </form>
        </div>
    </div>
</div>
<br/>
</body>
</html>
