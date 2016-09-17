<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.workfront.intern.cb.service.GroupService" %>
<%@ page import="com.workfront.intern.cb.common.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Participant</title>

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
                                <button class="btn btn-primary button-custom"><B>Create Team</B></button>
                            </li>
                            <BR>

                            <li>
                                <form action="add-members-page" method="get">
                                    <button type="submit" class="btn btn-primary button-custom"><B>ADD A MEMBER</B>
                                    </button>
                                </form>
                            </li>

                        </ul>
                        <br>
                    </div>

                    <div class="col-sm-9">
                        <h2><%=request.getParameter("groupName")%>
                        </h2>
                        <br>

                        <h3>Teams</h3>
                        <hr>
                        <br>

                        <%-------------=-=-=-=-=-=-=---TEAM'S TABLE---=-=-=-=-=-=-=-------------%>
                        <div class="container">
                            <div id="tableTeam" class="table-editable">
                                <%--Update Button--%>
                                <div class="btn-location-1">
                                    <button class="btn btn-warning " type="button" onclick="">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </button>
                                </div>

                                <%--Remove Button--%>
                                <form action="#" method="get" id="deleteMemberBtnId">
                                    <div class="btn-location-2">
                                        <button class="btn btn-danger " type="button" onclick="">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                    </div>
                                    <br>
                                    <br>

                                    <table class="table" id="1">
                                        <tr>
                                            <th width="1%">Check</th>
                                            <th width="3%">No</th>
                                            <th width="3%">Id</th>
                                            <th>Team Name</th>
                                            <th>Team Info</th>
                                            <th>Tournament Name</th>
                                        </tr>

                                        <tr>
                                            <%--Radio--%>
                                            <td>
                                                <input type="radio" id="" class="checkbox-custom"
                                                       name="groupId"
                                                       value="" required/>
                                            </td>

                                            <%--No--%>
                                            <td>
                                            </td>

                                            <%--Id--%>
                                            <td>
                                            </td>

                                            <%--Team name--%>
                                            <td>

                                            </td>

                                            <%--Team info--%>
                                            <td>
                                            </td>

                                            <%--TournamentName--%>
                                            <td>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>

                        <%-------------=-=-=-=-=-=-=---MEMBERS'S TABLE---=-=-=-=-=-=-=-------------%>
                        <br>
                        <h3>Members</h3>
                        <hr>
                        <br>

                        <div class="container">
                            <div id="table" class="table-editable">
                                <%--Update Button--%>
                                <div class="btn-location-1">
                                    <button class="btn btn-warning " type="button" onclick="">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </button>
                                </div>

                                <%--Remove Button--%>
                                <form action="#" method="get" id="deleteMemberBtnId">
                                    <div class="btn-location-2">
                                        <button class="btn btn-danger " type="button"
                                                onclick="deleteSelectedMember()">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                    </div>
                                    <br>
                                    <br>
                                    <%
                                        List<Member> membersList = (List<Member>) session.getAttribute("membersList");
                                        int listSize = membersList.size();
                                    %>

                                    <table class="table" id="updateTournamentTable">
                                        <tr>
                                            <th width="1%">Check</th>
                                            <th width="3%">No</th>
                                            <th width="3%">Id</th>
                                            <th>Name</th>
                                            <th>Surname</th>
                                            <th>Position</th>
                                            <th>Email</th>
                                            <th>Participant Info</th>
                                            <th>Tournament Name</th>
                                        </tr>
                                        <%
                                            for (int i = 0; i < listSize; i++) {
                                                int memberIDSelected = membersList.get(i).getId();
                                        %>

                                        <tr>
                                            <%--Radio--%>
                                            <td>
                                                <input type="radio" id="<%=memberIDSelected%>"
                                                       class="checkbox-custom"
                                                       name="groupId"
                                                       value="<%=memberIDSelected%>" required/>
                                            </td>

                                            <td>
                                                <%=i%>
                                            </td>

                                            <%--Id--%>
                                            <td>
                                                <%=memberIDSelected%>
                                            </td>

                                            <%--Name--%>
                                            <td>
                                                <%=membersList.get(i).getName()%>
                                            </td>

                                            <%--Surname--%>
                                            <td>
                                                <%=membersList.get(i).getSurName()%>
                                            </td>

                                            <%--Position--%>
                                            <td>
                                                <%=membersList.get(i).getPosition()%>
                                            </td>

                                            <%--Email--%>
                                            <td>
                                                <%=membersList.get(i).getEmail()%>
                                            </td>

                                            <%--Participant info--%>
                                            <td>
                                                <%=membersList.get(i).getParticipantInfo()%>
                                            </td>

                                            <%--TournamentName--%>
                                            <td>
                                                <%=request.getParameter("tournamentName")%>
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