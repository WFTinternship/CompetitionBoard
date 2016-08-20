package com.workfront.intern.cb.web.servlets.tournament;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.TournamentService;
import com.workfront.intern.cb.spring.CompetitionBoardApp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddTournamentServlet extends HttpServlet {
    private TournamentService tournamentService;

    @Override
    public void init() throws ServletException {
        super.init();
        tournamentService = CompetitionBoardApp.getApplicationContext(getServletContext()).getBean(TournamentService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");

        String name = request.getParameter("name");
//        Timestamp startDate = Timestamp.valueOf(request.getParameter("start_date"));
//        Timestamp endDate = Timestamp.valueOf(request.getParameter("end_date"));
        String location = request.getParameter("location");
        String description = request.getParameter("tournament_description");
        int format = Integer.parseInt(request.getParameter("format"));
        int managerId = manager.getId();

        Tournament tournament = new Tournament();
        tournament.setTournamentName(name);
//        tournament.setStartDate(startDate);
//        tournament.setStartDate(endDate);
        tournament.setLocation(location);
        tournament.setTournamentDescription(description);
        tournament.setTournamentFormatId(format);
        tournament.setManagerId(managerId);

        tournamentService.addTournament(tournament);

        request.setAttribute("managerId", managerId);
        request.getRequestDispatcher("/tournament.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
