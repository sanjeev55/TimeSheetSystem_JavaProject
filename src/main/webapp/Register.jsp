<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Register</title>

    <script type="javascript">
        function validate() {
            var fullname = document.form.fullname.value;
            var email = document.form.email.value;
            var password = document.form.password.value;
            var conpassword = document.form.conpassword.value;

            if (fullname == null || fullname === "") {
                alert("Full Name can't be blank");
                return false;
            } else if (email == null || email === "") {
                alert("Email can't be blank");
                return false;
            } else if (password.length < 6) {
                alert("Password must be at least 6 characters long.");
                return false;
            } else if (password !== conpassword) {
                alert("Confirm Password should match with the Password");
                return false;
            }
        }
    </script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="#" class="navbar-brand"> Time Sheet System Application </a>
        </div>
    </nav>
</header>
<br/>
<br/>
<center><h2>Register Here</h2></center>
<br/>
<form name="form" action="${pageContext.request.contextPath}/register" method="post" onsubmit="return validate()">
    <table align="center">
        <tr>
            <td>Full Name</td>
            <td><input type="text" name="fullname"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>DOB</td>
            <td><input type="date" name="dob"/></td>
        </tr>
        <tr>
            <td>
                <label for="roles">Choose a role:</label>
            </td>
            <td>
                <select name="roles" id="roles">
                    <option value="Employee">Employee</option>
                    <option value="Assistant">Assistant</option>
                    <option value="Secretary">Secretary</option>
                    <option value="Supervisor">Supervisor</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label for="federal_state">Select Federal State:</label>
            </td>
            <td>
                <select name="federal_state" id="federal_state">
                    <option value="Baden-Württemberg">Baden-Württemberg</option>
                    <option value="Bavaria">Bavaria</option>
                    <option value="Berlin">Berlin</option>
                    <option value="Brandenburg">Brandenburg</option>
                    <option value="Bremen">Bremen</option>
                    <option value="Hamburg">Hamburg</option>
                    <option value="Hesse">Hesse</option>
                    <option value="Lower Saxony">Lower Saxony</option>
                    <option value="Mecklenburg-Vorpommern">Mecklenburg-Vorpommern</option>
                    <option value="North Rhine-Westphalia">North Rhine-Westphalia</option>
                    <option value="Rhineland-Palatinate">Rhineland-Palatinate</option>
                    <option value="Saarland">Saarland</option>
                    <option value="Saxony">Saxony</option>
                    <option value="Saxony-Anhalt">Saxony-Anhalt</option>
                    <option value="Schleswig-Holstein">Schleswig-Holstein</option>
                    <option value="Thuringia">Thuringia</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="conpassword"/></td>
        </tr>
        <tr>
            <td><input type="checkbox" name="tos" id="tos" value="true">
                <label for="tos">
                    <b>I agree to the TOS of the Time Sheet System!</b>
                </label>
            </td>
        </tr>
        <tr>
            <td><%=(request.getAttribute("errMessage") == null) ? ""
                    : request.getAttribute("errMessage")%>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Register"/>
                <input type="reset" value="Reset"/>
            </td>
        </tr>
        <tr>
            <td bgcolor="#FFFFFF" style="line-height:20px;" colspan=3>&nbsp;</td>
        </tr>
        <tr>
            <td>
                <p class="message"><b>Already have an account?</b>
                    <a href="${pageContext.request.contextPath}/login">
                        <u>Login Here</u>
                    </a>
                </p>
            </td>

        </tr>
    </table>
</form>
<center>
    <a href="${pageContext.request.contextPath}/users-list">
        <button class="btn btn-success" type="submit">
            Test Contract CRUD
        </button>
    </a>
</center>
</body>
</html>