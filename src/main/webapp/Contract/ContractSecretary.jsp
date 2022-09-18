<%--
  Created by IntelliJ IDEA.
  User: BMS-PC
  Date: 9/14/2022
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Secretary Dashboard</title>
</head>
<body>
<h2>This page is after logging in as <b>Secretary</b></h2>
<a href="<%=request.getContextPath()%>/contract-secretary"></a>
<input type="button" name="updateContract" value="Update a Contract"/>
<input type="button" name="Print Contracts"/>
</body>
</html>
