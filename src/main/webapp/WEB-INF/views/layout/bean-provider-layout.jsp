<%@ page import="com.workfront.intern.cb.service.*" %>
<%@ page import="com.workfront.intern.cb.web.beans.BeanProvider" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bean-provider-layout</title>
</head>
<body>

<%
    ManagerService managerService = BeanProvider.getManagerService();
    TournamentService tournamentService = BeanProvider.getTournamentService();
    GroupService groupService = BeanProvider.getGroupService();
    MatchService matchService = BeanProvider.getMatchService();
    MediaService  mediaService = BeanProvider.getMediaService();
    ParticipantService participantService = BeanProvider.getParticipantService();
%>

</body>
</html>
