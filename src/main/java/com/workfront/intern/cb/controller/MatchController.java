package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.*;
import com.workfront.intern.cb.service.*;
import com.workfront.intern.cb.web.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MatchController {

    @Autowired
    ManagerService managerService;

    @Autowired
    TournamentService tournamentService;

    @Autowired
    GroupService groupService;

    @Autowired
    MatchService matchService;

    @Autowired
    ParticipantService participantService;

    @RequestMapping(value = {"/match-page"})
    public String toMatchPage(Model model,
                              HttpServletRequest request) {

        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        List<Match> matchList = matchService.getMatchListByManager(manager.getId());
        session.setAttribute("matchList", matchList);

        return Params.PAGE_MATCH;
    }

    @RequestMapping(value = {"/add-match"})
    public String addMatch(Model model,
                           @RequestParam("groupSelectId") int groupSelectId,
                           @RequestParam("participant1MatchId") int participant1MatchId,
                           @RequestParam("participant2MatchId") int participant2MatchId,
                           HttpServletRequest request) {

        HttpSession session = request.getSession();

        Match match = new Match();
        match.setGroupId(groupSelectId);
        match.setParticipantOneId(participant1MatchId);
        match.setParticipantTwoId(participant2MatchId);

        matchService.addMatch(match);
        session.setAttribute("groupSelectMatchId", groupSelectId);

        return "redirect:match-page";
    }

    @RequestMapping(value = {"/match-selector-page"})
    public String toMatchSelectorPage(Model model, HttpServletRequest request) {

        // Selected groups list of manager tournaments
        List<Group> groupsMatchByManager = new ArrayList<>();

        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");

        // All groups list
        List<Group> allGroups = groupService.getAllGroups();
        int allGroupsSize = allGroups.size();

        // Tournaments list of manager
        List<Tournament> tournamentListByManager = tournamentService.getTournamentListByManager(manager.getId());
        int tournamentListSize = tournamentListByManager.size();
        for (int i = 0; i < tournamentListSize; i++) {
            for (int j = 0; j < allGroupsSize; j++) {
                if ((tournamentListByManager.get(i).getTournamentId()) == allGroups.get(j).getTournamentId()) {
                    groupsMatchByManager.add(allGroups.get(j));
                }
            }
        }
        int tournamentId = (int) session.getAttribute("selectedTournamentId");
        List<Team> teamListMatch = (List<Team>) participantService.getParticipantsByTournamentId(Team.class, tournamentId);
        List<Member> memberListMatch = (List<Member>) participantService.getParticipantsByTournamentId(Member.class, tournamentId);

        session.setAttribute("groupsMatchByManager", groupsMatchByManager);
        session.setAttribute("teamListMatch", teamListMatch);
        session.setAttribute("memberListMatch", memberListMatch);


        return Params.PAGE_MATCH_SELECTOR;
    }

    @RequestMapping(value = {"/all-match-page"})
    public String allMatchPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        return Params.PAGE_ALL_MATCH;
    }


    @RequestMapping(value = "/updateMatch", method = RequestMethod.GET)
    public String updateMatch(Model model,
                                   @RequestParam("matchID") int matchID,
                                   @RequestParam("participantOneScore") int participantOneScore,
                                   @RequestParam("participantTwoScore") int participantTwoScore,
                                   @RequestParam("matchScore") int matchScore) {

        Match match = matchService.getMatchById(matchID);
        match.setScoreParticipantOne(participantOneScore);
        match.setScoreParticipantTwo(participantTwoScore);
        match.setMatchScore(matchScore);

        matchService.updateMatch(matchID, match);

        return "redirect:match-page";
    }


    @RequestMapping(value = "/deleteMatch", method = RequestMethod.GET)
    public String deleteMatch(Model model,
                              @RequestParam("matchNameId") int matchID) {

       matchService.deleteMatch(matchID);

        return "redirect:match-page";
    }
}