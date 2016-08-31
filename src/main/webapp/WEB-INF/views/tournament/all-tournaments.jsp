<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.workfront.intern.cb.common.Manager" %>
<%@ page import="com.workfront.intern.cb.common.TournamentFormat" %>
<%@ page import="com.workfront.intern.cb.common.Tournament" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>All available tournaments</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/blog-home.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/creative.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/custom.css"/>">


    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<%--Gets specific atributes from http session--%>
<%
    String userName = "";
    String welcomeStr = "";
    String hrefToSpecificTournamentPage = "all-tournaments-page";
    String avatar = "";

    String addTournamentMenuItem = null;
    String classStr = null;

    Manager manager  = (Manager) session.getAttribute("manager");
    if (manager  != null) {
        avatar = "resources/img/user_avatar/" + manager .getAvatar();
        userName = manager .getLogin();
        welcomeStr = "Hi, ";
        hrefToSpecificTournamentPage = "tournament-page";

        addTournamentMenuItem = "Add Tournament";
        classStr = "visible-element";
    }

    if (userName.equals("")) {
        addTournamentMenuItem = "";
        classStr = "hidden-element";
    }
%>


<body class="backgroundTournament">

<!-- Navigation -->
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand page-scroll" href="/">Home</a>
            <a ><img class="avatar" src="<%=avatar%>" > </a>
            <a class="navbar-brand page-scroll"><%=welcomeStr + "" + userName%></a>
        </div>
        <input type="hidden" id="login-status" value="<%=userName%>" />


        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a class="visible-when-logged-in page-scroll" href="addTournament-page" id="<%=classStr%>" onload="showMenuItem()"><%=addTournamentMenuItem%></a></li>
                <li><a class=" page-scroll" href="<%=hrefToSpecificTournamentPage%>">Tournaments</a></li>
                <li type="hide"><a class="page-scroll" href="tournament/match.jsp">Matches</a></li>
                <li><a class="page-scroll" href="#portfolio">Gallery</a></li>
                <li><a class="page-scroll" href="contact-page">Contact Us</a></li>
                <li><a href="signup-page" class="hidden-when-logged-in">Sign Up</a></li>
                <li><a href="login-page" class="hidden-when-logged-in">Log In </a></li>
                <li><a href="logout-page" class="visible-when-logged-in hidden-element">Log Out</a></li>
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

                    <%--LEFT BLOCK--%>

                        </ul>
                        <br>
                    </div>

                    <div class="col-sm-9">
                        <h2>All available tournaments</h2>
                        <hr>

                        <%
                            List<Tournament> tournamentList = (List<Tournament>) session.getAttribute("allTournamentList");
                            int sizeList = tournamentList.size();
                        %>
                        <table class="tournamentTable">
                            <tr class="searchTblResultTr">
                                <th>No</th>
                                <th>TournamentId</th>
                                <th>TournamentName</th>
                                <th>StartDate</th>
                                <th>EndDate</th>
                                <th>Location</th>
                                <th>TournamentDescription</th>
                                <th>TournamentFormat</th>
                                <th>Tournament creator</th>
                            </tr>
                            <%
                                for (int i = 0; i < sizeList; i++) {
                            %>
                            <tr>
                                <%--No--%>
                                <td>
                                    <%=i%>
                                </td>

                                <%--TournamentId--%>
                                <td>
                                    <%--<%=request.getAttribute("tournamentId"+i)%>--%>
                                    <%=tournamentList.get(i).getTournamentId()%>
                                </td>

                                <%--TournamentName--%>
                                <td>
                                    <%--<%=request.getAttribute("tournamentName")%>--%>
                                    <%=tournamentList.get(i).getTournamentName()%>

                                </td>

                                <%--StartDate--%>
                                <td>
                                    <%=tournamentList.get(i).getStartDate()%>
                                </td>

                                <%--EndDate--%>
                                <td>
                                    <%=tournamentList.get(i).getEndDate()%>
                                </td>

                                <%--Location--%>
                                <td>
                                    <%=tournamentList.get(i).getLocation()%>
                                </td>

                                <%--TournamentDescription--%>
                                <td>
                                    <%=tournamentList.get(i).getTournamentDescription()%>
                                </td>

                                <%--TournamentFormatId--%>
                                <%
                                    int tournamentFormatId = tournamentList.get(i).getTournamentFormatId();
                                    String formatStr = TournamentFormat.parseTournamentFormatIdToString(tournamentFormatId);
                                %>
                                <td>
                                    <%=formatStr%>
                                </td>

                                <%--Tournament creator--%>
                                <% int id = tournamentList.get(i).getManagerId();
                                %>
                                <td>
                                    <%=id%>
                                </td>
                            </tr>
                            <br>
                            <%}%>
                        </table>






                    </div>
                </div>
            </div>

            <!-- Footer -->
            <%--<footer id="footer">--%>
            <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
            <%--<p>Copyright &copy; Artur Babayan 2016</p>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</footer>--%>

        </div>
    </div>
</div>

<!-- jQuery -->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>

<%--Custom JS--%>
<script src="<c:url value="/resources/js/custom.js" />"></script>

</body>
</html>