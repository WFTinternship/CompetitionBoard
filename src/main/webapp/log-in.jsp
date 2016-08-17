<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SignUp/Login Form</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/custom.css">

    <script src="js/custom.js"></script>

</head>

<body class="backgroundLogin">
<div class="form">

    <%--<ul class="tab-content tab active">--%>
        <%--<li class="tab active" id="hrefSignIn">--%>
            <%--<a href="#login"></a></li>--%>
    <%--</ul>--%>


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
        <h4 class="err-msg"><%=userNameErrStr%></h4>

        <form action="login" name="logInForm" method="post">
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
            <p class="forgot"><a href="#">Forgot Password?</a></p>

            <button class="button button-block" name="logInButton">Log In</button>
        </form>
    </div>
</div>

<script src="js/jquery-3.1.0.js"></script>
<script src="js/login.js"></script>
</body>
</html>
