<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.workfront.intern.cb.common.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>All available matches</title>

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
<%@ include file="../layout/bean-provider-layout.jsp" %>

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
                    <a class="page-scroll" href="#">Gallery</a>
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
                        <h2>All available matches</h2>
                        <hr>
                        <br>

                        <div class="container">
                            <div id="table" class="table-editable">

                                <%
                                    List<Match> matchList = matchService.getAllMatchList();
                                    int size = matchList.size();
                                %>

                                <%--Update Button--%>
                                <div class="btn-location-1">
                                    <button class="btn btn-warning" type="button" onclick="">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </button>
                                </div>

                                <%--Remove Button--%>
                                <form action="deleteGroup" method="get" id="deleteGroupBtnId">
                                    <div class="btn-location-2">
                                        <button class="btn btn-danger" type="button" onclick="">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                    </div>

                                    <br>
                                    <br>
                                    <table class="table" id="updateGroupTable">
                                        <tr>
                                            <th width="3%">No</th>
                                            <%--<th width="10%">Match Id</th>--%>
                                            <th width="10%">Group name</th>
                                            <th>Participant 1</th>
                                            <th>Participant 2</th>
                                            <th width="10%">Score Participant 1</th>
                                            <th width="10%">Score Participant 2</th>
                                            <th width="10%">Match Score</th>
                                        </tr>
                                        <%
                                            for (int i = 0; i < size; i++) {
                                                int matchId = matchList.get(i).getMatchId();
                                        %>

                                        <tr>
                                            <%--No--%>
                                            <td>
                                                <%=i + 1%>
                                            </td>

                                            <%--Id--%>
                                            <%--<td>--%>
                                            <%--<%=matchId%>--%>
                                            <%--</td>--%>

                                            <%--Group Name--%>
                                            <td contenteditable="false" data-name="groupName" data-updatable="true">
                                                <a href="participant-page?groupName="
                                                   class="a-custom" name="hrefTournamentName">
                                                </a>
                                                <%
                                                    int groupId = matchList.get(i).getGroupId();
                                                    Group group = groupService.getGroupById(groupId);
                                                    String groupName = group.getGroupName();
                                                %>
                                                <%=groupName%>
                                            </td>

                                            <%--Participant 1--%>
                                            <td>
                                                <%
                                                    int participantOneId = matchList.get(i).getParticipantOneId();
                                                    Team participantOne = (Team) participantService.getOne(Team.class, participantOneId);
                                                    String participantOneName = participantOne.getTeamName();
                                                %>
                                                <%=participantOneName%>
                                            </td>

                                            <%--Participant 2--%>
                                            <td>
                                                <%
                                                    int participantTwoId = matchList.get(i).getParticipantTwoId();
                                                    Team participantTwo = (Team) participantService.getOne(Team.class, participantTwoId);
                                                    String participantTwoName = participantTwo.getTeamName();
                                                %>
                                                <%=participantTwoName%>
                                            </td>

                                            <%--Score Participant 1--%>
                                            <td>
                                                <%= matchList.get(i).getScoreParticipantOne()%>
                                            </td>

                                            <%--Score Participant 2--%>
                                            <td>
                                                <%= matchList.get(i).getScoreParticipantTwo()%>
                                            </td>

                                            <%--Match score--%>
                                            <td contenteditable="false" data-name="matchScore" data-updatable="true">
                                                <%=matchList.get(i).getMatchScore()%>
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