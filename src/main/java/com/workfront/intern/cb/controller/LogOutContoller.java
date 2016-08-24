package com.workfront.intern.cb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LogOutContoller {

    @RequestMapping("/logout-page")
    public String toLogOut(Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        return "redirect:index.jsp";
    }
}
