package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.GroupService;
import com.workfront.intern.cb.service.ParticipantService;
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
public class ParticipantController {
    private static Logger LOG = Logger.getLogger(ParticipantController.class);

    @Autowired
    ParticipantService participantService;

    @Autowired
    TournamentService tournamentService;

    @Autowired
    GroupService groupService;


    @RequestMapping(value = {"/participant-page"})
    public String toParticipantPage(Model model,
                                    HttpServletRequest request) {
        return Params.PAGE_PARTICIPANTS;
    }


    @RequestMapping(value = {"/participant-mirror-page"})
    public String toParticipantMirrorPage(Model model,
                                    HttpServletRequest request) {

        return Params.PAGE_PARTICIPANTS_MIRROR;
    }

    // region <ADD MEMBER>

    @RequestMapping(value = {"/add-members-page"})
    public String toAddMembersPage(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        int managerId = manager.getId();

        List<Tournament> tournamentListParticipantFrom = tournamentService.getTournamentListByManager(managerId);
        request.setAttribute("tournamentListParticipantFrom", tournamentListParticipantFrom);

        return Params.PAGE_ADD_MEMBER;
    }

    @RequestMapping(value = {"/addMember-form"})
    public String addMember(Model model,
                            @RequestParam("nameMember") String nameMember,
                            @RequestParam("surNameMember") String surNameMember,
                            @RequestParam("positionMember") String positionMember,
                            @RequestParam("emailMember") String email,
                            @RequestParam("infoMember") String info,
                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        int tournamentId = (int) session.getAttribute("selectedTournamentId");

        Member member = new Member();
        member.setName(nameMember);
        member.setSurName(surNameMember);
        member.setPosition(positionMember);
        member.setEmail(email);
        member.setParticipantInfo(info);
        member.setTournamentId(tournamentId);

        participantService.addParticipant(member);

        return "participant/participant-mirror";
    }

    // endregion
}