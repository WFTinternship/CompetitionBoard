package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Team;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.GroupService;
import com.workfront.intern.cb.service.ParticipantService;
import com.workfront.intern.cb.service.TournamentService;
import com.workfront.intern.cb.web.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchingController {

    @Autowired
    TournamentService tournamentService;

    @Autowired
    GroupService groupService;

    @Autowired
    ParticipantService participantService;

    @RequestMapping(value = {"/search-result-page"})
    public String toSearchResultPage(Model model) {
        return Params.PAGE_SEARCH_RESULT;
    }

    @RequestMapping(value = {"/searchByName-form"}, method = RequestMethod.GET)
    public String searchTournamentsByName(Model model,
                                          @RequestParam("searchStr") String searchStr,
                                          HttpServletRequest request) {

        // It contains the values of all list sizes
        List<Integer> allListSize = new ArrayList<>();

        // Result by tournament list
        List<Tournament> tournamentList = tournamentService.getTournamentListByName(searchStr);
        int tournamentListSize = tournamentList.size();
        allListSize.add(tournamentListSize);

        // Result by group list
        List<Group> groupList = groupService.getGroupListByName(searchStr);
        int groupListSize = groupList.size();
        allListSize.add(groupListSize);

        // Result by member list
        List<Member> memberList = (List<Member>) participantService.getParticipantListByName(Member.class, searchStr);
        int memberListSize = memberList.size();
        allListSize.add(memberListSize);

        // Result by member list
        List<Team> teamListList = (List<Team>) participantService.getParticipantListByName(Team.class, searchStr);
        int teamListListSize = teamListList.size();
        allListSize.add(teamListListSize);

        int sumSize = 0;
        for (Integer anAllListSize : allListSize) {
            sumSize += anAllListSize;
        }

        if (sumSize != 0) {
            request.setAttribute("searchResultTournament", tournamentList);
            request.setAttribute("searchResultGroup", groupList);
            request.setAttribute("searchResultMember", memberList);
            request.setAttribute("searchResultTeam", teamListList);
        } else {
            model.addAttribute("noSearchResultMsg", "No matches with provided search criteria");

            return Params.PAGE_INDEX;
        }

        return Params.PAGE_SEARCH_RESULT;
    }
}