package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.GroupService;
import com.workfront.intern.cb.service.ManagerService;
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

@Controller
public class GroupController {
    private static Logger LOG = Logger.getLogger(GroupController.class);

    @Autowired
    ManagerService managerService;

    @Autowired
    TournamentService tournamentService;

    @Autowired
    GroupService groupService;

    @RequestMapping(value = {"/all-group-page"})
    public String toAllGroupPage(Model model,
                                 HttpServletRequest request) {

        List<Group> allGroups = groupService.getAllGroups();
        request.setAttribute("allGroups", allGroups);
        request.setAttribute("tournamentService", tournamentService);

        return Params.PAGE_ALL_GROUPS;
    }

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

        request.setAttribute("tournamentService", tournamentService);
        request.setAttribute("groupsByManager", groupsByManager);

        return Params.PAGE_GROUPS;
    }

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
                           @RequestParam("tournamentNameId") String tournamentNameId) {

        String notSelected = "notSelected";

        if (!tournamentNameId.equals(notSelected)) {
            Tournament tournament = tournamentService.getTournamentById(Integer.parseInt(tournamentNameId));

            Group group = new Group();
            group.setGroupName(nameGroup);
            group.setTournamentId(Integer.parseInt(tournamentNameId));

            groupService.addGroup(group);
        } else {

            return "redirect:add-group-page";
        }

        return "redirect:group-page";
    }

    // region <DELETE Group>

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public String deleteTournament(Model model,
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