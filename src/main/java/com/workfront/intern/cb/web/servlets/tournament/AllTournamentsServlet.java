package com.workfront.intern.cb.web.servlets.tournament;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.TournamentService;
import com.workfront.intern.cb.spring.CompetitionBoardApp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AllTournamentsServlet extends HttpServlet {
    TournamentService tournamentService;

    @Override
    public void init() throws ServletException {
        super.init();
        tournamentService = CompetitionBoardApp.getApplicationContext(getServletContext()).getBean(TournamentService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Tournament> allTournamentList = tournamentService.getTournamentList();
        session.setAttribute("allTournamentList", allTournamentList);

        response.sendRedirect("all-tournaments.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}