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
    background: darkgreen;
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
    color: #4CAF50;
    font-size: 14px;
    cursor: pointer;
  }
  .form button:hover,.form button:active,.form button:focus {
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
      if(email === ""){
          alert("email field empty");
          return false;
      }
      if(password === ""){
          alert("password field empty");
          return false;
      }
      if(!email.match(format)){
          alert("invalid email format");
          return false;
      }
  }
</script>
<title>Login Page</title>
<body>
<div class="login-page">
  <div class="form">
    <div class="top-text">
      Time Sheet System (TSS)
    </div>
      <form name="form" method="post" onsubmit="return validate()">
      <input type="text" name="email" placeholder="Email"/>
      <input type="password" name="password" placeholder="password"/>
      <button type="submit" name="login">Login</button>
      <p class="message">New Employees <a href="#"><u>Create an account</u></a></p>
      <p class="message">Forgot Password? <a href="#"><u>Reset Here</u></a></p>
    </form>
  </div>
</div>
</body>