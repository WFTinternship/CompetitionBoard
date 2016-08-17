<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SignUp/Login Form</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
    <%--<link rel="stylesheet" href="css/custom.css">--%>

    <script src="js/custom.js"></script>
</head>

<body class="backgroundLogin">
<div class="form">

    <%--<ul class="tab-content tab active">--%>
        <%--<li class="tab active" id="hrefSignIn">--%>
            <%--<a href="#signup"></a></li>--%>
    <%--</ul>--%>
    <%--<div class="tab-content">--%>


        <div id="signup">
            <h1>Create account</h1>

            <%--SignUp Form--%>
            <form action="sign-in" name="signUpForm" method="post">
                <%--Login Label--%>
                <div class="field-wrap">
                    <label>Login<span class="req"></span></label>
                    <input type="text" name="userNameSignIn" required autocomplete="off"/>
                </div>
                <%--Avatar Label--%>
                <div id="field6-container" class="field-wrap">
                    <div class="field f_100">
                        <label></label>
                        <input size="48" name="field6" id="field6" type="file">
                    </div>
                </div>
                <%--Password Label--%>
                <div class="field-wrap">
                    <label>Password<span class="req"></span></label>
                    <input type="password" name="passwordSignIn" required autocomplete="off"/>
                </div>
                <%--Confirm Password Label--%>
                <div class="field-wrap">
                    <label>Confirm Password<span class="req">*</span></label>
                    <input type="password" name="passwordConfirmSignIn" required autocomplete="off"/>
                </div>

                <button type="submit" class="button button-block">Sign Up</button>
            </form>
        </div>
    </div>
</div>

<script src="js/jquery-3.1.0.js"></script>
<script src="js/login.js"></script>
</body>
</html>
