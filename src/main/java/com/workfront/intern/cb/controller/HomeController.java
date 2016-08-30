package com.workfront.intern.cb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String toHomePage(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @RequestMapping("/contact-page")
    public String toContactUsPage(Model model) {
        return "contact/contact-us";
    }
}