<%@ page import="com.workfront.intern.cb.web.util.Params" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Your Tournaments</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/custom/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-home.css" rel="stylesheet">
    <link href="css/creative.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <script src="js/custom.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/login.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

    <%
    String userNameSignInStr = (String) session.getAttribute("userNameSignIn");
    if (userNameSignInStr == null) {
        userNameSignInStr = "";
    }

    String loginUserStr = (String) session.getAttribute("usernameLogin");
    if (loginUserStr == null) {
        loginUserStr = "";
    }
%>

<%--Gets error message from SignInServlet--%>
<%
    String existsTournament = (String) request.getAttribute("existsTournament");
    if (existsTournament == null) {
        existsTournament = "";
    }
%>

<body class="backgroundTournament">
<!-- Navigation -->
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">

            <a class="navbar-brand page-scroll" href="<%=Params.PAGE_INDEX%>">Home</a>
            <a class="navbar-brand page-scroll"><%= loginUserStr%> <%= userNameSignInStr%>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a class="page-scroll" href="tournament.jsp">Tournaments</a></li>
                <li><a class="page-scroll" href="match.jsp">Matches</a></li>
                <li><a class="page-scroll" href="media.jsp">Gallery</a></li>
                <li><a class="page-scroll" href="contact.jsp">Contact Us</a></li>

                <% if ((loginUserStr.equals("")) && (userNameSignInStr.equals(""))) { %>
                <li><a href="log-in.jsp?action=signUp" name="signUpMenuBtn">Sign Up</a></li>
                <li><a href="log-in.jsp?action=logIn" name="logInMenuBtn">Log In</a></li>
                <%} else if (loginUserStr != null) { %>
                <li><a href="logout">Log Out </a></li>
                <%} else if ((userNameSignInStr != null)) { %>
                <li><a href="logout">Log Out </a></li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <form action="add" class="form-horizontal" role="form" method="get">
        <h2>New Tournament</h2>
        <hr>

        <div class="err-msg"><%=existsTournament%></div>

        <%--Tournament Name--%>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">Name</label>
            <div class="col-sm-9">
                <input type="text" id="name" name="name" placeholder="Tournament Name"
                       class="form-control" autofocus required >
            </div>
        </div>

        <%--StartDate--%>
        <div class="form-group">
            <label for="startDate" class="col-sm-3 control-label">Start Time</label>
            <div class="col-sm-9">
                <input type="text" id="startDate" placeholder="Start Time" class="form-control "pattern="^[ 0-9]+$">
                <span class="help-block">eg.: 2020-07-10 10:00:00 </span>
            </div>
        </div>

        <%--EndDate--%>
        <div class="form-group">
            <label for="endDate" class="col-sm-3 control-label">End time</label>
            <div class="col-sm-9">
                <input type="text" id="endDate" placeholder="End time" class="form-control">
                <span class="help-block">eg.: 2020-07-10 10:00:00 </span>
            </div>
        </div>

        <%--Location--%>
        <div class="form-group">
            <label for="location" class="col-sm-3 control-label">Location</label>
            <div class="col-sm-9">
                <input type="text" id="location" name="location" placeholder="Location" class="form-control" required>
            </div>
        </div>

        <%--Description--%>
        <div class="form-group">
            <label  class="col-sm-3 control-label">Description</label>
            <div class="col-sm-9">
                <textarea rows="5" cols="20" name="tournament_description" id="field2" class="form-control" ></textarea>
            </div>
        </div>

        <%--TournamentFormat--%>
        <div class="form-group">
            <label for="formatId" class="col-sm-3 control-label">Format</label>
            <div class="col-sm-9">
                <select id="formatId" name="format" class="form-control" onchange="selectElement()">
                    <%--<option value="0" selected="selected">Select format</option>--%>
                    <option value="1">Round_Robin</option>
                    <option value="2">Olympic</option>
                </select>
            </div>
        </div>
        <!-- /.form-group -->
        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <input type="submit" class="btn btn-danger" value="Save and continue">
            </div>
        </div>
    </form>
    <!-- /form -->
</div>
<!--
</body>
</html>