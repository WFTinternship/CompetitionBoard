<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SignUp/Login Form</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="<c:url value="/resources/css/normalize.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/custom.css"/>">
</head>

<body class="backgroundLogin">
<div class="form">
    <%--Gets error message from LogInServlet--%>
    <%
        String userNameErrStr = (String) session.getAttribute("userNameErr");
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

            <p class="forgot"><a href="signup-page">No account? Create one!</a></p>
            <%--<p class="forgot"><a href="#">Forgot Password?</a></p>--%>

            <button class="button button-block" name="logInButton">Log In</button>
        </form>
    </div>
</div>

<script src="<c:url value="/resources/js/jquery-3.1.0.js" />"></script>
<script src="<c:url value="/resources/js/login.js" />"></script>
</body>
</html>