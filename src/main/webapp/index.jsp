<style>
    .top-text {
        margin-top: 15px;
        text-align: center;
        font-size: 20px;
        letter-spacing: 1px;
        margin-bottom: 15px;
        font-weight: bold;
        color: #FFFFFF;

    }

    .login-page {
        width: 360px;
        padding: 8% 0 0;
        margin: auto;
    }

    .form {
        position: relative;
        z-index: 1;
        background: tomato;
        max-width: 360px;
        margin: 0 auto 100px;
        padding: 45px;
        text-align: center;
    }

    .form input {
        font-family: "Comic Sans MS";
        outline: 0;
        background: #f2f2f2;
        width: 100%;
        border: 0;
        margin: 0 0 15px;
        padding: 15px;
        box-sizing: border-box;
        font-size: 14px;
    }

    .form button {
        font-family: "Comic Sans MS";
        text-transform: uppercase;
        outline: 0;
        background: #FFFFFF;
        width: 100%;
        border: 0;
        padding: 15px;
        color: tomato;
        font-size: 14px;
        cursor: pointer;
    }

    .form button:hover, .form button:active, .form button:focus {
        background: #4d4d4d;
    }

    .form .message {
        margin: 15px 0 0;
        color: #FFFFFF;
        font-size: 12px;
    }

    .form .message a {
        color: #FFFFFF;
        font-style: italic;
        text-decoration: none;
    }
</style>
<script>
    function validate() {
        var email = document.form.email.value;
        var password = document.form.password.value;
        var format = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (email === "") {
            alert("email field empty");
            return false;
        }
        if (password === "") {
            alert("password field empty");
            return false;
        }
        if (!email.match(format)) {
            alert("invalid email format");
            return false;
        }
    }
</script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Login Page</title>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="#" class="navbar-brand"> Time Sheet System Application </a>
        </div>
    </nav>
</header>
<div class="login-page">
    <div class="form">
        <div class="top-text">
            Time Sheet System Login
        </div>
        <form name="form" method="post" action="${pageContext.request.contextPath}/login" onsubmit="return validate()">
            <input type="text" name="email" placeholder="Email"/>
            <input type="password" name="password" placeholder="Password"/>
            <button type="submit" name="login">Login</button>
            <p class="message">New Employees <a href="${pageContext.request.contextPath}/register"><u>Create an
                account</u></a></p>
            <p class="message">Forgot Password? <a href="${pageContext.request.contextPath}/reset-password"><u>Reset Here</u></a></p>
        </form>
    </div>
</div>
</body>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
...
<body>
<p><%=(request.getAttribute("loginResult") == null) ? ""
        : "id or password is wrong"%>
</p>

...
</body>