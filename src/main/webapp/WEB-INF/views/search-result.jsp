<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.workfront.intern.cb.common.Tournament" %>
<%@ page import="com.workfront.intern.cb.common.TournamentFormat" %>
<%@ page import="com.workfront.intern.cb.web.util.Helpers" %>
<%@ page import="java.util.List" %>
<%@ page import="com.workfront.intern.cb.common.Group" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Search result...</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
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

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>

<%@ include file="layout/layout.jsp" %>

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
                        <h2>Search results...</h2>
                        <hr>
                        <br>


                        <%-------------------------------------------TOURNAMENT RESULT-------------------------------------------%>
                        <div class="container">
                            <div id="table" class="table-editable">

                                <h3>Tournaments</h3>
                                <hr>
                                    <%
                                        List<Tournament> tournamentList = (List<Tournament>) request.getAttribute("searchResultTournament");
                                        int tournamenListSize = tournamentList.size();
                                    %>
                                    <table class="table" id="updateTournamentTable">
                                        <tr class="thCustom">
                                            <th>No</th>
                                            <th>Id</th>
                                            <th>Name</th>
                                            <th>StartDate</th>
                                            <th>EndDate</th>
                                            <th>Location</th>
                                            <th>Description</th>
                                            <th>Format</th>
                                            <th>Owner</th>
                                        </tr>
                                        <% for (int i = 0; i < tournamenListSize; i++) {
                                            int tournamentId = tournamentList.get(i).getTournamentId();
                                        %>
                                        <tr>
                                          <%--No--%>
                                            <td contenteditable="false">
                                                <%=i%>
                                            </td>

                                            <%--Id--%>
                                            <td contenteditable="false" data-name="tournamentNameId" data-updatable="false">
                                                <%=tournamentId%>
                                            </td>

                                            <%--TournamentName--%>
                                            <td contenteditable="false" data-name="nameUpdate" data-updatable="true">
                                                <a href="participant-page" class="a-custom" name="hrefTournamentName">
                                                    <%=tournamentList.get(i).getTournamentName()%>
                                                </a>
                                            </td>

                                            <%--StartDate--%>
                                            <td contenteditable="false" data-name="startDateUpdate" data-updatable="true">
                                                <%=Helpers.parseTimeStampToString(tournamentList.get(i).getStartDate())%>
                                            </td>

                                            <%--EndDate--%>
                                            <td contenteditable="false" data-name="endDateUpdate" data-updatable="true">
                                                <%=Helpers.parseTimeStampToString(tournamentList.get(i).getEndDate())%>
                                            </td>

                                            <%--Location--%>
                                            <td contenteditable="false" data-name="locationUpdate" data-updatable="true">
                                                <%=tournamentList.get(i).getLocation()%>
                                            </td>

                                            <%--TournamentDescription--%>
                                            <td contenteditable="false" data-name="descriptionUpdate" data-updatable="true">
                                                <%=tournamentList.get(i).getTournamentDescription()%>
                                            </td>

                                            <%--TournamentFormatId--%>
                                            <%
                                                int tournamentFormatId = tournamentList.get(i).getTournamentFormatId();
                                                String formatStr = TournamentFormat.parseTournamentFormatIdToString(tournamentFormatId);
                                            %>
                                            <td contenteditable="false" data-name="formatUpdateNot" data-updatable="false">
                                                <%=formatStr%>
                                            </td>

                                            <%--Tournament creator--%>
                                            <%--<%--%>
                                                <%--assert manager != null;--%>
                                                <%--String managerName = manager.getLogin();--%>
                                            <%--%>--%>
                                            <td contenteditable="false" data-name="managerUpdateNot" data-updatable="false">
                                                <%--<%= managerName%>--%>
                                            </td>
                                        </tr>
                                        <%}%>
                                    </table>


                                <%-------------------------------------------GROUP RESULT-------------------------------------------%>
                                <br>
                                <h3>Groups</h3>
                                <hr>
                                <%
                                    List<Group> groupList = (List<Group>) request.getAttribute("searchResultGroup");
                                    int groupListSize = groupList.size();
                                %>



                                <table class="table" id="updateGroupTable">
                                    <tr>
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
                                        for (int i = 0; i < groupListSize; i++) {
                                    %>

                                    <tr>
                                       <%--No--%>
                                        <td>
                                            <%=i%>
                                        </td>

                                        <%--Id--%>
                                        <td contenteditable="false" data-name="groupIDSelected"
                                            data-updatable="false">
                                            <%=groupList.get(i).getGroupId()%>
                                            ${groupList.get}
                                        </td>

                                        <%--Name--%>
                                        <td contenteditable="false" data-name="groupName" data-updatable="true">
                                            <a href="participant-page?groupName=<%=groupList.get(i).getGroupName()%>"
                                               class="a-custom" name="hrefTournamentName">
                                                <%=groupList.get(i).getGroupName()%>
                                            </a>
                                        </td>

                                        <%--Participant count--%>
                                        <td contenteditable="false" data-name="participantCount"
                                            data-updatable="false">
                                            <%=groupList.get(i).getParticipantsCount()%>
                                        </td>

                                        <%--Round--%>
                                        <td contenteditable="false" data-name="round" data-updatable="false">
                                            <%=groupList.get(i).getRound()%>
                                        </td>

                                        <%--Next Round Participants--%>
                                        <td contenteditable="false" data-name="nextRoundParticipants"
                                            data-updatable="false">
                                            <%=groupList.get(i).getNextRoundParticipants()%>
                                        </td>

                                        <%--Tournament Id--%>
                                        <%
                                            int tournamentIdSelected = groupList.get(i).getTournamentId();
                                        %>
                                        <td contenteditable="false" data-name="tournamentId" data-updatable="false">
                                            <%=tournamentIdSelected%>
                                        </td>

                                        <%--Tournament name--%>
                                        <%--<%--%>
                                            <%--String tournamentNameSelected = tournamentService.getTournamentById(groups.get(i).getTournamentId()).getTournamentName();--%>
                                            <%--session.setAttribute("tournamentNameSelected", tournamentNameSelected);--%>

                                        <%--%>--%>
                                        <td contenteditable="false" data-name="tournamentName" data-updatable="false">
                                            <%--<%=tournamentNameSelected%>--%>
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
        <script src="<c:url value="/resources/js/jquery-3.1.0.js" />"></script>
        <%--<script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>--%>

        <%--Custom JS--%>
        <script src="<c:url value="/resources/js/custom.js" />"></script>

        <script src='http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js'></script>
        <%--<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>--%>

        <script src='http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.6.0/underscore.js'></script>
</body>
</html>