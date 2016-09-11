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

            <form action="/addMemberForm" class="form-horizontal" method="post" >
                <%--Name--%>
                <div class="form-group">
                    <label for="nameMember" class="cols-sm-2 control-label">Name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="nameMember" id="nameMember"
                                   placeholder="Name" />
                        </div>
                    </div>
                </div>

                <%--SurName--%>
                <div class="form-group">
                    <label for="surNameMember" class="cols-sm-2 control-label">Surname</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="surNameMember" id="surNameMember"
                                   placeholder="Surname"/>
                        </div>
                    </div>
                </div>

                <%--Position--%>
                <div class="form-group">
                    <label for="positionMember" class="cols-sm-2 control-label">Position</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-wrench fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="positionMember" id="positionMember"
                                   placeholder="Position"/>
                        </div>
                    </div>
                </div>

                <%--Avatar--%>
                <%--<div class="form-group">--%>
                    <%--<label for="username" class="cols-sm-2 control-label">Avatar</label>--%>
                    <%--<div class="cols-sm-10">--%>
                        <%--<div class="input-group">--%>
                            <%--<span class="input-group-addon"><i class="fa fa-upload fa" aria-hidden="true"></i></span>--%>
                            <%--<input type="file" class="form-control" name="username" id="file"--%>
                                   <%--placeholder="Enter your Username"/>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>

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
