package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.*;
import com.workfront.intern.cb.service.GroupService;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.ParticipantService;
import com.workfront.intern.cb.service.TournamentService;
import com.workfront.intern.cb.web.util.Params;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.workfront.intern.cb.web.util.Params.PAGE_ASSIGN_TO_GROUP;

@Controller
public class GroupController {
    private static Logger LOG = Logger.getLogger(GroupController.class);

    @Autowired
    ManagerService managerService;

    @Autowired
    TournamentService tournamentService;

    @Autowired
    GroupService groupService;

    @Autowired
    ParticipantService participantService;

    // region <All Group>

    @RequestMapping(value = {"/all-group-page"})
    public String toAllGroupPage(Model model, HttpServletRequest request) {

        List<Group> allGroups = groupService.getAllGroups();
        request.setAttribute("allGroups", allGroups);
        request.setAttribute("tournamentService", tournamentService);

        return Params.PAGE_ALL_GROUPS;
    }

    // endregion

    // region <Group>

    @RequestMapping(value = {"/group-page"})
    public String toGroupPage(Model model, HttpServletRequest request) {

        // Selected groups list of manager tournaments
        List<Group> groupsByManager = new ArrayList<>();

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
                    groupsByManager.add(allGroups.get(j));
                }
            }
        }

        request.setAttribute("groupsByManager", groupsByManager);

        return Params.PAGE_GROUPS;
    }

    // endregion

    // region <Group Add>

    @RequestMapping(value = {"/add-group-page"})
    public String toAddPage(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        int managerId = manager.getId();

        List<Tournament> tournamentList = tournamentService.getTournamentListByManager(managerId);
        session.setAttribute("tournamentList", tournamentList);

        return Params.PAGE_ADD_GROUP;
    }

    @RequestMapping(value = {"/addGroup-form"})
    public String addGroup(Model model,
                           @RequestParam("nameGroup") String nameGroup,
                           @RequestParam("roundSelectId") String roundSelectIdStr,
                           @RequestParam("tournamentNameId") String tournamentNameIdStr) {

        String notSelected = "notSelected";
        if (!tournamentNameIdStr.equals(notSelected)) {
            Group group = new Group();
            group.setGroupName(nameGroup);
            group.setRound(Integer.parseInt(roundSelectIdStr));
            group.setTournamentId(Integer.parseInt(tournamentNameIdStr));

            groupService.addGroup(group);
        } else {
            return "redirect:add-group-page";
        }

        return "redirect:group-page";
    }

    // endregion

    // region <Group - Participant>

    @RequestMapping(value = {"/group-participant-page"})
    public String toGroupParticipantPage(Model model,
                                         @RequestParam("groupNameId") int groupNameId,
                                         HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<Team> teamList = (List<Team>) participantService.getParticipantListByGroupId(Team.class, groupNameId);
        session.setAttribute("groupParticipantTeamList", teamList);

        List<Member> memberList = (List<Member>) participantService.getParticipantListByGroupId(Member.class, groupNameId);
        session.setAttribute("groupParticipantMemberList", memberList);

        return Params.PAGE_GROUP_PARTICIPANT;
    }

    // endregion

    // region <Group - Tournament>

    @RequestMapping(value = {"/group-tournament-page"})
    public String toGroupTournamentPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        // Gets groups by tournament id
        int selectedTournamentId = (int) session.getAttribute("selectedTournamentId");
        List<Group> groupsByCurrentTournament = groupService.getTournamentGroups(selectedTournamentId);
        session.setAttribute("groupsByCurrentTournament", groupsByCurrentTournament);

        return Params.PAGE_GROUP_TOURNAMENT;
    }

    // endregion

    // region <UPDATE Group>

    @RequestMapping(value = "/updateGroup", method = RequestMethod.GET)
    public String updateGroup(Model model,
                              @RequestParam("groupName") String groupName,
                              HttpServletRequest request) {

        int groupId = Integer.parseInt(request.getParameter("groupIDSelected"));

        Group group = groupService.getGroupById(groupId);
        group.setGroupName(groupName);
        groupService.updateGroup(groupId, group);

        return "redirect:group-page";
    }

    // endregion

    // region <Assign to Group>

    @RequestMapping(value = "/assign-participant-to-group-page", method = RequestMethod.GET)
    public String assignParticipantToGroupPage(Model model,
                                               @RequestParam("assignToGroupBtn") String assignToGroupBtn,
                                               HttpServletRequest request) {

        HttpSession session = request.getSession();
        int assignToGroupBtnValue = Integer.parseInt(assignToGroupBtn);

        // Gets Add To Group Button value, to check who pressed button member or team
        if (assignToGroupBtnValue == 1) {
            session.setAttribute("assignToGroupBtnValue", assignToGroupBtnValue);
        } else if (assignToGroupBtnValue == 5) {
            session.setAttribute("assignToGroupBtnValue", assignToGroupBtnValue);
        }


        // Gets groups by tournament id
        int selectedTournamentId = (int) session.getAttribute("selectedTournamentId");
        List<Group> groupsByCurrentTournament = groupService.getTournamentGroups(selectedTournamentId);
        session.setAttribute("groupsByCurrentTournament", groupsByCurrentTournament);

        return PAGE_ASSIGN_TO_GROUP;
    }

    @RequestMapping(value = "/assignToGroup-form", method = RequestMethod.GET)
    public String assignParticipantToGroup(Model model,
                                           @RequestParam("groupId") String groupIdStr,
                                           @RequestParam("teamId") String teamIdStr,
                                           @RequestParam("memberId") String memberIdStr,
                                           HttpServletRequest request) {

        HttpSession session = request.getSession();
        int tournamentId = (int) session.getAttribute("selectedTournamentId");

        Participant participant = null;
        String notSelected = "notSelected";
        System.out.println(groupIdStr);
        if (groupIdStr.equals(notSelected) && teamIdStr.equals(notSelected)) {
            return PAGE_ASSIGN_TO_GROUP;
        }
        if (groupIdStr.equals(notSelected) && memberIdStr.equals(notSelected)) {
            return PAGE_ASSIGN_TO_GROUP;
        }

        if (!memberIdStr.equals(notSelected)) {
            participant = new Member();
            participant.setId(Integer.parseInt(memberIdStr));
        } else if (!teamIdStr.equals(notSelected)) {
            participant = new Team();
            participant.setId(Integer.parseInt(teamIdStr));
        }

        assert participant != null;
        participant.setTournamentId(tournamentId);

        assert groupIdStr != null;
        int groupId = Integer.parseInt(groupIdStr);
        groupService.assignParticipant(tournamentId, groupId, participant);
        session.setAttribute("assignedGroupId", groupId);

        return Params.PAGE_PARTICIPANTS;
    }

    // endregion

    // region <DELETE Group>

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public String deleteGroup(Model model,
                              @RequestParam("groupId") String groupId) {
        try {
            groupService.deleteGroup(Integer.parseInt(groupId));
        } catch (Exception ex) {
            return "redirect:group-page";
        }

        return "redirect:group-page";
    }

    // endregion
}