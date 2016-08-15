package com.workfront.intern.cb.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String signInLoginInput = request.getParameter("userNameSignIn");
//        String passwordSignInInput = request.getParameter("passwordSignIn");
//
//        Manager manager = new Manager();
//        manager.setLogin(signInLoginInput);
//        manager.setPassword(passwordSignInInput);
//
//        new ManagerServiceImpl().addManager(manager);
//        HttpSession session = request.getSession();
//        session.setAttribute("userNameSignIn", signInLoginInput);
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
