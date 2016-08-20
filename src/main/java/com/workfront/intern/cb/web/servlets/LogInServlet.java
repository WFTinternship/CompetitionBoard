package com.workfront.intern.cb.web.servlets;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.util.StringHelper;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.spring.CompetitionBoardApp;
import com.workfront.intern.cb.web.util.Params;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInServlet extends HttpServlet {
    private ManagerService managerService;

    @Override
    public void init() throws ServletException {
        super.init();
        managerService = CompetitionBoardApp.getApplicationContext(getServletContext()).getBean(ManagerService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Autowired
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginInput = request.getParameter(Params.FORM_PARAM_LOG_IN);
        String passwordInput = request.getParameter(Params.FORM_PARAM_LOG_IN_PASSWORD);

        if (loginInput != null && passwordInput != null) {
            // Encrypted input password
            String passwordEncrypt = StringHelper.passToEncrypt(passwordInput);

            try {
                Manager manager = managerService.getManagerByLogin(loginInput);
                String loginFromDb = manager.getLogin();
                String passwordFromDb = manager.getPassword();

                // Checking of login and password for login system
                if (loginInput.equals(loginFromDb) && passwordEncrypt.equals(passwordFromDb)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usernameLogin", loginInput);

                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                if (loginInput.equals(loginFromDb) && passwordEncrypt.equals(passwordFromDb)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usernameLogin", loginInput);

                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } catch (RuntimeException ex) {
                request.setAttribute("userNameErr", "Sorry, username or password error");
                request.getRequestDispatcher(Params.PAGE_LOG_IN).include(request, response);
            }
        }
    }
}


