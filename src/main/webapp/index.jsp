<%@ page import="com.workfront.intern.cb.web.util.Params" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Competition Board</title>

    <%--Custom JS--%>
    <script src="js/custom.js"></script>
    <script src="js/jquery-3.1.0.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
          rel='stylesheet' type='text/css'>

    <!-- Plugin CSS -->
    <link href="vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="css/creative.min.css" rel="stylesheet">
    <link href="css/creative.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/normalize.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<%--Gets specific atributes from http session--%>
<%
    String userNameSignInStr = (String) session.getAttribute("userNameSignIn");
    if (userNameSignInStr == null) {
        userNameSignInStr = "";
    }

    String loginUserStr = (String) session.getAttribute("usernameLogin");
    if (loginUserStr == null) {
        loginUserStr = "";
    }
%>

<body id="page-top" onload="hiddenBtn()">
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand page-scroll" href="#page-top">Home</a>
            <a class="navbar-brand page-scroll"><%= userNameSignInStr%> <%= loginUserStr%>
            </a>
        </div>

        <%--ToDo--%>
        <%--Hide/unhide--%>
        <%
            String addTournamentMenuItem = null;
            String signUpMenuItem = null;
            String logInMenuBtnItem = null;
            String logOutMenuBtnItem = null;
            if ((loginUserStr.equals("")) && (userNameSignInStr.equals(""))) {
                addTournamentMenuItem = "";
                signUpMenuItem = "Sign Up";
                logInMenuBtnItem = "Log In";
                logOutMenuBtnItem = "";
            } else if ((userNameSignInStr != null) || (loginUserStr != null)) {
                addTournamentMenuItem = "Add Tournament";
                signUpMenuItem = "";
                logInMenuBtnItem = "";
                logOutMenuBtnItem = "Log Out";
            }
        %>


        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <!--<li><a class="page-scroll" href="#about">About</a></li>-->
                <li><a class="page-scroll" href="add-tournament.jsp"><%=addTournamentMenuItem%></a></li>
                <li><a class="page-scroll" href="all-tournaments.jsp">Tournaments</a></li>
                <li><a class="page-scroll" href="match.jsp">Matches</a></li>
                <li><a class="page-scroll" href="#portfolio">Gallery</a></li>
                <li><a class="page-scroll" href="#contact>">Contact Us</a></li>
                <li><a href="<%=Params.PAGE_SIGN_IN%>"><%=signUpMenuItem%></a></li>
                <li><a href="<%=Params.PAGE_LOG_IN%>"><%=logInMenuBtnItem%></a></li>
                <li><a href="logout"><%=logOutMenuBtnItem%> </a></li>

            </ul>
        </div>
    </div>
</nav>

<header>
    <div class="header-content">

        <%--tournament-search--%>
        <form action="search" method="get" class="container">
            <div class="container">
                <div class="row">
                    <div id="custom-search-input">
                        <div class="input-group col-md-12">
                            <input type="text" class="  search-query form-control" name="searchStr"
                                   placeholder="Search tournaments" required/>
                            <span class="input-group-btn">
                    <button class="btn btn-danger" type="submit" onclick="submitForm()">
                        <span class=" glyphicon glyphicon-search"></span>
                    </button>
                </span>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <br>

        <%--Read attribute from request--%>
        <%
            String searchResultMsg = (String) request.getAttribute("noSearchResultMsg");
            if (searchResultMsg != null)
                out.println("<font color=red size=4px>" + searchResultMsg + "</font>");
        %>

        <%--TournamentCreatButton--%>
        <%--<%--%>
        <%--String idValue = "";--%>
        <%--if ((loginUserStr.equals("") && (userNameSignInStr.equals("")))) {--%>
        <%--idValue = "hide";--%>
        <%--} else if ((loginUserStr != null) || (loginUserStr != null)) {--%>
        <%--idValue = "unhide";--%>
        <%--}--%>
        <%--%>--%>
        <%--<form action="tournament.jsp" method="get" class="container">--%>
        <%--<div class="textarea">--%>
        <%--<button type="submit" class="buttonCustom" id=<%=idValue%>>Create a tournament</button>--%>
        <%--</div>--%>

        <%--</form>--%>


    </div>
</header>

<section class="no-padding" id="portfolio">
    <div class="container-fluid">
        <div class="row no-gutter popup-gallery">
            <div class="col-lg-4 col-sm-6">
                <a href="<%=Params.PAGE_MEDIA%>" class="portfolio-box">
                    <img src="img/portfolio/thumbnails/1.jpg" class="img-responsive" alt="">

                    <div class="portfolio-box-caption">
                        <div class="portfolio-box-caption-content">
                            <div class="project-category text-faded">
                                Category
                            </div>
                            <div class="project-name">
                                Fifa 2020
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-sm-6">
                <a href="<%=Params.PAGE_MEDIA%>" class="portfolio-box">
                    <img src="img/portfolio/thumbnails/2.jpg" class="img-responsive" alt="">

                    <div class="portfolio-box-caption">
                        <div class="portfolio-box-caption-content">
                            <div class="project-category text-faded">
                                Gorci texi txeqov
                            </div>
                            <div class="project-name">
                                Gallery
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-sm-6">
                <a href="i<%=Params.PAGE_MEDIA%>" class="portfolio-box">
                    <img src="img/portfolio/thumbnails/3.jpg" class="img-responsive" alt="">

                    <div class="portfolio-box-caption">
                        <div class="portfolio-box-caption-content">
                            <div class="project-category text-faded">
                                Category
                            </div>
                            <div class="project-name">
                                Gallery
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-sm-6">
                <a href="<%=Params.PAGE_MEDIA%>" class="portfolio-box">
                    <img src="img/portfolio/thumbnails/4.jpg" class="img-responsive" alt="">

                    <div class="portfolio-box-caption">
                        <div class="portfolio-box-caption-content">
                            <div class="project-category text-faded">
                                Category
                            </div>
                            <div class="project-name">
                                Gallery
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-sm-6">
                <a href="<%=Params.PAGE_MEDIA%>" class="portfolio-box">
                    <img src="img/portfolio/thumbnails/5.jpg" class="img-responsive" alt="">

                    <div class="portfolio-box-caption">
                        <div class="portfolio-box-caption-content">
                            <div class="project-category text-faded">
                                Category
                            </div>
                            <div class="project-name">
                                Gallery
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-4 col-sm-6">
                <a href="<%=Params.PAGE_MEDIA%>" class="portfolio-box">
                    <img src="img/portfolio/thumbnails/6.jpg" class="img-responsive" alt="">

                    <div class="portfolio-box-caption">
                        <div class="portfolio-box-caption-content">
                            <div class="project-category text-faded">
                                Category
                            </div>
                            <div class="project-name">
                                Liqy sirun axjik
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</section>

<section id="contact">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 text-center">
                <h2 class="section-heading">Let's Get In Touch!</h2>
                <hr class="primary">
                <p>Write your review. We love hearing from our customers<br>
                    and we had love to hear from you too.
                </p>
            </div>
            <div class="col-lg-4 col-lg-offset-2 text-center">
                <i class="fa fa-phone fa-3x sr-contact"></i>

                <p>123-456-6789</p>
            </div>
            <div class="col-lg-4 text-center">
                <i class="fa fa-envelope-o fa-3x sr-contact"></i>

                <p><a href="mailto:arturbabayan@workfront.com">arturbabayan@workfront.com</a></p>
            </div>
        </div>
    </div>
</section>

<!-- jQuery -->
<script src="vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<%--<script src="vendor/bootstrap/js/bootstrap.min.js"></script>--%>

<!-- Plugin JavaScript -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="vendor/scrollreveal/scrollreveal.min.js"></script>
<%--<script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>--%>

<!-- Theme JavaScript -->
<script src="js/creative.min.js"></script>

</body>

<script></script>

</html>
