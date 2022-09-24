<%@ page import="com.javaee.javaee2022teamnine.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: BMS-PC
  Date: 9/17/2022
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java EE TSS - Users List</title>
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
<%----%>
<br/>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">Create Contract</h3>
        <hr>
        <%--<div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New User</a>
        </div>--%>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <%--<jsp:useBean id="listUser" scope="request" type="java.util.List"/>
            <c:forEach var="user" items="${listUser}">--%>
            <%
                List<User> users = (List<User>) request.getAttribute("listUser");
                for (User user : users) {
            %>
            <tr>
                <td>
                    <%=user.getId()%>
                </td>
                <td><%=user.getFullName()%>
                </td>
                <td><%=user.getEmail()%>
                </td>
                <td><%=user.getRole()%>
                </td>
                <td>

                    <a href="${pageContext.request.contextPath}/create?fullName=<%=user.getFullName()%>">
                        <button class="btn" style="background-color: #4CAF50; color: white;">
                            Create Contract
                        </button>
                    </a>
                    <%--                        <a href="edit?id=<c:out value='<%=user.getId()%>' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;--%>
                    <%--                        <a href="delete?id=<c:out value='<%=user.getId()%>' />">Delete</a>--%>
                </td>
            </tr>
            <%--                </c:forEach>--%>
            <%}%>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
