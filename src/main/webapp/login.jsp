<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign-Up/Login Form</title>
    <%--<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>--%>

    <%--<link rel="stylesheet" href="css/normalize.css">--%>
    <%--<link rel="stylesheet" href="css/style.css">--%>
</head>

<body>
<div class="form">
    <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>

    <%--SignUp Menu--%>
    <div class="tab-content">
        <div id="signup">
            <h1>Sign Up for Free</h1>
            <form action="login" method="post">
                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            First Name<span class="req">*</span>
                        </label>
                        <input type="text" required autocomplete="off"/>
                    </div>

                    <%--LastName--%>
                    <div class="field-wrap">
                        <label>
                            Last Name<span class="req">*</span>
                        </label>
                        <input type="text" required autocomplete="off"/>
                    </div>
                </div>

                <%--EmailAddress--%>
                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email" required autocomplete="off"/>
                </div>

                <%--SetPassword--%>
                <div class="field-wrap">
                    <label>
                        Set A Password<span class="req">*</span>
                    </label>
                    <input type="password" required autocomplete="off"/>
                </div>

                <button type="submit" class="button button-block">Get Started</button>
            </form>
        </div>

        <%--LogIn Menu--%>
        <div id="login">
            <h1>Welcome Back!</h1>

            <%--EmailInsert--%>
            <form action="/" method="post">
                <div class="field-wrap">
                    <label>Email Address<span class="req">*</span></label>
                    <input type="email" name="email"  required autocomplete="off"/>
                </div>

                <%--PasswordInsert--%>
                <div class="field-wrap">
                    <label> Password<span class="req">*</span></label>
                    <input type="password" name="pass" required autocomplete="off"/>
                </div>
                <%--Password Forgot--%>
                <p class="forgot"><a href="#">Forgot Password?</a></p>

                <button class="button button-block">Log In</button>
            </form>
        </div>
    </div>
</div>

<!-- /form -->
<%--<script src="js/jquery-3.1.0.js"></script>--%>
<%--<script src="js/login.js"></script>--%>
</body>
</html>