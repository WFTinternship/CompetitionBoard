<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SignUp/Login Form</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="backgroundLogin">
<div class="form">
    <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>

    <div class="tab-content">
        <div id="signup">
            <h1>Sign Up for Free</h1>

            <%--SignUp Form--%>
            <form action="signin" method="post">
                <div class="field-wrap">
                    <label>Username/Login<span class="req">*</span></label>
                    <input type="text" name="userNameSignIn" required autocomplete="off"/>
                </div>
                <div class="field-wrap">
                    <label>Password<span class="req">*</span></label>
                    <input type="password" name="passwordSignIn" required autocomplete="off"/>
                </div>
                <div class="field-wrap">
                    <label>Confirm Password<span class="req">*</span></label>
                    <input type="password" name="passwordConfirmSignIn" required autocomplete="off"/>
                </div>

                <button type="submit" class="button button-block">Sign Up</button>
            </form>
        </div>

        <div id="login">
            <h1>Welcome Back!</h1>
            <%--Log In Form--%>
            <form action="login" method="post">
                <div class="field-wrap">
                    <label>Username/Login<span class="req">*</span></label>
                    <input type="text" name="usernameLogin" required autocomplete="off"/>
                </div>
                <%--Password For Log In --%>
                <div class="field-wrap">
                    <label> Password<span class="req">*</span></label>
                    <input type="password" name="passwordLogin" required autocomplete="off"/>
                </div>
                <%--Password Forgot--%>
                <p class="forgot"><a href="#">Forgot Password?</a></p>

                <button class="button button-block">Log In</button>
            </form>
        </div>
    </div>
</div>

<!-- /form -->
<script src="js/jquery-3.1.0.js"></script>
<script src="js/login.js"></script>
</body>
</html>
