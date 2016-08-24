package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.web.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class SignUpContoller {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/signup-page")
    public String toSignUp() {
        return Params.PAGE_SIGN_UP;
    }


    @RequestMapping(value = "/signup-form", method = RequestMethod.POST)
    public String logInReg(Model model,
                           @RequestParam("userNameSignIn") String signInLoginInput,
                           @RequestParam("passwordSignIn") String passwordSignInInput,
                           HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        try {
            Manager signInUser = new Manager();
            signInUser.setLogin(signInLoginInput);
            signInUser.setPassword(passwordSignInInput);

            managerService.addManager(signInUser);

            // Gets added manager id and set in session
            Manager manager = managerService.getManagerByLogin(signInLoginInput);
            session.setAttribute("manager", manager);

//            request.getRequestDispatcher(Params.PAGE_INDEX).forward(request, response);
        } catch (RuntimeException ex) {
            // Checking duplicate of manager name during registration
            request.setAttribute("existsManager", "Sorry, but user with this name exists");
            request.getRequestDispatcher(Params.PAGE_SIGN_UP).include(request, response);
        }

        return "redirect:index.jsp";
    }
}
