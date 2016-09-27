package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Team;
import com.workfront.intern.cb.service.GroupService;
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
    public String toParticipantPage(Model model, HttpServletRequest request) {
        return Params.PAGE_PARTICIPANTS;
    }

    // region <ADD MEMBER>

    @RequestMapping(value = {"/add-members-page"})
    public String toAddMembersPage(Model model, HttpServletRequest request) {
       return Params.PAGE_ADD_MEMBER;
    }

    @RequestMapping(value = {"/addMember-form"})
    public String addMember(Model model,
                            @RequestParam("nameMember") String nameMember,
                            @RequestParam("surNameMember") String surNameMember,
                            @RequestParam("positionMember") String positionMember,
                            @RequestParam("emailMember") String email,
                            @RequestParam("memberInfo") String memberInfo,
                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        int tournamentId = (int) session.getAttribute("selectedTournamentId");

        Member member = new Member();
        member.setName(nameMember);
        member.setSurName(surNameMember);
        member.setPosition(positionMember);
        member.setEmail(email);
        member.setParticipantInfo(memberInfo);
        member.setTournamentId(tournamentId);

        participantService.addParticipant(member);

        return "redirect:participant-page";
    }

    // endregion

    // region <UPDATE MEMBER>

    @RequestMapping(value = "/updateMember", method = RequestMethod.GET)
    public String updateMember(Model model, HttpServletRequest request) {

        int memberId = Integer.parseInt(request.getParameter("memberNameId"));
        String nameUpdate = request.getParameter("memberName");
        String sureNameUpdate = request.getParameter("memberSureName");
        String memberPositionUpdate = request.getParameter("memberPosition");
        String memberEmailUpdate = request.getParameter("memberEmail");
        String memberInfoUpdate = request.getParameter("memberInfo");

        Member member = (Member) participantService.getOne(Member.class, memberId);
        member.setName(nameUpdate);
        member.setSurName(sureNameUpdate);
        member.setPosition(memberPositionUpdate);
        member.setEmail(memberEmailUpdate);
        member.setParticipantInfo(memberInfoUpdate);

        participantService.update(memberId, member);

        return "redirect:participant-page";
    }

    // endregion

    // region <DELETE MEMBERS>

    @RequestMapping(value = "/deleteMember", method = RequestMethod.GET)
    public String deleteMember(Model model,
                                   @RequestParam("memberNameId") int memberId) {
        try {
            participantService.delete(memberId);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return "redirect:participant-page";
        }

        return "redirect:participant-page";
    }

    // endregion

    // region <ADD TEAM>

    @RequestMapping(value = {"/add-teams-page"})
    public String toAddTeamPage(Model model, HttpServletRequest request) {
        return Params.PAGE_ADD_TEAM;
    }

    @RequestMapping(value = {"/addTeam-form"})
    public String addTeam(Model model,
                            @RequestParam("nameTeam") String nameTeam,
                            @RequestParam("teamInfo") String teamInfo,
                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        int tournamentId = (int) session.getAttribute("selectedTournamentId");


        Team team = new Team();
        team.setTeamName(nameTeam);
        team.setParticipantInfo(teamInfo);
        team.setTournamentId(tournamentId);

        participantService.addParticipant(team);

        return "redirect:participant-page";
    }

    // endregion

    // region <UPDATE TEAM>

    @RequestMapping(value = "/updateTeam", method = RequestMethod.GET)
    public String updateTeam(Model model, HttpServletRequest request) {

        int teamId = Integer.parseInt(request.getParameter("teamNameId"));
        String nameUpdate = request.getParameter("teamName");
        String teamInfoUpdate = request.getParameter("teamInfo");

        Team team = (Team) participantService.getOne(Team.class, teamId);
        team.setTeamName(nameUpdate);
        team.setParticipantInfo(teamInfoUpdate);

        participantService.update(teamId, team );

        return "redirect:participant-page";
    }

    // region <DELETE TEAM>

    @RequestMapping(value = "/deleteTeam", method = RequestMethod.GET)
    public String deleteTeam(Model model,
                               @RequestParam("teamNameId") int teamId) {
        try {
            participantService.delete(teamId);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return "redirect:participant-page";
        }
        return "redirect:participant-page";
    }

    // endregion
}