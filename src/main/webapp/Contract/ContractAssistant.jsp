<%--
  Created by IntelliJ IDEA.
  User: BMS-PC
  Date: 9/14/2022
  Time: 6:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contract Assistant</title>
</head>
<body>
<h2>This is contract page for <b>Assistant</b></h2>
<form action="${pageContext.request.contextPath}/contract" method="get">
    <input type="submit" name="createContract" value="Create a Contract"/>
</form>
<input type="button" name="updateContract" value="Update a Contract"/>
<input type="button" name="viewAllContract" value="View All Contracts"/>
<input type="button" name="deleteContract" value="Delete a Contract"/>
</body>
</html>
