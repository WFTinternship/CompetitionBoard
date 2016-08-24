<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SignUp/Login Form</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="resources/css/normalize.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/custom.css">
</head>

<body class="backgroundLogin">
<div class="form">

    <%--Gets error message from LogInServlet--%>
    <%
        String userNameErrStr = (String) request.getAttribute("userNameErr");
        if (userNameErrStr == null) {
            userNameErrStr = "";
        }
    %>

    <%--LogIn Form--%>
    <div id="login">
        <h1>Welcome Back!</h1>
        <div class="err-msg"><%=userNameErrStr%></div>

        <form action="login-form" name="logInForm" method="post">
            <div class="field-wrap">
                <label>Username/Login<span class="req"></span></label>
                <input type="text" name="usernameLogin" required autocomplete="off"/>
            </div>
            <%--Password For Log In --%>
            <div class="field-wrap">
                <label> Password<span class="req"></span></label>
                <input type="password" name="passwordLogin" required autocomplete="off"/>
            </div>
            <%--Password Forgot--%>

            <p class="forgot"><a href="sign-in.jsp">No account? Create one!</a></p>
            <%--<p class="forgot"><a href="#">Forgot Password?</a></p>--%>

            <button class="button button-block" name="logInButton">Log In</button>
        </form>
    </div>
</div>

<script src="resources/js/jquery-3.1.0.js"></script>
<script src="resources/js/login.js"></script>
</body>
</html>