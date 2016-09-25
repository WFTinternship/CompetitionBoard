<%@ page import="com.workfront.intern.cb.common.Tournament" %>
<%@ page import="java.util.List" %>
<%@ page import="com.workfront.intern.cb.web.beans.BeanProvider" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add member form</title>

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

    <!-- Website Font style -->
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

            <h2>Add Member
                <p>to
                <p><%=tournament.getTournamentName()%>

            </h2>
            <hr>

            <form action="addMember-form" class="form-horizontal" method="get">

                <%--Name--%>
                <div class="form-group">
                    <label for="memberId" class="cols-sm-2 control-label">Name</label>

                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="nameMember" id="memberId"
                                   placeholder="Name" required/>
                        </div>
                    </div>
                </div>

                <%--SurName--%>
                <div class="form-group">
                    <label for="surNameId" class="cols-sm-2 control-label">Surname</label>

                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="surNameMember" id="surNameId"
                                   placeholder="Surname" required/>
                        </div>
                    </div>
                </div>

                <%--Position--%>
                <div class="form-group">
                    <label for="positionId" class="cols-sm-2 control-label">Position</label>

                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-wrench fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="positionMember" id="positionId"
                                   placeholder="Position"/>
                        </div>
                    </div>
                </div>

                <%--Email--%>
                <div class="form-group">
                    <label for="emailId" class="cols-sm-2 control-label">Email</label>

                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="email" class="form-control" name="emailMember" id="emailId"
                                   placeholder="Email"/>
                        </div>
                    </div>
                </div>

                <%--Participant info--%>
                <div class="form-group">
                    <label for="infoId" class="cols-sm-2 control-label">Info</label>

                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-info fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="infoMember" id="infoId"
                                   placeholder="Info"/>
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
