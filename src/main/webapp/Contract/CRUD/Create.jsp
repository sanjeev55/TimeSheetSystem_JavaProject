<%@ page import="com.javaee.javaee2022teamnine.model.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: BMS-PC
  Date: 9/14/2022
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Contract</title>
</head>
<body>
<center>
    <h1>Create Contract</h1>
    <h2>Displaying User List</h2>
</center>
<table border="1" width="500" align="center">
    <tr bgcolor="00FF7F">
        <th><b>User Name</b></th>
        <th><b>User Email</b></th>
        <th><b>User Role</b></th>
        <th bgcolor="#cd5c5c"><b>Action</b></th>
    </tr>
    <%
        ArrayList<User> user = (ArrayList<User>) request.getAttribute("data");
        for (User u : user) {
    %>
    <tr>
        <td><%=u.getFullName()%>
        </td>
        <td><%=u.getEmail()%>
        </td>
        <td><%=u.getRole()%>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/contract" method="post">
                <input value="Create" name="createContractButton" type="submit"/>
            </form>
        </td>
    </tr>
    <%}%>
</table>


</body>
</html>
