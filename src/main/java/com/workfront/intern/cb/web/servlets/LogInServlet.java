package com.workfront.intern.cb.web.servlets;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.util.StringHelper;
import com.workfront.intern.cb.service.ManagerServiceImpl;
import com.workfront.intern.cb.util.Params;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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

        PrintWriter out = response.getWriter();

        /**
         * Check login and password for LogIn system
         */
        if (login.equals(getManagerLoginStr) && passwordEncrypt.equals(getManagerPasswordStr)) {
            HttpSession session = request.getSession();
            session.setAttribute("usernameLogin", login);

            request.getRequestDispatcher(Params.PAGE_INDEX).forward(request, response);
        }

        /**
         * Check login and password for LogIn system
         */
        if (login.equals(getManagerLoginStr)) {
            out.println("Sorry, username or password error!");
            request.getRequestDispatcher(Params.PAGE_SEARCH_RESULT).include(request, response);
        }

//        else {
//            System.out.println("Sorry, username or password error!");
//            request.getRequestDispatcher("/login.jsp").include(request, response);
//        }
    }
}
