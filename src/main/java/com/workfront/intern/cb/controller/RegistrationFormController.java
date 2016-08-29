package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.util.StringHelper;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.web.util.Params;
import com.workfront.intern.cb.web.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class RegistrationFormController {

    @Autowired
    private ManagerService managerService;

    // region <SIGN-UP CASES>

    @RequestMapping("/signup-page")
    public String toSignUpPage() {
        return Params.PAGE_SIGN_UP;
    }

    @RequestMapping(value = "/signup-form", method = RequestMethod.POST)
    public String signUp(Model model,
                         @RequestParam("userNameSignIn") String signInLoginInput,
                         @RequestParam("user_avatar") String userAvatar,
                         @RequestParam("passwordSignIn") String passwordSignInInput,
                         @RequestParam("passwordConfirmSignIn") String passwordConfirmSignIn,
                         HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        // Checking valid passwords in two fields
        if (!passwordSignInInput.equals(passwordConfirmSignIn)) {
            String passErr = "Password does not match";
            model.addAttribute("passwordNotMatchErr", passErr);

            return "secure/sign-up";
        }
        try {
            Manager signInUser = new Manager();
            // set login
            signInUser.setLogin(signInLoginInput);

            // set password
            signInUser.setPassword(passwordSignInInput);

            managerService.addManager(signInUser);

            // Gets added manager id and set in session
            Manager manager = managerService.getManagerByLogin(signInLoginInput);
            session.setAttribute("manager", manager);

        } catch (RuntimeException ex) {
            // Checking duplicate of manager name during registration
            session.setAttribute("errMessage", "Sorry, but user with this name exists");
            return "redirect:signup-page";
        }

        return "redirect:/";
    }
    // endregion


    // region <LOG-IN CASES>

    @RequestMapping("/login-page")
    public String toLogIinPage() {
        return Params.PAGE_LOG_IN;
    }

    @RequestMapping(value = "/login-form", method = RequestMethod.POST)
    public String logIn(Model model,
                        @RequestParam("usernameLogin") String loginInput,
                        @RequestParam("passwordLogin") String passwordInput,
                        HttpServletRequest request, HttpServletResponse response) {

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
            } catch (Exception ex) {
                session.setAttribute("userNameErr", "Sorry, username or password error");
                return "redirect:login-page";
            }
        }

        return "redirect:/";
    }
    // endregion

    // region <LOG-OUT CASES>

    @RequestMapping("/logout-page")
    public String toLogOutPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

// endregion
}