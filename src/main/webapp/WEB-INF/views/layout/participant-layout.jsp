<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Participant-layout</title>
</head>
<body>

<%
    String slcTournamentIdStr = request.getParameter("selectedTournamentId");

    int slcTournamentId;
    if (slcTournamentIdStr != null) {
        slcTournamentId = Integer.parseInt(request.getParameter("selectedTournamentId"));
        session.setAttribute("selectedTournamentId", slcTournamentId);
    } else {
        slcTournamentId = (int) session.getAttribute("selectedTournamentId");
    }

    // Acquire selected tournament
    Tournament selectedTournament = BeanProvider.getTournamentService().getTournamentById(slcTournamentId);
    String tournamentName = selectedTournament.getTournamentName();
    boolean isStared = BeanProvider.getTournamentService().tournamentStarted(slcTournamentId);

    // Acquire tournament groups for all rounds
    Map<Integer, List<Group>> groupsMap = BeanProvider.getGroupService().getTournamentGroupsByRounds(slcTournamentId);

    // Member list by specific tournament
    List<Member> memberListByTournament = (List<Member>) BeanProvider.getParticipantService().
            getParticipantsByTournamentId(Member.class, slcTournamentId);
    int memberListSize = memberListByTournament.size();
    session.setAttribute("memberListByTournament", memberListByTournament);

    List<Team> teamListByTournament = (List<Team>) BeanProvider.getParticipantService().
            getParticipantsByTournamentId(Team.class, slcTournamentId);
    int teamListSize = teamListByTournament.size();
    session.setAttribute("teamListByTournament", teamListByTournament);


    // Hide/unhide member or team blocks
    String showTeamElement = null;
    String showMemberElement = null;
    String showElement = null;

    if (teamListSize <= 0 && memberListSize <= 0) {
        showTeamElement = "show-element";
        showMemberElement = "show-element";
        showElement = "hidden-element";
    } else if (teamListSize > 0) {
        showTeamElement = "show-element";
        showMemberElement = "hidden-element";
        showElement = "show-element";
    } else if (memberListSize > 0) {
        showTeamElement = "hidden-element";
        showMemberElement = "show-element";
        showElement = "show-element";
    }
%>

</body>
</html>
