//package com.workfront.intern.cb.web.servlets;
//
//import com.workfront.intern.cb.common.Manager;
//import com.workfront.intern.cb.service.ManagerService;
//import com.workfront.intern.cb.service.ManagerServiceImpl;
//import com.workfront.intern.cb.spring.CompetitionBoardApp;
//import com.workfront.intern.cb.web.util.Params;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class SignInServlet extends HttpServlet {
//    ManagerService managerService;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        managerService = CompetitionBoardApp.getApplicationContext(getServletContext()).getBean(ManagerService.class);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//
//        try {
//            String signInLoginInput = request.getParameter(Params.FORM_PARAM_SIGN_IN);
//            String passwordSignInInput = request.getParameter(Params.FORM_PARAM_SIGN_IN_PASSWORD);
//
//            Manager signInUser = new Manager();
//            signInUser.setLogin(signInLoginInput);
//            signInUser.setPassword(passwordSignInInput);
//
//            managerService.addManager(signInUser);
//
//            // Gets added manager id and set in session
//            Manager manager = managerService.getManagerByLogin(signInLoginInput);
//            session.setAttribute("manager", manager);
//
//
//            request.getRequestDispatcher(Params.PAGE_INDEX).forward(request, response);
//        } catch (RuntimeException ex) {
//            // Checking duplicate of manager name during registration
//            request.setAttribute("existsManager", "Sorry, but user with this name exists");
//            request.getRequestDispatcher(Params.PAGE_SIGN_IN).include(request, response);
//        }
//    }
//}
