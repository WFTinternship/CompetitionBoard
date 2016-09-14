<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.workfront.intern.cb.common.Manager" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Your Matches</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/blog-home.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/creative.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">

    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<%--Gets specific atributes from http session--%>
<%@ include file="../layout/layout.jsp" %>

<body class="backgroundTournament">
<!-- Navigation -->
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand page-scroll" href="/">Home</a>
            <a class="navbar-brand page-scroll"><%=welcomeStr + "" + userName%>

        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a class="visible-when-logged-in page-scroll" href="addTournament-page" id="<%=classStr%>" onload="showMenuItem()"><%=addTournamentMenuItem%></a></li>
                <li><a class=" page-scroll" href="<%=hrefToSpecificTournamentPage%>">Tournaments</a></li>
                <li type="hide"><a class="page-scroll" href="match.jsp">Matches</a></li>
                <li><a class="page-scroll" href="#portfolio">Gallery</a></li>
                <li><a class="page-scroll" href="contact-page">Contact Us</a></li>
                <li><a href="signup-page" class="hidden-when-logged-in">Sign Up</a></li>
                <li><a href="login-page" class="hidden-when-logged-in">Log In </a></li>
                <li><a href="logout-page" class="visible-when-logged-in hidden-element">Log Out</a></li>
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
                                <button class="btn btn-danger"><B>CREATE A MATCH</B></button>
                            </li>
                            <BR>
                            <li>
                                <button class="btn btn-danger"><B>EDIT A MATCH</B></button>
                            </li>
                            <BR>
                            <li>
                                <button class="btn btn-danger"><B>DELETE A MATCH</B></button>
                            </li>

                        </ul>
                        <br>
                    </div>

                    <div class="col-sm-9">
                        <h2>Your Matches</h2>
                        <hr>

                        <div id="custom-search-input">
                            <div class="input-group col-md-12">
                                <input type="text" class="  search-query form-control"
                                       placeholder="Search your match"/>
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