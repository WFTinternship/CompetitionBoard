package com.workfront.intern.cb.web.servlets;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.service.ManagerServiceImpl;
import com.workfront.intern.cb.web.util.Params;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String signInLoginInput = request.getParameter(Params.FORM_PARAM_SIGN_IN);
        String passwordSignInInput = request.getParameter(Params.FORM_PARAM_SIGN_IN_PASSWORD);

        Manager manager = new Manager();
        manager.setLogin(signInLoginInput);
        manager.setPassword(passwordSignInInput);

        new ManagerServiceImpl().addManager(manager);
        session.setAttribute("signInLoginInput", signInLoginInput);

        // Gets added manager id and set in session
        Manager managerFromDb = new ManagerServiceImpl().getManagerByLogin(signInLoginInput);
        int managerId = managerFromDb.getId();
        session.setAttribute("managerId", managerId);

        request.getRequestDispatcher(Params.PAGE_INDEX).forward(request, response);
    }
}
