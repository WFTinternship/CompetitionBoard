<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.workfront.intern.cb.web.util.Params" %>
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

    <script src="<c:url value="/resources/js/custom.js" />"></script>
</head>

<body class="backgroundLogin">
<div class="form">
    <%--Gets error message from SignInServlet--%>
    <%
        String errMessage = (String) session.getAttribute("duplicateUserErrMsg");
        if (errMessage == null) {
            errMessage = "";
        }
    %>
    <div id="signup">
        <h1>Create account</h1>
        <div class="err-msg"><%=errMessage%>

        </div>
        <%--SignUp Form--%>
        <form action="signup-form" name="signUpForm" enctype="multipart/form-data" method="post">
            <%--Login Label--%>
            <div class="field-wrap">
                <label>Login<span class="req"></span></label>
                <input type="text" name="userNameSignIn" required autocomplete="off"/>
            </div>
            <%--Avatar Label--%>
            <div id="avatar" class="field-wrap">
                <%--<div class="field f_100">--%>
                <label></label>
                <input type="file" size="48" name="user_avatar" multiple required accept="image/*,image/jpg">
                <%--</div>--%>
            </div>

            <%--Write error message, when when the passwords do not match --%>
            <p class="err-msg">
                <% String passwordNotMatchErr = (String) request.getAttribute("passwordNotMatchErr");
                    if (passwordNotMatchErr != null) {
                        out.println(passwordNotMatchErr);
                    }
                %>
            </p>
            <%--Password Label--%>
            <div class="field-wrap">
                <label>Password<span class="req"></span></label>
                <input type="password" name="passwordSignIn" required autocomplete="off"/>
            </div>
            <%--Confirm Password Label--%>
            <div class="field-wrap">
                <label>Confirm Password<span class="req"></span></label>
                <input type="password" name="passwordConfirmSignIn" required autocomplete="off"/>
            </div>
            <p class="forgot"><a href="/login-page">Already have an account? LogIn</a></p>

            <button type="submit" class="button button-block">Sign Up</button>
        </form>
    </div>
</div>
</div>

<script src="<c:url value="/resources/js/jquery-3.1.0.js" />"></script>
<script src="<c:url value="/resources/js/login.js" />"></script>
</body>
</html>
