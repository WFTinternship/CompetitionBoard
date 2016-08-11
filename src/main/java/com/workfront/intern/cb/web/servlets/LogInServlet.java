package com.workfront.intern.cb.web.servlets;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.util.StringHelper;
import com.workfront.intern.cb.service.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("usernameLogin");
        String password = request.getParameter("passwordLogin");
        String passwordEncrypt = StringHelper.passToEncrypt(password);

        Manager manager = new ManagerServiceImpl().getManagerByLogin(login);
        String getManagerLoginStr = manager.getLogin();
        String getManagerPasswordStr = manager.getPassword();

        if (login.equals(getManagerLoginStr) && passwordEncrypt.equals(getManagerPasswordStr)) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            response.sendRedirect("/error500.jsp");
        }
    }
}
