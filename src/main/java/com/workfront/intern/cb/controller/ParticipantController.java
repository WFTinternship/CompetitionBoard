package com.workfront.intern.cb.controller;


import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.service.ParticipantService;
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

    @RequestMapping(value = {"/participant-page"})
    public String toContactUsPage(Model model,
                                  @RequestParam("page")String page,
                                  HttpServletRequest request) {
        System.out.println(page);
        List<Member> membersList = (List<Member>) participantService.getAll(Member.class);
        model.addAttribute("membersList", membersList);

        return Params.PAGE_PARTICIPANTS;
    }

    // region <ADD MEMBER>

    @RequestMapping(value = {"/add-members-page"})
    public String toAddMembersPage(Model model) {

        return Params.PAGE_ADD_MEMBER;
    }

    @RequestMapping(value = {"/addMemberForm"})
    public String addMember(Model model,
                            @RequestParam("nameMember") String nameMember,
                            @RequestParam("surNameMember") String surNameMember,
                            @RequestParam("positionMember") String positionMember,
                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member member = new Member();
        member.setName(nameMember);
        member.setSurName(surNameMember);
        member.setPosition(positionMember);

        participantService.addParticipant(member);


        return "participant/participants";
    }

    // endregion

}
