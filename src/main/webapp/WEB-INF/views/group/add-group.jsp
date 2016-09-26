<%@ page import="com.workfront.intern.cb.common.Tournament" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add members</title>

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
    List<Tournament> tournamentList = (List<Tournament>) session.getAttribute("tournamentList");
    String name;
    int tournamentId;
    int size = tournamentList.size();
%>


<body>
<div class="container">
    <div class="row main">
        <div class="main-login main-center">

            <h2>Add Group</h2>
            <hr>

            <form action="addGroup-form" class="form-horizontal" method="get">

                <%--Name--%>
                <div class="form-group">
                    <label for="groupNameID" class="cols-sm-2 control-label">Group name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="nameGroup" id="groupNameID"
                                   placeholder="Group name" required/>
                        </div>
                    </div>
                </div>

                <%--Tournament name--%>
                <div class="form-group">
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-futbol-o" aria-hidden="true"></i></span>
                            <select id="tournamentSelectId" name="tournamentNameId" class="form-control" required >
                                <option value="notSelected" selected="selected">Select tournament</option>
                                <%
                                    for (Tournament aTournamentList : tournamentList) {
                                        name = aTournamentList.getTournamentName();
                                        tournamentId = aTournamentList.getTournamentId();
                                %>
                                <option value="<%=tournamentId%>"><%=name%>
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
