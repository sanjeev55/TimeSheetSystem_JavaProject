<%-- 
    Document   : resetpw
    Created on : 12 Sep 2022, 14:17:12
    Author     : ayush
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
        <style>
            .top-text {
                font-family: "Comic Sans MS";
                margin-top: 15px;
                text-align: center;
                font-size: 20px;
                letter-spacing: 1px;
                margin-bottom: 15px;
                font-weight: bold;
                color: #FFFFFF;}
            
            .reset-page {
                width: 360px;
                padding: 8% 0 0;
                margin: auto; }
            
            .form {
                position: relative;
                z-index: 1;
                background: darkgreen;
                max-width: 360px;
                margin: 0 auto 100px;
                padding: 45px;
                text-align: center; }
            
            .form input {
                font-family: "Comic Sans MS";
                outline: 0;
                background: #f2f2f2;
                width: 100%;
                border: 0;
                margin: 0 0 15px;
                padding: 15px;
                box-sizing: border-box;
                font-size: 14px; }
            
            .form button {
                font-family: "Comic Sans MS";
                text-transform: uppercase;
                outline: 0;
                background: #FFFFFF;
                width: 100%;
                border: 0;
                padding: 15px;
                color: #4CAF50;
                font-size: 14px;
                cursor: pointer; }
            
            .form button:hover,.form button:active,.form button:focus {
                background: #4d4d4d; }
        </style>
        <script>
            function validate(){
                var email= document.form.email.value;
                var password= document.form.password.value;
                var emailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                if(email === ""){
                    alert("email field empty");
                    return false;
                }
                if(password === ""){
                    alert("password field empty");
                    return false;
                }
                if(!email.match(emailformat)){
                    alert("invalid email format");
                    return false;
                }
                if(password.length<6){
                    alert("minimul length of password is 6");
                }     
            }
        </script>
    </head>
    <body>
        <div class="reset-page">
            <div class="form">
                <div class="top-text">
                    Reset Your Password
                </div>
                <form name="form" method="post" onsubmit="return validate()">
                    <input type="text" name="email" placeholder="Your Email"/>
                    <input type="password" name="password" placeholder="new password"/>
                    <button type="submit" name="Reset">Reset</button>
                </form>
            </div>
        </div>
    </body>
</html>
