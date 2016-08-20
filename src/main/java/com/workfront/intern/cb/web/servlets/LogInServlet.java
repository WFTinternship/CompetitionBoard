package com.workfront.intern.cb.web.servlets;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.util.StringHelper;
import com.workfront.intern.cb.service.ManagerServiceImpl;
import com.workfront.intern.cb.web.util.Params;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginInput = request.getParameter(Params.FORM_PARAM_LOG_IN);
        String passwordInput = request.getParameter(Params.FORM_PARAM_LOG_IN_PASSWORD);

        if (loginInput != null && passwordInput != null) {
            // Encrypted input password
            String passwordEncrypt = StringHelper.passToEncrypt(passwordInput);

            // Check valid login and password.
            // For invalid login and password sends error messages.
            try {
                Manager manager = new ManagerServiceImpl().getManagerByLogin(loginInput);
                int managerId = manager.getId();
                String loginFromDb = manager.getLogin();
                String passwordFromDb = manager.getPassword();

                // Check login and password for LogIn system
                if (loginInput.equals(loginFromDb) && passwordEncrypt.equals(passwordFromDb)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loginInput", loginInput);
                    session.setAttribute("managerId", managerId);

                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } catch (RuntimeException ex) {
                request.setAttribute("userNameErr", "Sorry, username or password error");
                request.getRequestDispatcher(Params.PAGE_LOG_IN).include(request, response);
            }
        }
    }
}