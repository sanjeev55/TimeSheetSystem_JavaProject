<%@ page import="com.javaee.javaee2022teamnine.model.Contract" %>
<%@ page import="com.javaee.javaee2022teamnine.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 9/24/2022
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
  <title>View Contract</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
    <div>
      <a href="<%=request.getContextPath()%>/Dashboard.jsp" class="navbar-brand"> Time Sheet System Application </a>
    </div>
    <% User user = (User) session.getAttribute("user"); %>
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
        <br/>
        <caption>
          <h2>
            Contract Details
          </h2>
        </caption>
        <label>
          <input type="hidden" name="contract_id" value=<%=contract.getId()%>>
        </label>

        <div>
            <fieldset class="form-group">
            <label>Name</label>
            <span style="float:right;"><%=contract.getName()%></span>
          </fieldset>
              <fieldset class="form-group">
            <label>Start Date</label>
            <span style="float:right;"><%=contract.getStartDate()%></span>
          </fieldset>
            <fieldset class="form-group">
            <label>End Date</label>
            <span style="float:right;"><%=contract.getEndDate()%></span>
          </fieldset>
            <fieldset class="form-group">
            <label>Hours Per Week</label>
            <span style="float:right;"><%=contract.getHoursPerWeek()%></span>
          </fieldset>
            <fieldset class="form-group">
            <label>Hours Due</label>
            <span style="float:right;"><%=contract.getHoursDue()%></span>
          </fieldset>
            <fieldset class="form-group">
            <label>Working Days Per Week</label>
            <span style="float:right;"><%=contract.getWorkingDaysPerWeek()%></span>
          </fieldset>
              <fieldset class="form-group">
            <label>Vacation Days Per Year</label>
            <span style="float:right;"><%=contract.getVacationDaysPerYear()%></span>
              </fieldset>
        </div>
    </div>
  </div>
</div>
<br/>
</body>
</html>
