package com.workfront.intern.cb.web.servlets;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.TournamentServiceImpl;
import com.workfront.intern.cb.web.util.Params;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * gets tournament list by specific tournament name
 */
public class SearchTournamentsByNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTournamentStr = request.getParameter("searchStr");
        List<Tournament> tournamentList = new TournamentServiceImpl().getTournamentListByName(searchTournamentStr);
        int listSize = tournamentList.size();

        if (listSize != 0) {
            request.setAttribute("searchResult", tournamentList);

            request.getRequestDispatcher("/search-result.jsp").forward(request, response);
        } else {
            request.setAttribute("noSearchResultMsg", "No tournament/s found");
            request.getRequestDispatcher(Params.PAGE_INDEX).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
