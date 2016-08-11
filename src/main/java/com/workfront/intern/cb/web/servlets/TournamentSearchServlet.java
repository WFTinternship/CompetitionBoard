package com.workfront.intern.cb.web.servlets;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.TournamentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TournamentSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTournamentStr = request.getParameter("tournamentSearch");
        List<Tournament> list = new TournamentServiceImpl().getTournamentListByName(searchTournamentStr);
        System.out.println(list);

//        request.setAttribute();

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
