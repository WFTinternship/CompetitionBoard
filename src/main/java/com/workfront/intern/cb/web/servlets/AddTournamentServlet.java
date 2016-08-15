package com.workfront.intern.cb.web.servlets;

import com.workfront.intern.cb.web.util.Params;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTournamentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object sessionContext_1 = request.getSession().getAttribute(Params.FORM_PARAM_SIGN_IN);
        Object sessionContext_2 = request.getSession().getAttribute(Params.FORM_PARAM_LOG_IN);

        if (sessionContext_1 == null) {


        }


//        Tournament tournament = null;
//        String name = (String) session1.getAttribute("name");
//        String location = (String) session.getAttribute("location");
//        int id = (int) session.getAttribute("manager_id");
//
//        tournament.setTournamentName(name);
//        tournament.setLocation(location);
//        tournament.setManagerId(id);
//
//        new TournamentServiceImpl().addTournament(tournament);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
