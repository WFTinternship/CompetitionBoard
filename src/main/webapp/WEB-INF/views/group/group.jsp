<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.workfront.intern.cb.common.Group" %>
<%@ page import="com.workfront.intern.cb.service.TournamentService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.workfront.intern.cb.web.beans.BeanProvider" %>
<%@ page import="com.workfront.intern.cb.common.Participant" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>User's groups</title>

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
            <a class="navbar-brand page-scroll" href="home">HOME</a>
            <a><img class="avatar" src="<%=avatar%>"> </a>
            <a class="navbar-brand page-scroll"><%=welcomeStr + "" + userName%>
            </a>
        </div>
        <input type="hidden" id="login-status" value="<%=userName%>"/>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">

                <%--All Tournament--%>
                <li>
                    <a class=" page-scroll" href="<%=hrefToSpecificTournamentPage%>"><%=allTournaments%>
                    </a>
                </li>

                <%--Groups--%>
                <li>
                    <a class=" page-scroll" href="<%=hrefToSpecificGroupPage%>"><%=allGroups%>
                    </a>
                </li>

                <%--Match--%>
                <li>
                    <a class=" page-scroll" href="<%=hrefToSpecificMatchPage%>"><%=allMatches%>
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
                                            class="btn btn-primary button-custom visible-when-logged-in page-scroll">
                                        <B>Create Group</B>
                                    </button>
                                </form>
                            </li>

                        </ul>
                        <br>
                    </div>
                    <div class="col-sm-9">
                        <h2><%=userName%> groups</h2>
                        <hr>
                        <br>

                        <div class="container">
                            <div id="table" class="table-editable">

                                <%--Update Button--%>
                                <div class="btn-location-1">
                                    <button class="btn btn-warning" type="button" onclick="updateSelectedGroups()">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </button>
                                </div>

                                <%--Remove Button--%>
                                <form action="deleteGroup" method="get" id="deleteGroupBtnId">
                                    <div class="btn-location-2">
                                        <button class="btn btn-danger" type="button" onclick="deleteSelectedGroup()">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                    </div>

                                    <br>
                                    <br>
                                    <%
                                        TournamentService tournamentService = BeanProvider.getTournamentService();

                                        List<Group> groups = (List<Group>) request.getAttribute("groupsByManager");
                                        int size = groups.size();
                                    %>
                                    <table class="table" id="updateGroupTable">
                                        <tr>
                                            <th width="1%">Check</th>
                                            <th width="3%">No</th>
                                            <th width="3%">Id</th>
                                            <th>Name</th>
                                            <th>Participant count</th>
                                            <th>Round</th>
                                            <th>Next round participants</th>
                                            <th>Tournament Id</th>
                                            <th>Tournament name</th>
                                        </tr>
                                        <%
                                            for (int i = 0; i < size; i++) {
                                                int groupIDSelected = groups.get(i).getGroupId();
                                                session.setAttribute("groupIDSelected", groupIDSelected);
                                        %>
                                        <tr>
                                            <%--Radio--%>
                                            <td>
                                                <input type="radio" id="<%=groupIDSelected%>" class="checkbox-custom"
                                                       name="groupId" value="<%=groupIDSelected%>" required/>
                                            </td>

                                            <%--No--%>
                                            <td>
                                                <%=i%>
                                            </td>

                                            <%--Id--%>
                                            <td contenteditable="false" data-name="groupIDSelected"
                                                data-updatable="false">
                                                <%=groupIDSelected%>
                                            </td>

                                            <%--Name--%>
                                            <td contenteditable="false" data-name="groupName" data-updatable="true">
                                                <a href="participant-page?groupName=<%=groups.get(i).getGroupName()%>"
                                                   class="a-custom" name="hrefTournamentName">
                                                    <%=groups.get(i).getGroupName()%>
                                                </a>
                                            </td>

                                            <%--Participant count--%>
                                            <td contenteditable="false" data-name="participantCount"
                                                data-updatable="false">
                                                <%=groups.get(i).getParticipantsCount()%>
                                            </td>

                                            <%--Round--%>
                                            <td contenteditable="false" data-name="round" data-updatable="false">
                                                <%=groups.get(i).getRound()%>
                                            </td>

                                            <%--Next Round Participants--%>
                                            <td contenteditable="false" data-name="nextRoundParticipants"
                                                data-updatable="false">
                                                <%=groups.get(i).getNextRoundParticipants()%>
                                            </td>

                                            <%--Tournament Id--%>
                                            <%
                                                int tournamentIdSelected = groups.get(i).getTournamentId();
                                                session.setAttribute("tournamentIdSelected", tournamentIdSelected);
                                            %>
                                            <td contenteditable="false" data-name="tournamentId" data-updatable="false">
                                                <%=tournamentIdSelected%>
                                            </td>

                                            <%--Tournament name--%>
                                            <%
                                                String tournamentNameSelected = tournamentService.getTournamentById(groups.get(i).getTournamentId()).getTournamentName();
                                                session.setAttribute("tournamentNameSelected", tournamentNameSelected);

                                            %>
                                            <td contenteditable="false" data-name="tournamentName"
                                                data-updatable="false">
                                                <%=tournamentNameSelected%>
                                            </td>
                                        </tr>
                                        <%}%>
                                    </table>
                                </form>

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
</div>
<!-- jQuery -->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>

<%--Custom JS--%>
<script src="<c:url value="/resources/js/custom.js" />"></script>
</body>
</html>