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
import java.io.PrintWriter;

public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginInput = request.getParameter(Params.FORM_PARAM_LOG_IN);
        HttpSession session1 = request.getSession();
        session1.setAttribute("login", Params.FORM_PARAM_LOG_IN);

        String signInLoginInput = request.getParameter(Params.FORM_PARAM_SIGN_IN);
        HttpSession session2 = request.getSession();
        session2.setAttribute("signin", Params.FORM_PARAM_SIGN_IN);


        if (loginInput != null) {
            loginInput = request.getParameter(Params.FORM_PARAM_LOG_IN);
            String passwordInput = request.getParameter(Params.FORM_PARAM_LOG_IN_PASSWORD);

            // Encrypted input password
            String passwordEncrypt = StringHelper.passToEncrypt(passwordInput);

            Manager manager = new ManagerServiceImpl().getManagerByLogin(loginInput);
            String loginFromDb = manager.getLogin();
            String passwordFromDb = manager.getPassword();
            PrintWriter out = response.getWriter();

            // Check login and password for LogIn system
            if (loginInput.equals(loginFromDb) && passwordEncrypt.equals(passwordFromDb))

            {
                HttpSession session = request.getSession();
                session.setAttribute("usernameLogin", loginInput);

                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

            // Return wrong message where username is invalid
            if (loginInput.equals(loginFromDb)) {
                out.println("Sorry, username or password error!");
                request.getRequestDispatcher(Params.PAGE_ERROR_500).include(request, response);
            }
        }

        // Checking login
        if (signInLoginInput != null) {
            signInLoginInput = request.getParameter(Params.FORM_PARAM_SIGN_IN);
            String passwordSignInInput = request.getParameter(Params.FORM_PARAM_SIGN_IN_PASSWORD);

            Manager manager = new Manager();
            manager.setLogin(signInLoginInput);
            manager.setPassword(passwordSignInInput);

            new ManagerServiceImpl().addManager(manager);
            HttpSession session = request.getSession();
            session.setAttribute("userNameSignIn", signInLoginInput);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }


//        else {
//            System.out.println("Sorry, username or password error!");
//            request.getRequestDispatcher("/login.jsp").include(request, response);
//        }
//}
}
