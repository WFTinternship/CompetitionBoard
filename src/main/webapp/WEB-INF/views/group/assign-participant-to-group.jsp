<%@ page import="com.workfront.intern.cb.common.Tournament" %>
<%@ page import="java.util.List" %>
<%@ page import="com.workfront.intern.cb.common.Group" %>
<%@ page import="com.workfront.intern.cb.common.Member" %>
<%@ page import="com.workfront.intern.cb.common.Team" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Assign to Group</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Main CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/add-member.css"/>">


    <!-- Scripts -->
    <script src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

    <!-- Website Font customStyle.css -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
</head>


<body>
<div class="container">
    <div class="row main">
        <div class="main-login main-center">

            <h2>Assign to Group</h2>
            <hr>

            <form action="assignToGroup-form" class="form-horizontal" method="get" id="assignToGroupBtn">
                <%
                    List<Group> groupListByManager = (List<Group>) session.getAttribute("groupListByManager");
                    List<Member> memberListByTournament = (List<Member>) session.getAttribute("memberListByTournament");
                    List<Team> teamListByTournament = (List<Team>) session.getAttribute("teamListByTournament");

                %>

                <%--Group name--%>
                <div class="form-group">
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-futbol-o" aria-hidden="true"></i></span>
                            <select id="tournamentSelectId" name="groupId" class="form-control" required >
                                <option value="notSelected" selected="selected">Select Groups</option>
                                <%
                                    for (Group groupList : groupListByManager) {
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
                <div class="form-group">
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-futbol-o" aria-hidden="true"></i></span>
                            <select name="memberId" class="form-control" required >
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

                <%--Team name--%>
                <div class="form-group">
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-futbol-o" aria-hidden="true"></i></span>
                            <select id="" name="teamId" class="form-control" required >
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

                <div class="form-group ">
                    <br>
                    <br>
                    <input type="reset" class="btn btn-danger btn-lg btn-block login-button" value="Reset"/>
                    <br>
                    <input type="submit" class="btn btn-primary btn-lg btn-block login-button" value="Submit" />
                </div>

            </form>
        </div>
    </div>
</div>


<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
</body>
</html>
