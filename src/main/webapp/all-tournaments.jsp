<%@ page import="com.workfront.intern.cb.common.Manager" %>
<%@ page import="com.workfront.intern.cb.web.util.Params" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>All Available Tournaments</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-home.css" rel="stylesheet">
    <link href="css/creative.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>


<%--Gets specific atributes from http session--%>
<%
    String userName = "";
    String welcomeStr = "";
    String hrefToSpecificTournamentPage = Params.PAGE_ALL_AVALABLE_TOURNAMENTS;

    String addTournamentMenuItem = null;
    String signUpMenuItem = null;
    String logInMenuBtnItem = null;
    String logOutMenuBtnItem = null;
    String hideStr = null;

    Manager sessionContext = (Manager) session.getAttribute("manager");
    if (sessionContext != null) {
        userName = sessionContext.getLogin();
        welcomeStr = "Hi, ";
        hrefToSpecificTournamentPage = Params.PAGE_TOURNAMENT;

        addTournamentMenuItem = "Add Tournament";
        signUpMenuItem = "";
        logInMenuBtnItem = "";
        logOutMenuBtnItem = "Log Out";
        hideStr = "unHide";
    }

    if (userName.equals("")) {
        addTournamentMenuItem = "";
        signUpMenuItem = "Sign Up";
        logInMenuBtnItem = "Log In";
        logOutMenuBtnItem = "";
        hideStr = "hide";
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
            <a class="navbar-brand page-scroll" href="index.jsp">Home</a>
            <a class="navbar-brand page-scroll"><%=welcomeStr + "" + userName%>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a class="page-scroll" href="add-tournament.jsp" id="<%=hideStr%>" onload="showMenuItem()"><%=addTournamentMenuItem%></a></li>
                <li><a class="page-scroll" href="<%=hrefToSpecificTournamentPage%>">Tournaments</a></li>
                <li type="hide"><a class="page-scroll" href="match.jsp">Matches</a></li>
                <li><a class="page-scroll" href="#portfolio">Gallery</a></li>
                <li><a class="page-scroll" href="#contact>">Contact Us</a></li>
                <li><a href="<%=Params.PAGE_SIGN_IN%>" id="<%=hideStr%>" onload="showMenuItemReverse()"><%=signUpMenuItem%> </a></li>
                <li><a href="<%=Params.PAGE_LOG_IN%>" id="<%=hideStr%>" onload="showMenuItemReverse()"><%=logInMenuBtnItem%> </a></li>
                <li><a href="logout" id="<%=hideStr%>" onload="showMenuItem()"><%=logOutMenuBtnItem%> </a></li>
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

                      <%--SOME TEXT--%>
                    </div>


                    <div class="col-sm-9">
                        <h2>All Available Tournaments</h2>
                        <hr>

                        <%--<div class="col-sm-9">--%>
                            <%--<h2>My tournaments</h2>--%>
                            <%--<hr>--%>
                            <%--<%--%>
                                <%--List<Tournament> allTournamentList = (List<Tournament>) request.getAttribute("allTournamentList");--%>
                                <%--int sizeList = allTournamentList.size();--%>
                            <%--%>--%>
                            <%--<table class="tournamentTable">--%>
                                <%--<tr class="searchTblResultTr">--%>
                                    <%--<th>No</th>--%>
                                    <%--<th>TournamentId</th>--%>
                                    <%--<th>TournamentName</th>--%>
                                    <%--<th>StartDate</th>--%>
                                    <%--<th>EndDate</th>--%>
                                    <%--<th>Location</th>--%>
                                    <%--<th>TournamentDescription</th>--%>
                                    <%--<th>TournamentFormat</th>--%>
                                    <%--<th>Tournament creator</th>--%>
                                <%--</tr>--%>
                                <%--<%--%>
                                    <%--for (int i = 0; i < sizeList; i++) {--%>
                                <%--%>--%>
                                <%--<tr>--%>
                                    <%--&lt;%&ndash;No&ndash;%&gt;--%>
                                    <%--<td><%=i%>--%>
                                    <%--</td>--%>

                                    <%--&lt;%&ndash;TournamentId&ndash;%&gt;--%>
                                    <%--<td><%=allTournamentList.get(i).getTournamentId()%>--%>
                                    <%--</td>--%>

                                    <%--&lt;%&ndash;TournamentName&ndash;%&gt;--%>
                                    <%--<td><%=allTournamentList.get(i).getTournamentName()%>--%>
                                    <%--</td>--%>

                                    <%--&lt;%&ndash;StartDate&ndash;%&gt;--%>
                                    <%--<td><%=allTournamentList.get(i).getStartDate()%>--%>
                                    <%--</td>--%>

                                    <%--&lt;%&ndash;EndDate&ndash;%&gt;--%>
                                    <%--<td><%=allTournamentList.get(i).getEndDate()%>--%>
                                    <%--</td>--%>

                                    <%--&lt;%&ndash;Location&ndash;%&gt;--%>
                                    <%--<td><%=allTournamentList.get(i).getLocation()%>--%>
                                    <%--</td>--%>

                                    <%--&lt;%&ndash;TournamentDescription&ndash;%&gt;--%>
                                    <%--<td><%=allTournamentList.get(i).getTournamentDescription()%>--%>
                                    <%--</td>--%>

                                    <%--&lt;%&ndash;TournamentFormatId&ndash;%&gt;--%>
                                    <%--<%--%>
                                        <%--int tournamentFormatId = allTournamentList.get(i).getTournamentFormatId();--%>
                                        <%--String formatStr = TournamentFormat.parseTournamentFormatIdToString(tournamentFormatId);--%>
                                    <%--%>--%>
                                    <%--<td><%=formatStr%>--%>
                                    <%--</td>--%>

                                    <%--&lt;%&ndash;Tournament creator&ndash;%&gt;--%>
                                    <%--<td>--%>
                                        <%--<%= allTournamentList.get(i).getManagerId()%>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                                <%--<br>--%>
                                <%--<%}%>--%>
                            <%--</table>--%>

                            <%--<!-- Footer -->--%>
                            <%--<footer>--%>
                                <%--<div class="row">--%>
                                    <%--<div class="col-lg-12">--%>
                                        <%--<p>Copyright &copy; Artur Babayan 2016</p>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</footer>--%>

                        <%--</div>--%>
                    <%--</div>--%>










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

</body>
</html>