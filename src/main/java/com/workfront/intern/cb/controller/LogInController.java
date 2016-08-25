package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.util.StringHelper;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.web.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LogInController extends HttpServlet {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/login-page")
    public String toLogIinPage() {
        return Params.PAGE_LOG_IN;
    }

    @RequestMapping(value = "/login-form", method = RequestMethod.POST)
    public String logInReg(Model model,
                           @RequestParam("usernameLogin") String loginInput,
                           @RequestParam("passwordLogin") String passwordInput,
                           HttpServletRequest request, HttpServletResponse response)  {

        HttpSession session = request.getSession();

        if (loginInput != null && passwordInput != null) {
            // Encrypted input password
            String passwordEncrypt = StringHelper.passToEncrypt(passwordInput);

            // Check valid login and password.
            // For invalid login and password sends error messages.
            try {
                Manager manager = managerService.getManagerByLogin(loginInput);
                String loginFromDb = manager.getLogin();
                String passwordFromDb = manager.getPassword();

                // Check login and password for LogIn system
                if (loginInput.equals(loginFromDb) && passwordEncrypt.equals(passwordFromDb)) {
                    session.setAttribute("manager", manager);
                }
            } catch (RuntimeException ex) {
                session.setAttribute("userNameErr", "Sorry, username or password error");
                return "redirect:login-page";
            }
        }

        return "redirect:/";
    }

}