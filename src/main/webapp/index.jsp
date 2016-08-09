<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/main.css"/>
    <script src="js/ie/html5shiv.js"></script>

    <title>Competition Board</title>
</head>
<body>

<!-- Header -->
<header id="header" class="alt">
    <nav id="nav">
        <ul>
            <li><% String userName = request.getParameter("userName");
                if (userName != null) {
            %> Hello, <%=userName%>
                <%}%>

            </li>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="#" class="icon fa-angle-down">Menu</a>
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
            <li><a href="login.jsp" name="signUpMenuBtn" class="button">Sign Up</a></li>
            <li><a href="login.jsp" name="logInMenuBtn" class="button">Log In</a></li>
        </ul>
    </nav>
</header>

<!-- Banner/Searching -->
<section id="banner">
    <form action="/" method="get" class="container">
        <div class="textarea">
            <input type="text" name="search" required autocomplete="off"/>
            <br>
            <button type="submit" class="button">SEARCH</button>
        </div>
    </form>
</section>


<!-- Footer -->
<footer id="footer">
    <ul class="copyright">
        <li>&copy; Artur Babayan, All rights reserved.</li>
    </ul>
</footer>

<!-- Scripts -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.dropotron.min.js"></script>
<script src="js/jquery.scrollgress.min.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/main.js"></script>
</body>
</html>