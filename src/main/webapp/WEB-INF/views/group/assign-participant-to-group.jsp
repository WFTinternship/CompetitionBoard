<%@ page import="com.workfront.intern.cb.common.Group" %>
<%@ page import="com.workfront.intern.cb.common.Member" %>
<%@ page import="com.workfront.intern.cb.common.Team" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Assign to Group</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Main CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">

    <link rel="stylesheet" href="<c:url value="/resources/css/blog-home.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/creative.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">


    <!-- Custom CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/add-member.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/custom.css"/>">

    <!-- Scripts -->
    <script src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

    <!-- Website Font customStyle.css -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
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


<div class="container">
    <div class="row main">
        <div class="main-login main-center">

            <h2>Assign to Group</h2>
            <hr class="hr-custom">

            <form action="assignToGroup-form" class="form-horizontal" method="get" id="assignToGroupBtn">
                <%
                    int assignToGroupBtnValue = (int) session.getAttribute("assignToGroupBtnValue");
                    String showTeamElement = null;
                    String showMemberElement = null;

                    List<Team> teamListByTournament = (List<Team>) session.getAttribute("teamListByTournament");
                    List<Member> memberListByTournament = (List<Member>) session.getAttribute("memberListByTournament");

                    List<Group> groupsByCurrentTournament = (List<Group>) session.getAttribute("groupsByCurrentTournament");

                    if (assignToGroupBtnValue == 1) {
                        showTeamElement = "show-element";
                        showMemberElement = "hidden-element";
                    } else if (assignToGroupBtnValue == 5) {
                        showTeamElement = "hidden-element";
                        showMemberElement = "show-element";
                    }
                %>

                <%--Group name--%>
                <div class="form-group">
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-futbol-o" aria-hidden="true"></i></span>
                            <select id="groupSelectId" name="groupId" class="form-control" required title="">
                                <option value="notSelected" selected="selected">Select Groups</option>
                                <%
                                    for (Group groupList : groupsByCurrentTournament) {
                                        String name = groupList.getGroupName();
                                        int groupId = groupList.getGroupId();
                                %>
                                <option value="<%=groupId%>"><%=name%>
                                    <%}%>
                                </option>
                            </select>
                        </div>
                    </div>
                </div>

                <%--Member name--%>
                <div class="<%=showMemberElement%>">
                    <div class="form-group ">
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-futbol-o" aria-hidden="true"></i></span>
                                <select name="memberId" class="form-control" required title="">
                                    <option value="notSelected" selected="selected">Select Members</option>
                                    <%
                                        for (Member member : memberListByTournament) {
                                            String memberName = member.getName();
                                            int memberId = member.getId();
                                    %>
                                    <option value="<%=memberId%>"><%=memberName%>
                                        <%}%>
                                    </option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <%--Team name--%>
                <div class="<%=showTeamElement%>">
                    <div class="form-group ">
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-futbol-o" aria-hidden="true"></i></span>
                                <select id="teamSelectId" name="teamId" class="form-control" required title="">
                                    <option value="notSelected" selected="selected">Select Teams</option>
                                    <%
                                        for (Team team : teamListByTournament) {
                                            String teamName = team.getTeamName();
                                            int teamId = team.getId();
                                    %>
                                    <option value="<%=teamId%>"><%=teamName%>
                                        <%}%>
                                    </option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <br>
                    <br>
                    <button type="reset" class="btn btn-danger btn-lg btn-block login-button" value="Reset">Reset</button>
                    <br>
                    <button type="submit" class="btn btn-primary button-custom btn-lg btn-block login-button" value="Submit">Submit</button>
                </div>
            </form>

            <div class="btn-location-3">
                <form action="add-group-page" method="get">
                    <input type="submit" class="btn btn-primary button-custom btn-lg btn-block login-button" value="Create Group"/>
                    <br>
                </form>
            </div>

        </div>
    </div>
</div>

<%--Custom JS--%>
<script src="<c:url value="/resources/js/custom.js" />"></script>

<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
</body>
</html>