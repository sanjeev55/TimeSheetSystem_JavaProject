<%-- 
    Document   : resetpw
    Created on : 12 Sep 2022, 14:17:12
    Author     : ayush
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reset Password</title>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>

    <style>
        .top-text {
            font-family: "sans-serif";
            margin-top: 15px;
            text-align: center;
            font-size: 20px;
            letter-spacing: 1px;
            margin-bottom: 15px;
            font-weight: bold;
            color: #FFFFFF;
        }

        .reset-page {
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
    </style>
    <script>
        function validate() {
            var email = document.form.email.value;
            var password = document.form.password.value;
            var emailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            if (email === "") {
                alert("email field empty");
                return false;
            }
            if (password === "") {
                alert("password field empty");
                return false;
            }
            if (!email.match(emailformat)) {
                alert("invalid email format");
                return false;
            }
            if (password.length < 6) {
                alert("minimul length of password is 6");
            }
        }
    </script>
</head>
<body>
<header>

    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="#" class="navbar-brand"> Time Sheet System Application </a>
        </div>
    </nav>
</header>
<div class="reset-page">
    <div class="form">
        <div class="top-text">
            Reset Your Password
        </div>
        <form action="${pageContext.request.contextPath}/reset-password" name="form" method="post"
              onsubmit="return validate()">
            <input type="text" name="email" placeholder="Your Email"/>
            <input type="password" name="password" placeholder="new password"/>
            <button type="submit" name="Reset">Reset</button>
        </form>
    </div>
</div>
</body>
</html>
