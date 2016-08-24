package com.workfront.intern.cb.controller.tournament;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.TournamentService;
import com.workfront.intern.cb.web.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AllTournamentsController {

    @Autowired
    TournamentService tournamentService;

    @RequestMapping("/all-tournaments")
    public String allTournamentPage(Model model, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        List<Tournament> allTournamentList = tournamentService.getTournamentList();
        session.setAttribute("allTournamentList", allTournamentList);

        return "redirect:" + Params.PAGE_ALL_AVALABLE_TOURNAMENTS;
    }
}

