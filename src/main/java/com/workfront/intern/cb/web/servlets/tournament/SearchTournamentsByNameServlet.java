//package com.workfront.intern.cb.web.servlets.tournament;
//
//import com.workfront.intern.cb.common.Manager;
//import com.workfront.intern.cb.common.Tournament;
//import com.workfront.intern.cb.service.ManagerService;
//import com.workfront.intern.cb.service.TournamentService;
//import com.workfront.intern.cb.spring.CompetitionBoardApp;
//import com.workfront.intern.cb.web.util.Params;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
///**
// * gets tournament list by specific tournament name
// */
//public class SearchTournamentsByNameServlet extends HttpServlet {
//    private ManagerService managerService;
//    private TournamentService tournamentService;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        managerService = CompetitionBoardApp.getApplicationContext(getServletContext()).getBean(ManagerService.class);
//        tournamentService = CompetitionBoardApp.getApplicationContext(getServletContext()).getBean(TournamentService.class);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String searchTournamentStr = request.getParameter("searchStr");
//        List<Tournament> tournamentList = tournamentService.getTournamentListByName(searchTournamentStr);
//
//        int listSize = tournamentList.size();
//        if (listSize != 0) {
//            HttpSession session = request.getSession();
//            List<Manager> managerList = managerService.getManagerList();
//
//            session.setAttribute("searchResultList", tournamentList);
//            session.setAttribute("searchResultListManager", managerList);
//
//
//            request.getRequestDispatcher(Params.PAGE_SEARCH_TOURNAMENT_BY_NAME_RESULT).forward(request, response);
//
//        } else {
//            request.setAttribute("noSearchResultMsg", "No tournament/s found");
//            request.getRequestDispatcher(Params.PAGE_INDEX).forward(request, response);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//    }
//}
