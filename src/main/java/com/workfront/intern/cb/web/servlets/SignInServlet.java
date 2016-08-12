package com.workfront.intern.cb.web.servlets;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.service.ManagerServiceImpl;

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
        String registrLogin = request.getParameter("userNameSignIn");
        String password = request.getParameter("passwordSignIn");

        Manager manager = new Manager();
        manager.setLogin(registrLogin);
        manager.setPassword(password);

        new ManagerServiceImpl().addManager(manager);
        HttpSession session = request.getSession();
        session.setAttribute("userNameSignIn", registrLogin);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
