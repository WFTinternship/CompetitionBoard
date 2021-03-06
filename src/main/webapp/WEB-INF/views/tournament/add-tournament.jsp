<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add tournament form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/custom/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/custom.css"/>">

    <script src="<c:url value="/resources/js/custom.js" />"></script>
    <script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>
</head>

<%--Gets error message from SignInServlet--%>
<%@ include file="../layout/add-tournament-layout.jsp" %>

<body class="backgroundTournament">
<div class="container">

    <form action="addTournamentForm" class="form-horizontal" role="form" method="get">
        <h2>New Tournament</h2>
        <hr>

        <div class="err-msg"><%=existsTournament%></div>

    <%--Tournament Name--%>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">Name</label>
            <div class="col-sm-9">
                <input type="text" id="name" name="name" placeholder="Tournament Name"
                       class="form-control" autofocus required >
            </div>
        </div>

        <%--StartDate--%>
        <div class="form-group">
            <label for="startDate" class="col-sm-3 control-label">Start Time</label>
            <div class="col-sm-9">
                <input type="datetime-local" id="startDate" name="startDate" class="form-control" value="<%=today%>" onload="submitDateNameInForm()" />
            </div>
        </div>

        <%--EndDate--%>
        <div class="form-group">
            <label for="endDate" class="col-sm-3 control-label">End time</label>
            <div class="col-sm-9">
                <input type="datetime-local" id="endDate" name="endDate" class="form-control" value="<%=today%>" onload="submitDateNameInForm()" />
            </div>
        </div>

        <%--Location--%>
        <div class="form-group">
            <label for="location" class="col-sm-3 control-label">Location</label>
            <div class="col-sm-9">
                <input type="text" id="location" name="location" placeholder="Location" class="form-control" required>
            </div>
        </div>

        <%--Description--%>
        <div class="form-group">
            <label  class="col-sm-3 control-label">Description</label>
            <div class="col-sm-9">
                <label for="field2"></label>
                <textarea class="form-control textarea-custom" rows="5" cols="20" name="tournament_description" id="field2"></textarea>
            </div>
        </div>

        <%--TournamentFormat--%>
        <div class="form-group">
            <label for="formatId" class="col-sm-3 control-label">Format</label>
            <div class="col-sm-9">
                <select id="formatId" name="format" class="form-control">
                    <%--<option value="0" selected="selected">Select format</option>--%>
                    <option value="1">Round Robin</option>
                    <option value="2">Olympic</option>
                </select>
            </div>
        </div>

        <!-- /.form-group -->
        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <input type="reset" class="btn btn-danger " value="Reset">
                <br><br>
                <input type="submit" class="btn btn-primary button-custom" value="Save and continue">
            </div>
        </div>
    </form>
    <!-- /form -->
</div>
<!-- ./container -->
</body>
</html>
