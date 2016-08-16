package com.workfront.intern.cb.web.servlets;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.TournamentServiceImpl;

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
        Manager manager = (Manager) session.getAttribute("manager");

        Tournament tournament = new Tournament();
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        int format = Integer.parseInt(request.getParameter("format"));

        int id = manager.getId();


        tournament.setTournamentName(name);
        tournament.setLocation(location);
        tournament.setTournamentFormatId(format);
        tournament.setManagerId(id);

        new TournamentServiceImpl().addTournament(tournament);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
