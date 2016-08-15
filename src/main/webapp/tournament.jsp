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

    <!-- Custom CSS -->
    <link href="css/blog-home.css" rel="stylesheet">
    <link href="css/creative.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <script src="js/jquery.js"></script>
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

<body class="backgroundTournament">
<!-- Navigation -->
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <%--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"--%>
                    <%--data-target="#bs-example-navbar-collapse-1">--%>
                <%--<span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>--%>
            <%--</button>--%>
            <a class="navbar-brand page-scroll" href="<%=Params.PAGE_INDEX%>">Home</a>
            <a class="navbar-brand page-scroll"><%= loginUserStr%> <%= userNameSignInStr%>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a class="page-scroll" href="tournament.jsp">Tournaments</a></li>
                <li><a class="page-scroll" href="match.jsp">Matches</a></li>
                <li><a class="page-scroll" href="#portfolio">Gallery</a></li>
                <li><a class="page-scroll" href="#contact">Contact Us</a></li>

                <% if ((loginUserStr.equals("")) && (userNameSignInStr.equals(""))) { %>
                <li><a href="login.jsp?action=signUp" name="signUpMenuBtn">Sign Up</a></li>
                <li><a href="login.jsp?action=logIn" name="logInMenuBtn">Log In</a></li>
                <%} else if (loginUserStr != null) { %>
                <li><a href="logout">Log Out </a></li>
                <%} else if ((userNameSignInStr != null)) { %>
                <li><a href="logout">Log Out </a></li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>


<div class="row">
    <!-- Blog Entries Column -->
    <div class="col-md-8">
        <div class="container">
            <div class="container-fluid">
                <div class="row content">
                    <div class="col-sm-3 sidenav">

                        <ul class="nav nav-pills nav-stacked">
                            <li>
                                <button class="btn btn-danger"><B>CREATE A TOURNAMENT</B></button>
                            </li>
                        </ul>
                        <br>
                    </div>

                    <div class="col-sm-9">
                        <h2>Your Tournaments</h2>
                        <hr>

                        <div class="container">
                            <form action="add" class="form-horizontal" role="form" method="get">
                                <h2>New Tournament</h2>

                                <%--Tournament Name--%>
                                <div class="form-group">
                                    <label for="name" class="col-sm-3 control-label">Name</label>
                                    <div class="col-sm-9">
                                        <input type="text" id="name" placeholder="Tournament Name" class="form-control" autofocus>
                                    </div>
                                </div>

                                <%--StartDate--%>
                                <div class="form-group">
                                    <label for="startDate" class="col-sm-3 control-label">Start Time</label>
                                    <div class="col-sm-9">
                                        <input type="text" id="startDate" placeholder="Start Time" class="form-control">
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
                                        <input type="text" id="location" placeholder="Location" class="form-control">
                                    </div>
                                </div>

                                <%--TournamentFormat--%>
                                <div class="form-group">
                                    <label for="format" class="col-sm-3 control-label">Format</label>
                                    <div class="col-sm-9">
                                        <select id="format" class="form-control"> <option>Round Robin</option>
                                            <option>Olympic</option>
                                        </select>
                                    </div>
                                </div>
                                <!-- /.form-group -->
                                <div class="form-group">
                                    <div class="col-sm-9 col-sm-offset-3">
                                        <input type="submit" class="btn btn-primary btn-block">Save and continue</input>
                                    </div>
                                </div>
                            </form>
                            <!-- /form -->
                        </div>

                        <div id="custom-search-input">
                            <div class="input-group col-md-12">
                                <input type="text" class="  search-query form-control" placeholder="Search your tournament"/>
                <span class="input-group-btn">
                    <button class="btn btn-danger" type="button">
                        <span class=" glyphicon glyphicon-search"></span>
                    </button>
                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <%--<footer id="footer">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-lg-12">--%>
                        <%--<p>Copyright &copy; Artur Babayan 2016</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</footer>--%>

        </div>
        </div>
    </div>

</body>
</html>