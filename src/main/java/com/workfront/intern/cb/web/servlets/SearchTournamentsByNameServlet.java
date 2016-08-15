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

        if (listSize == 0) {
            request.setAttribute("noSearchResultMsg", "No tournament/s found");
            request.getRequestDispatcher(Params.PAGE_INDEX).forward(request, response);
        } else {
            request.setAttribute("tournamentList", tournamentList);
            for (int i = 0; i < listSize; i++) {
                request.setAttribute("tournamentId", tournamentList.get(i).getTournamentId());
                request.setAttribute("tournamentName", tournamentList.get(i).getTournamentName());
                request.setAttribute("startDate", tournamentList.get(i).getStartDate());
                request.setAttribute("endDate", tournamentList.get(i).getEndDate());
                request.setAttribute("tournamentLocation", tournamentList.get(i).getLocation());
                request.setAttribute("tournamentDescription", tournamentList.get(i).getTournamentDescription());
                request.setAttribute("tournamentFormatId", tournamentList.get(i).getTournamentFormatId());
                request.setAttribute("tournamentManagerId", tournamentList.get(i).getManagerId());

                request.getRequestDispatcher(Params.PAGE_SEARCH_RESULT).forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
