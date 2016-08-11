<%@ page import="java.util.List" %>
<%@ page import="com.workfront.intern.cb.common.Tournament" %>
<%@ page import="com.workfront.intern.cb.service.TournamentServiceImpl" %>
<%@ page import="com.workfront.intern.cb.service.ManagerServiceImpl" %>
<%@ page import="com.workfront.intern.cb.common.Manager" %>
<%@ page import="com.workfront.intern.cb.common.TournamentFormat" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tournaments</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-home.css" rel="stylesheet">
    <link href="css/creative.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

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
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <!--<li><a class="page-scroll" href="#about">About</a></li>-->
                <li><a class="page-scroll" href="tournament.jsp">Tournaments</a></li>
                <li><a class="page-scroll" href="match.jsp">Matches</a></li>
                <li><a class="page-scroll" href="media.jsp">Media</a></li>
                <li><a class="page-scroll" href="contact.jsp">Contact Us</a></li>
                <li><a href="login.jsp" name="signUpMenuBtn">Sign Up</a></li>
                <li><a href="login.jsp" name="logInMenuBtn">Log In</a></li>
            </ul>
        </div>
    </div>
</nav>


<div class="row">

    <!-- Blog Entries Column -->
    <div class="col-md-8">
        <h1 class="page-header">T o u r n a m e n t s</h1>

        <div class="container">

            <%
                List<Tournament> tournamentList = new TournamentServiceImpl().getTournamentList();
                int sizeList = tournamentList.size();
            %>

            <table class="tournamentTable">
                <tr>
                    <th>No</th>
                    <th>TournamentId</th>
                    <th>TournamentName</th>
                    <th>StartDate</th>
                    <th>EndDate</th>
                    <th>Location</th>
                    <th>TournamentFormat</th>
                    <th>TournamentDescription</th>
                    <th>Tournament creator</th>
                </tr>
                <%
                    for (int i = 0; i < sizeList; i++) {
                %>
                <tr>
                    <%--No--%>
                    <td><%=i%>
                    </td>

                    <%--TournamentId--%>
                    <td><%=tournamentList.get(i).getTournamentId()%>
                    </td>

                    <%--TournamentName--%>
                    <td><%=tournamentList.get(i).getTournamentName()%>
                    </td>

                    <%--StartDate--%>
                    <td><%=tournamentList.get(i).getStartDate()%>
                    </td>

                    <%--EndDate--%>
                    <td><%=tournamentList.get(i).getEndDate()%>
                    </td>

                    <%--Location--%>
                    <td><%=tournamentList.get(i).getLocation()%>
                    </td>

                    <%--TournamentFormat--%>
                    <%
                        int tournamentFormatId = tournamentList.get(i).getTournamentFormatId();
                        String formatStr = TournamentFormat.parseTournamentFormatIdToString(tournamentFormatId);
                    %>
                    <td><%=formatStr%>
                    </td>

                    <%--TournamentDescription--%>
                    <td><%=tournamentList.get(i).getTournamentDescription()%>
                    </td>

                    <%--Tournament creator--%>
                    <% int managerId = tournamentList.get(i).getManagerId();
                        Manager manager = new ManagerServiceImpl().getManagerById(managerId);
                        String managerName = manager.getLogin();
                    %>
                    <td>
                        <%=managerName%>
                    </td>
                </tr><br>
                <%}%>

            </table>

        </div>






        <%--<!-- Second Blog Post -->--%>
        <%--<h2>--%>
        <%--<a href="#">Blog Post Title</a>--%>
        <%--</h2>--%>
        <%--<p class="lead">--%>
        <%--by <a href="index.php">Start Bootstrap</a>--%>
        <%--</p>--%>
        <%--<p><span class="glyphicon glyphicon-time"></span> Posted on August 28, 2013 at 10:45 PM</p>--%>
        <%--<hr>--%>
        <%--<img class="img-responsive" src="http://placehold.it/900x300" alt="">--%>
        <%--<hr>--%>
        <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quibusdam, quasi, fugiat, asperiores harum--%>
        <%--voluptatum tenetur a possimus nesciunt quod accusamus saepe tempora ipsam distinctio minima dolorum--%>
        <%--perferendis labore impedit voluptates!</p>--%>
        <%--<a class="btn btn-primary" href="#">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>--%>

        <%--<hr>--%>

        <%--<!-- Third Blog Post -->--%>
        <%--<h2>--%>
        <%--<a href="#">Blog Post Title</a>--%>
        <%--</h2>--%>
        <%--<p class="lead">--%>
        <%--by <a href="index.php">Start Bootstrap</a>--%>
        <%--</p>--%>
        <%--<p><span class="glyphicon glyphicon-time"></span> Posted on August 28, 2013 at 10:45 PM</p>--%>
        <%--<hr>--%>
        <%--<img class="img-responsive" src="http://placehold.it/900x300" alt="">--%>
        <%--<hr>--%>
        <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate, voluptates, voluptas dolore ipsam--%>
        <%--cumque quam veniam accusantium laudantium adipisci architecto itaque dicta aperiam maiores provident id--%>
        <%--incidunt autem. Magni, ratione.</p>--%>
        <%--<a class="btn btn-primary" href="#">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>--%>

        <%--<hr>--%>

        <%--<!-- Pager -->--%>
        <%--<ul class="pager">--%>
        <%--<li class="previous">--%>
        <%--<a href="#">&larr; Older</a>--%>
        <%--</li>--%>
        <%--<li class="next">--%>
        <%--<a href="#">Newer &rarr;</a>--%>
        <%--</li>--%>
        <%--</ul>--%>

        <%--</div>--%>

        <%--<!-- Blog Sidebar Widgets Column -->--%>
        <%--<div class="col-md-4">--%>

        <%--<!-- Blog Search Well -->--%>
        <%--<div class="well">--%>
        <%--<h4>Blog Search</h4>--%>
        <%--<div class="input-group">--%>
        <%--<input type="text" class="form-control">--%>
        <%--<span class="input-group-btn">--%>
        <%--<button class="btn btn-default" type="button">--%>
        <%--<span class="glyphicon glyphicon-search"></span>--%>
        <%--</button>--%>
        <%--</span>--%>
        <%--</div>--%>
        <%--<!-- /.input-group -->--%>
        <%--</div>--%>

        <%--<!-- Blog Categories Well -->--%>
        <%--<div class="well">--%>
        <%--<h4>Blog Categories</h4>--%>
        <%--<div class="row">--%>
        <%--<div class="col-lg-6">--%>
        <%--<ul class="list-unstyled">--%>
        <%--<li><a href="#">Category Name</a>--%>
        <%--</li>--%>
        <%--<li><a href="#">Category Name</a>--%>
        <%--</li>--%>
        <%--<li><a href="#">Category Name</a>--%>
        <%--</li>--%>
        <%--<li><a href="#">Category Name</a>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<!-- /.col-lg-6 -->--%>
        <%--<div class="col-lg-6">--%>
        <%--<ul class="list-unstyled">--%>
        <%--<li><a href="#">Category Name</a>--%>
        <%--</li>--%>
        <%--<li><a href="#">Category Name</a>--%>
        <%--</li>--%>
        <%--<li><a href="#">Category Name</a>--%>
        <%--</li>--%>
        <%--<li><a href="#">Category Name</a>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<!-- /.col-lg-6 -->--%>
        <%--</div>--%>
        <%--<!-- /.row -->--%>
        <%--</div>--%>

        <%--<!-- Side Widget Well -->--%>
        <%--<div class="well">--%>
        <%--<h4>Side Widget Well</h4>--%>
        <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus--%>
        <%--laudantium odit aliquam repellat tempore quos aspernatur vero.</p>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Artur Babayan 2016</p>
                </div>
            </div>
        </footer>

    </div>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>