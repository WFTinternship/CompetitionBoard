<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<html>
<head>
    <title>tournament.jsp</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script src="js/ie/html5shiv.js"></script>
    <link rel="stylesheet" href="css/main.css"/>
</head>

<body>
<div id="page-wrapper">

    <!-- Header -->
    <header id="header">
        <nav id="nav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li>
                    <a href="#" class="icon fa-angle-down">Menu</a>
                    <ul>
                        <li><a href="tournament.jsp">Tournaments</a></li>
                        <li><a href="match.jsp">Matches</a></li>
                        <li><a href="media.jsp">Media</a></li>
                        <li><a href="contact.jsp">Contact Us</a></li>
                        <li>
                            <a href="#">Submenu</a>
                            <ul>
                                <li><a href="#">Option One</a></li>
                                <li><a href="#">Option Two</a></li>
                                <li><a href="#">Option Three</a></li>
                                <li><a href="#">Option Four</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><a href="login.jsp" name="login" class="button">Log In</a></li>
                <li><a href="login.jsp" name="signup" class="button">Sign Up</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main -->
    <section id="main" class="container 75%">
        <header>
            <h2>Tournament</h2>
        </header>
        <div class="box">
            <form method="post" action="#">
                <%--Todo some logics--%>
            </form>
        </div>
    </section>
</div>


<!-- Scripts -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.dropotron.min.js"></script>
<script src="js/jquery.scrollgress.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/main.js"></script>

<!--<script src="js/ie/respond.min.js"></script>-->
<![endif]-->

</body>
</html>