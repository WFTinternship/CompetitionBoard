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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
                           @RequestParam("tournamentNameId") int tournamentNameId,
                           HttpServletRequest request) {

        Group group = new Group();
        group.setGroupName(nameGroup);
        group.setTournamentId(tournamentNameId);

        groupService.addGroup(group);

        HttpSession session = request.getSession();
        session.setAttribute("tournamentNameId", tournamentNameId);

        return "redirect:group/group";
    }
}
