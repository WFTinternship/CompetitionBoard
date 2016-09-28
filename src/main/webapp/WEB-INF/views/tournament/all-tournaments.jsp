<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.workfront.intern.cb.common.Manager" %>
<%@ page import="com.workfront.intern.cb.common.Tournament" %>
<%@ page import="com.workfront.intern.cb.common.TournamentFormat" %>
<%@ page import="com.workfront.intern.cb.web.util.Params" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>All available tournaments</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/blog-home.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/creative.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/custom.css"/>">


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
            <a class="navbar-brand page-scroll" href="home">HOME</a>
            <a><img class="avatar" src="<%=avatar%>"> </a>
            <a class="navbar-brand page-scroll"><%=welcomeStr + "" + userName%>
            </a>
        </div>
        <input type="hidden" id="login-status" value="<%=userName%>"/>


        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <%--Add Tournament--%>
                <li>
                    <a class="visible-when-logged-in page-scroll" href="addTournament-page" id="<%=classStr%>"
                       onload="showMenuItem()"><%=addTournamentMenuItem%>
                    </a>
                </li>

                <%--All Tournament--%>
                <li>
                    <a class=" page-scroll" href="<%=hrefToSpecificTournamentPage%>"><%=allTournaments%>
                    </a>
                </li>

                <%--Group--%>
                <li>
                    <a class=" page-scroll" href="<%=hrefToSpecificGroupPage%>"><%=allGroups%>
                    </a>
                </li>

                <%--Gallery--%>
                <li>
                    <a class="page-scroll" href="#portfolio">Gallery</a>
                </li>

                <%--Contact Us--%>
                <li>
                    <a class="page-scroll" href="#contact">Contact Us</a>
                </li>

                <%--Sign Up--%>
                <li>
                    <a href="signup-page" class="hidden-when-logged-in">Sign Up</a>
                </li>

                <%--Log In--%>
                <li>
                    <a href="login-page" class="hidden-when-logged-in">Log In </a>
                </li>

                <%--Log Out--%>
                <li>
                    <a href="logout-page" class="visible-when-logged-in hidden-element">Log Out</a>
                </li>
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
                            <%--Left Side--%>
                        </ul>
                        <br>
                    </div>

                    <div class="col-sm-9">
                        <h2>All available tournaments</h2>
                        <hr>
                        <br>

                        <div class="container">
                            <div id="table" class="table-editable">
                                <%
                                    List<Tournament> tournamentList = (List<Tournament>) session.getAttribute("allTournamentList");
                                    int sizeList = tournamentList.size();
                                %>
                                <table class="table">
                                    <tr class="thCustom">
                                        <th>No</th>
                                        <th>Name</th>
                                        <th>StartDate</th>
                                        <th>EndDate</th>
                                        <th>Location</th>
                                        <th>Description</th>
                                        <th>Format</th>
                                        <th>Owner</th>
                                    </tr>
                                    <%
                                        for (int i = 0; i < sizeList; i++) {
                                    %>
                                    <tr>
                                        <%--No--%>
                                        <td contenteditable="false">
                                            <%=i + 1 %>
                                        </td>

                                        <%--TournamentName--%>
                                        <td contenteditable="false" data-name="tournamentName" data-updatable="false">
                                            <%=tournamentList.get(i).getTournamentName()%>
                                        </td>

                                        <%--StartDate--%>
                                        <td contenteditable="false" data-name="startDate" data-updatable="false">
                                            <%=tournamentList.get(i).getStartDate()%>
                                        </td>

                                        <%--EndDate--%>
                                        <td contenteditable="false" data-name="endDate" data-updatable="false">
                                            <%=tournamentList.get(i).getEndDate()%>
                                        </td>

                                        <%--Location--%>
                                        <td contenteditable="false" data-name="location" data-updatable="false">
                                            <%=tournamentList.get(i).getLocation()%>
                                        </td>

                                        <%--TournamentDescription--%>
                                        <td contenteditable="false" data-name="tournamentDescription"
                                            data-updatable="false">
                                            <%=tournamentList.get(i).getTournamentDescription()%>
                                        </td>

                                        <%--TournamentFormatId--%>
                                        <%
                                            int tournamentFormatId = tournamentList.get(i).getTournamentFormatId();
                                            String formatStr = TournamentFormat.fromId(tournamentFormatId).getFormatName();
                                        %>
                                        <td contenteditable="false" data-name="tournamentFormatId"
                                            data-updatable="false">
                                            <%=formatStr%>
                                        </td>

                                        <%--Tournament creator--%>

                                        <td contenteditable="false" data-name="tournamentNameId" data-updatable="false">
                                            <%=tournamentList.get(i).getManagerId()%>
                                        </td>
                                    </tr>
                                    <%}%>
                                </table>

                                <!-- Footer -->
                                <footer>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <p>Copyright &copy; Artur Babayan 2016</p>
                                        </div>
                                    </div>
                                </footer>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>

        <%--Custom JS--%>
        <script src="<c:url value="/resources/js/custom.js" />"></script>
        <script src="<c:url value="/resources/js/tableView.js" />"></script>

        <script src='http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js'></script>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.6.0/underscore.js'></script>
</body>
</html>