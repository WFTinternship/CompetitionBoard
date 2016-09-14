<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>add-tournament-layout</title>
</head>
<body>
<%
    String existsTournament = (String) request.getAttribute("existsTournament");
    if (existsTournament == null) {
        existsTournament = "";
    }
%>

<%--Return local time--%>
<%
    java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
    String today = df.format(new java.util.Date());
%>

</body>
</html>
