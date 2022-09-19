<%@ page import="com.javaee.javaee2022teamnine.model.Contract" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: BMS-PC
  Date: 9/19/2022
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Terminate Contract</title>
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
            <li><a href="<%=request.getContextPath()%>/users-list" class="nav-link">Create Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/contract-list" class="nav-link">Edit/Delete Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/start-contract" class="nav-link">Start Contract</a></li>
            <li><a href="<%=request.getContextPath()%>/terminate-contract" class="nav-link">Terminate Contract</a></li>
        </ul>
    </nav>
</header>
<br/>
<div class="row">
    <div class="container">
        <h3 class="text-center">Terminate Contracts</h3>
        <hr>
        <br>
        <table class="table table-bordered">
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
                List<Contract> contracts = (List<Contract>) request.getAttribute("listContractToStart");
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
                    <a href="${pageContext.request.contextPath}/terminate-contract?contract_id=<%=contract.getId()%>">
                        <button class="btn" style="background-color: #f44336; color: white;">
                            Terminate Contract
                        </button>
                    </a>

                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
