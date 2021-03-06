<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.workfront.intern.cb.common.Member" %>
<%@ page import="com.workfront.intern.cb.common.Team" %>
<%@ page import="com.workfront.intern.cb.common.Tournament" %>
<%@ page import="com.workfront.intern.cb.web.beans.BeanProvider" %>
<%@ page import="com.workfront.intern.cb.common.Group" %>
<%@ page import="java.util.*" %>

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
    <script src="<c:url value="/resources/js/custom.js" />"></script>

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<%--Gets specific atributes from http session--%>
<%@ include file="../layout/layout.jsp" %>
<%@ include file="../layout/participant-layout.jsp" %>

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
            <a><img class="avatar" src="<%=avatar %>"> </a>
            <a class="navbar-brand page-scroll"><%=welcomeStr + "" + userName %>
            </a>
        </div>
        <input type="hidden" id="login-status" value="<%=userName %>"/>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">

                <%--All Tournament--%>
                <li>
                    <a class=" page-scroll" href="<%=hrefToSpecificTournamentPage %>"><%=allTournaments %>
                    </a>
                </li>

                <%--Groups--%>
                <li>
                    <a class=" page-scroll" href="<%=hrefToSpecificGroupPage %>"><%=allGroups %>
                    </a>
                </li>

                <%--Match--%>
                <li>
                    <a class=" page-scroll" href="<%=hrefToSpecificMatchPage %>"><%=allMatches %>
                    </a>
                </li>

                <%--Gallery--%>
                <li>
                    <a class="page-scroll" href="">Gallery</a>
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

                            <%-------------------- CREATE TEAM BUTTON --------------------%>
							<% if (!isStared) { %>
								<li class="<%=showTeamElement %>">
									<form action="add-teams-page" method="get">
										<button type="submit" class="btn btn-primary button-custom visible-element">
											<B>ADD TEAM</B>
										</button>
									</form>
								</li>
							<% } %>
                            <BR>

                            <%-------------------- CREATE MEMBER BUTTON --------------------%>
							<% if (!isStared) { %>
								<li class="<%=showMemberElement %>">
									<form action="add-members-page" method="get">
										<button type="submit" class="btn btn-primary button-custom visible-element">
											<B>ADD MEMBER</B>
										</button>
									</form>
								</li>
							<% } %>
                        </ul>
                        <br>
                    </div>

                    <div class="col-sm-9">
						<h2 class="title">
							Tournament: <%=tournamentName %>
						</h2>
						<% if (isStared) { %>
						(<i style="color:lawngreen">is started</i>)
						<% } else { %>
						(<i style="color:red">not started</i>)
						<% } %>
						<br><br>

						<%-- Show Groups Info if tournament started --%>
						<% if (groupsMap.size() > 0) { %>
							<% for (Map.Entry<Integer, List<Group>> entry : groupsMap.entrySet()) { %>
								<% if (entry.getValue().size() > 0) { %>
									<div class="text-15">Round <%=entry.getKey() %> groups: (<%=entry.getValue().size() %>) &nbsp; (
										<a class="action-link" href="group-tournament-page">view groups</a>
									)</div>
								<% } %>
							<% } %>
						<% } %>
						<br><br><br>

                        <div class="<%=showTeamElement %>">
							<div style="margin-bottom: -17px;">
								<h3>Participant: Teams (<%=teamListSize %>)</h3>
								<hr>
							</div>

                            <%-------------=-=-=-=-=-=-=---TEAM'S TABLE---=-=-=-=-=-=-=-------------%>
							<div id="tableTeam" class="table-editable">

								<%------------ Assign to group Button ------------%>
								<div class="<%=showElement %>">
								<form action="assign-participant-to-group-page" method="get" id="assignTeamGroupBtnId">
									<div class="btn-location-0">
										<button class="btn btn-primary button-custom visible-when-logged-in"
												name="assignToGroupBtn" value="1" type="submit">
											Add to Group
										</button>
									</div>
								</form>
								</div>

								<%--Update Button--%>
								<div class="btn-location-1">
									<button class="btn btn-warning visible-when-logged-in" type="button"
											onclick="updateSelectedTeam()">
										<span class="glyphicon glyphicon-edit"></span>
									</button>
								</div>

								<%--Remove Button--%>
								<form action="deleteTeam" method="get" id="deleteTeamBtnId">
									<div class="btn-location-2">
										<button class="btn btn-danger visible-when-logged-in" type="button"
												onclick="deleteSelectedTeam()">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
									</div>
									<br>
									<br>

									<div class="err-msg-delete">
									<c:out value="${errMsgTeam}"/>
										</div>

									<div class="container-custom">
									<table class="table" id="updateTeamTable">
										<tr>
											<th width="1%">Check</th>
											<th width="3%">No</th>
											<th>Team Name</th>
											<th>Team Info</th>
										</tr>
										<%
											for (int j = 0; j < teamListSize; j++) {
												int teamId = teamListByTournament.get(j).getId();
										%>
										<tr>
											<%--Radio--%>
											<td>
												<input type="radio" id="<%=teamId %>" class="checkbox-custom"
													   name="teamNameId" value="<%=teamId%>" required/>
											</td>

											<%--No--%>
											<td>
												<%=j + 1 %>
											</td>

											<%--Team name--%>
											<td contenteditable="false" data-name="teamName" data-updatable="true">
												<%=teamListByTournament.get(j).getTeamName() %>
											</td>

											<%--Team info--%>
											<td contenteditable="false" data-name="teamInfo" data-updatable="true">
												<%=teamListByTournament.get(j).getParticipantInfo() %>
											</td>
										</tr>
										<%}%>
									</table>
										</div>
								</form>
							</div>
						</div>

                        <br>

                        <div class="<%=showMemberElement %>">
							<div style="margin-bottom: -17px;">
								<h3>Participants: Members (<%=memberListSize %>)</h3>

								<hr>
							</div>

							<%-------------=-=-=-=-=-=-=---MEMBERS'S TABLE---=-=-=-=-=-=-=-------------%>
							<div id="table" class="table-editable">

								<div>
									<%------------ Assign to group Button ------------%>
										<div class="<%=showElement %>">

										<form action="assign-participant-to-group-page" method="get" id="assignMemberToGroupBtn">
										<div class="btn-location-0">
											<button class="btn btn-primary button-custom visible-when-logged-in"
													name="assignToGroupBtn" value="5" type="submit">
												Add to Group
											</button>
										</div>
									</form>
								</div>

								<%------------ Update Button ------------%>
								<div class="btn-location-1">
									<button class="btn btn-warning visible-when-logged-in" type="button"
											onclick="updateSelectedMember()">
										<span class="glyphicon glyphicon-edit"></span>
									</button>
								</div>

								<%------------ Remove Button ------------%>
								<form action="deleteMember" method="get" id="deleteMemberBtnId">
									<div class="btn-location-2">
										<button class="btn btn-danger visible-when-logged-in " type="button"
												onclick="deleteSelectedMembers()">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
									</div>
									<br>
									<br>

									<div class="container-custom">
									<table class="table" id="updateMemberTable">
										<tr>
											<th width="1%">Check</th>
											<th width="3%">No</th>
											<th>Name</th>
											<th>Surname</th>
											<th>Position</th>
											<th>Email</th>
											<th>Participant Info</th>
										</tr>
										<%
											for (int i = 0; i < memberListSize; i++) {
												int memberId = memberListByTournament.get(i).getId();
										%>
										<tr>
											<%--Radio--%>
											<td>
												<input type="radio" id="<%=memberId%>" class="checkbox-custom"
													   name="memberNameId" value="<%=memberId%>" required/>
											</td>

											<td>
												<%=i+1%>
											</td>

											<%--Name--%>
											<td contenteditable="false" data-name="memberName"
												data-updatable="true">
												<%=memberListByTournament.get(i).getName()%>
											</td>

											<%--Surname--%>
											<td contenteditable="false" data-name="memberSureName"
												data-updatable="true">
												<%=memberListByTournament.get(i).getSurName()%>
											</td>

											<%--Position--%>
											<td contenteditable="false" data-name="memberPosition"
												data-updatable="true">
												<%=memberListByTournament.get(i).getPosition()%>
											</td>

											<%--Email--%>
											<td contenteditable="false" data-name="memberEmail"
												data-updatable="true">
												<%=memberListByTournament.get(i).getEmail()%>
											</td>

											<%--Participant info--%>
											<td contenteditable="false" data-name="memberInfo"
												data-updatable="true">
												<%=memberListByTournament.get(i).getParticipantInfo()%>
											</td>
										</tr>
										<%}%>
									</table>
										</div>
								</form>
							</div>

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