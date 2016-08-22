<%@ page import="com.workfront.intern.cb.web.util.Params" %>
<%@ page import="com.workfront.intern.cb.common.Manager" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Competition Board Home Page</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

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
    String userName = "";
    String welcomeStr = "";
//    String hrefToSpecificTournamentPage = Params.PAGE_ALL_AVALABLE_TOURNAMENTS;
    String hrefToSpecificTournamentPage = "/allTournamentsServlet";

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

<body id="page-top" onload="hiddenBtn()">
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand page-scroll" href="#page-top">Home</a>
            <a class="navbar-brand page-scroll"><%=welcomeStr + "" + userName%>
            </a>
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

<header>

    <%--Search tournament---%>
        <div class="header-content">
        <form action="search" method="get" class="container">
            <div class="container">
                <div class="row">
                    <div id="custom-search-input">
                        <div class="input-group col-md-12">
                            <input type="text" class="  search-query form-control" name="searchStr" placeholder="Search tournaments" required/>
                            <span class="input-group-btn">
                    <button class="btn btn-danger" type="submit" onclick="submitSearchNameInForm()">
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

<!-- Plugin JavaScript -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="vendor/scrollreveal/scrollreveal.min.js"></script>

<!-- Theme JavaScript -->
<script src="js/creative.min.js"></script>

</body>
</html>
