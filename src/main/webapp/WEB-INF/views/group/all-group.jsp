<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.workfront.intern.cb.common.*" %>
<%@ page import="com.workfront.intern.cb.service.TournamentService" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>All available tournaments</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">

    <!-- Main CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/blog-home.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/creative.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">

    <!-- Custom CSS -->
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
            <a class="navbar-brand page-scroll" href="home">Home</a>
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
                    <a class="page-scroll" href="contact-page">Contact Us</a>
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
                            <li>
                                <form action="add-group-page" method="get">
                                    <button type="submit"
                                            class="btn btn-primary button-custom visible-when-logged-in page-scroll"><B>Create
                                        Group</B>
                                    </button>
                                </form>
                            </li>

                        </ul>
                        <br>
                    </div>
                    <div class="col-sm-9">
                        <h2>All Groups</h2>
                        <hr>
                        <br>

                        <div class="container">
                            <%
                                TournamentService tournamentService = (TournamentService) request.getAttribute("tournamentService");
                                List<Group> tournamentGroupsList = (List<Group>) request.getAttribute("allGroups");
                                int size = tournamentGroupsList.size();
                            %>
                            <table class="table">
                                <tr>
                                    <th width="1%">Check</th>
                                    <th width="3%">No</th>
                                    <th width="3%">Id</th>
                                    <th>Name</th>
                                    <th>Participant count</th>
                                    <th>Round</th>
                                    <th>Next round participants</th>
                                    <th>Tournament Id</th>
                                    <th>Tournament Name</th>
                                </tr>
                                <%
                                    for (int i = 0; i < size; i++) {
                                        int tournamentId = tournamentGroupsList.get(i).getTournamentId();
                                %>
                                <tr>
                                    <%--Radio--%>
                                    <td>
                                        <input type="radio" id="" class="checkbox-custom" name="" value="" required/>
                                    </td>
                                    <%--No--%>
                                    <td>
                                        <%=i%>
                                    </td>

                                    <%--Id--%>
                                    <td>
                                        <%=tournamentGroupsList.get(i).getGroupId()%>
                                    </td>

                                    <%--Name--%>
                                    <td>
                                        <%=tournamentGroupsList.get(i).getGroupName()%>
                                    </td>

                                    <%--Participant count--%>
                                    <td>
                                        <%=tournamentGroupsList.get(i).getParticipantsCount()%>
                                    </td>

                                    <%--Round--%>
                                    <td>
                                        <%--<%=tournamentGroupsList.get(i).getRound()%>--%>
                                    </td>

                                    <%--Next Round Participants--%>
                                    <td>
                                        <%=tournamentGroupsList.get(i).getNextRoundParticipants()%>
                                    </td>

                                    <%--Tournament Id--%>
                                    <td>
                                        <%=tournamentId%>
                                    </td>

                                    <%--Tournament Name--%>
                                    <td>
                                        <%
                                            Tournament tournament = tournamentService.getTournamentById(tournamentId);
                                        %>
                                        <%=tournament.getTournamentName()%>
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
</div>

<!-- jQuery -->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>

<%--Custom JS--%>
<script src="<c:url value="/resources/js/custom.js" />"></script>
</body>
</html>