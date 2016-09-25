<%@ page import="com.workfront.intern.cb.common.Manager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--&lt;%&ndash;Gets specific atributes from http session&ndash;%&gt;--%>
<%
    String userName = "";
    String welcomeStr = "";
    String hrefToSpecificTournamentPage = "all-tournaments-page";
    String hrefToSpecificMatchPage = "all-match-page";
    String avatar = "";
    String addTournamentMenuItem = null;
    String classStr = null;
    String allTournaments = "All Tournaments";
    String allMatches = "All Matches";

    Manager manager = (Manager) session.getAttribute("manager");

    if (manager != null) {
        avatar = "resources/img/user_avatar/" + manager.getAvatar();
        userName = manager.getLogin();
        welcomeStr = "Hi, ";
        hrefToSpecificTournamentPage = "tournament-page";
        hrefToSpecificMatchPage = "match-page";
        addTournamentMenuItem = "Add Tournament";
        allTournaments = "Tournaments";
        allMatches = "Matches";
        classStr = "visible-element";
    }

    if (userName.equals("")) {
        addTournamentMenuItem = "";
        classStr = "hidden-element";
    }
%>

</body>
</html>
