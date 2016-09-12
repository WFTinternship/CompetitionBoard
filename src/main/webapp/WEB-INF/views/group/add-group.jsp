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

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
</head>


<body>
<div class="container">
    <div class="row main">
        <div class="main-login main-center">

            <h2>Add Member</h2>
            <hr>

            <form action="addGroupForm" class="form-horizontal" method="post" >
                <%--Name--%>
                <div class="form-group">
                    <label for="groupId" class="cols-sm-2 control-label">Group name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="nameGroup" id="groupId"
                                   placeholder="Group name" />
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
