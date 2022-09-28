<%@ page import="com.javaee.javaee2022teamnine.model.Contract" %>
<%@ page import="java.util.List" %>
<%@ page import="com.javaee.javaee2022teamnine.model.User" %><%--
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
            <a href="<%=request.getContextPath()%>/Dashboard.jsp" class="navbar-brand"> Time Sheet System Application </a>
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
            <li><a href="<%=request.getContextPath()%>/sign-timesheet" class="nav-link">Sign Timesheet</a></li>
            <li><a href="<%=request.getContextPath()%>/view-timesheet" class="nav-link">View Timesheet</a>
            </li>

            <% } else { %>
            <li><a href="<%=request.getContextPath()%>/view-contract" class="nav-link">View Contract</a></li>
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
        <h3 class="text-center">Terminate Contracts</h3>
        <hr>
        <br>

        <table class="table table-bordered">
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
            <%
                for (Contract contract : contracts) {
            %>
            <tbody>
            <tr>
                <td>
                    <%=contract.getId()%>
                </td>
                <td><%=contract.getName()%>
                </td>
                <% if (contract.getStatus().getId() == 2) { %>
                <td>
                    <center>
                        <span class="badge badge-success">STARTED</span>
                    </center>
                </td>
                <%}%>
                <td><%=contract.getStartDate()%>
                </td>
                <td><%=contract.getEndDate()%>
                </td>
                <td>
                    <!-- Button trigger modal -->
                    <%--<a href="${pageContext.request.contextPath}/terminate-contract?contract_id=<%=contract.getId()%>">
                        <button class="btn" style="background-color: #f44336; color: white;">
                            Terminate Contract
                        </button>
                    </a>--%>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">
                        Terminate Contract
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Are you sure?</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    The user may still have Time Sheets that
                                    are in progress.
                                    <br/>
                                    Confirm termination of contract?
                                </div>
                                <div class="modal-footer">
                                    <a href="${pageContext.request.contextPath}/terminate-contract?contract_id=<%=contract.getId()%>">
                                        <button class="btn btn-success" onClick="window.location.reload();"
                                                type="submit">
                                            Confirm
                                        </button>
                                    </a>
                                    <%--<a href="${pageContext.request.contextPath}/terminate-contract?contract_id=<%=contract.getId()%>">
                                        <button class="btn btn-success" data-dismiss="modal">
                                            Confirm
                                        </button>
                                    </a>--%>
                                    <%--                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Terminate</button>--%>
                                    <button type="button" class="btn btn-secondary">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </td>
            </tr>
            </tbody>
            <%
                }
            } else {
            %>
            <div class="alert alert-success" role="alert">
                No contracts to <span class="badge badge-danger">TERMINATE</span> !
                Wait for contracts to be <span class="badge badge-info">STARTED</span> !
            </div>
            <%
                }
            %>
        </table>

    </div>
</div>

</body>
</html>
