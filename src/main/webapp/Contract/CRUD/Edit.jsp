<%@ page import="com.javaee.javaee2022teamnine.model.Contract" %><%--
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

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/users-list" class="nav-link">Users List</a></li>
            <li><a href="<%=request.getContextPath()%>/contract/contract-list" class="nav-link">Contracts List</a></li>
        </ul>
    </nav>
</header>
<br/>
<%--<jsp:useBean id="contract" scope="request" class="com.javaee.javaee2022teamnine.model.Contract"/>--%>

<% Contract contract = (Contract) request.getAttribute("contract"); %>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/update" method="post">

                <caption>
                    <h2>
                        Edit Contract
                    </h2>
                </caption>
                <label>
                    <input type="hidden" name="contract_id" value=<%=contract.getId()%>>
                </label>
                <fieldset class="form-group">
                    <label>Contract Holders Name</label>
                    <input type="text" value=<%=contract.getName()%>
                            class="form-control" name="name" required="required" readonly />
                </fieldset>
                <fieldset class="form-group">
                    <label>Vacation Days Per Year</label>
                    <input type="number" value=<%=contract.getVacationDaysPerYear()%>
                            class="form-control" name="vacation_days_per_year" />
                </fieldset>
                <input type="submit" value="Update" class="btn btn-success"/>
                <%--                <button type="submit" class="btn btn-success">Update</button>--%>
            </form>
        </div>
    </div>
</div>
</body>
</html>
