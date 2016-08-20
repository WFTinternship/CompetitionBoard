package com.workfront.intern.cb.web.servlets.tournament;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.TournamentServiceImpl;
import com.workfront.intern.cb.web.util.Params;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddTournamentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int managerId = (int) session.getAttribute("managerId");

        try {
            String name = request.getParameter("name");
//        Timestamp startDate = Timestamp.valueOf(request.getParameter("start_date"));
//        Timestamp endDate = Timestamp.valueOf(request.getParameter("end_date"));
            String location = request.getParameter("location");
            String description = request.getParameter("tournament_description");
            int format = Integer.parseInt(request.getParameter("format"));

            Tournament tournament = new Tournament();
            tournament.setTournamentName(name);
//        tournament.setStartDate(startDate);
//        tournament.setStartDate(endDate);
            tournament.setLocation(location);
            tournament.setTournamentDescription(description);
            tournament.setTournamentFormatId(format);
            tournament.setManagerId(managerId);

            new TournamentServiceImpl().addTournament(tournament);
            request.getRequestDispatcher(Params.PAGE_TOURNAMENT).forward(request, response);
        } catch (RuntimeException ex) {
            // Checking duplicate of manager name during registration
            request.setAttribute("existsTournament", "Sorry, but tournament with this name exists");
            request.getRequestDispatcher(Params.PAGE_ADD_TOURNAMENT).include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
