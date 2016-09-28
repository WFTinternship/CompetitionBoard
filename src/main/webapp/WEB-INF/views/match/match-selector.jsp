<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.workfront.intern.cb.common.Group" %>
<%@ page import="com.workfront.intern.cb.common.Member" %>
<%@ page import="com.workfront.intern.cb.common.Team" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>User's match selector</title>

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


<%
    List<Group> groupsMatchByManager = (List<Group>) session.getAttribute("groupsMatchByManager");
    int size = groupsMatchByManager.size();
    String groupName;
    int groupId;

    List<Team> teamListMatch = (List<Team>) session.getAttribute("teamListMatch");
    int teamListMatchSize = teamListMatch.size();
    int participant1Id;
    int participant2Id;
    String participant1;
    String participant2;

    List<Member> memberListMatch = (List<Member>) session.getAttribute("memberListMatch");
    int memberListMatchSize = memberListMatch.size();
%>

<body>
<div class="container">
    <div class="row main">
        <div class="main-login main-center">

            <h2>Select the group
                and participants</h2>
            <hr>

            <form action="add-match" class="form-horizontal" method="get">

             <%--Group name--%>
                <div class="form-group">
                    <label for="groupSelectId" class="cols-sm-2 control-label">Group</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-futbol-o" aria-hidden="true"></i></span>
                            <select id="groupSelectId" name="groupSelectId" class="form-control" required >
                                <option value="notSelected" selected="selected">Select Group</option>
                                <%
                                    for (int i = 0; i < size; i++) {
                                        groupName = groupsMatchByManager.get(i).getGroupName();
                                        groupId = groupsMatchByManager.get(i).getGroupId();
                                %>
                                <option value="<%=groupId%>"><%=groupName%>
                                    <%}%>
                                </option>
                            </select>
                        </div>
                    </div>
                </div>

                 <%--Participant 1--%>
                 <div class="form-group">
                     <label for="participant1MatchId" class="cols-sm-2 control-label">Select Participant 1</label>
                     <div class="cols-sm-10">
                         <div class="input-group">
                             <span class="input-group-addon"><i class="fa fa-futbol-o" aria-hidden="true"></i></span>
                             <select id="participant1MatchId" name="participant1MatchId" class="form-control" required >
                                 <option value="notSelected" selected="selected">Select Participant 1</option>
                                 <%
                                     for (int i = 0; i < teamListMatchSize; i++) {
                                         participant1 = teamListMatch.get(i).getTeamName();
                                         participant1Id = teamListMatch.get(i).getId();
                                 %>
                                 <option value="<%=participant1Id%>"><%=participant1%>
                                     <%}%>
                                 </option>
                             </select>
                         </div>
                     </div>
                 </div>

                 <%--Participant 2--%>
                 <div class="form-group">
                     <label for="participant2MatchId" class="cols-sm-2 control-label">Select Participant 2</label>
                     <div class="cols-sm-10">
                         <div class="input-group">
                             <span class="input-group-addon"><i class="fa fa-futbol-o" aria-hidden="true"></i></span>
                             <select id="participant2MatchId" name="participant2MatchId" class="form-control" required >
                                 <option value="notSelected" selected="selected">Select Participant 2</option>
                                 <%
                                     for (int i = 0; i < teamListMatchSize; i++) {
                                         participant2 = teamListMatch.get(i).getTeamName();
                                         participant2Id = teamListMatch.get(i).getId();
                                 %>
                                 <option value="<%=participant2Id%>"><%=participant2%>
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
                    <input type="submit" class="btn btn-primary btn-lg btn-block login-button" value="Start Match" />
                </div>

            </form>
        </div>
    </div>
</div>


<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
</body>
</html>
