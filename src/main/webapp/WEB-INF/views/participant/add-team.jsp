<%@ page import="com.workfront.intern.cb.common.Tournament" %>
<%@ page import="java.util.List" %>
<%@ page import="com.workfront.intern.cb.web.beans.BeanProvider" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add team form</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Main CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">

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

<%
    int selectedTournamentId = (int) session.getAttribute("selectedTournamentId");
    Tournament tournament = BeanProvider.getTournamentService().getTournamentById(selectedTournamentId);
%>

<body>
<div class="container">
    <div class="row main">
        <div class="main-login main-center">

            <h2>Add Team
                <p>to
                <p><%=tournament.getTournamentName()%>

            </h2>
            <hr>

            <div class="err-msg">
                <c:out value="${errMsgTeam}"/>

            </div>

            <form action="addTeam-form" class="form-horizontal" method="get">
                <%--Team Name--%>
                <div class="form-group">
                    <label for="teamId" class="cols-sm-2 control-label">Team name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="nameTeam" id="teamId"
                                   placeholder="Team name" required/>
                        </div>
                    </div>
                </div>

                <%--Team info--%>
                <div class="form-group">
                    <label for="infoTeamId" class="cols-sm-2 control-label">Team info</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-info fa" aria-hidden="true"></i></span>
                            <textarea class="form-control textarea-custom" name="teamInfo" id="infoTeamId"
                                      ></textarea>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <input type="reset" class="btn btn-danger btn-lg btn-block login-button" value="Reset"/>
                    <input type="submit" class="btn btn-primary btn-lg btn-block login-button" value="Submit"/>
                </div>

            </form>
        </div>
    </div>
</div>


<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
</body>
</html>
