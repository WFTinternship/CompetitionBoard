package com.workfront.intern.cb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/send-mail", method = RequestMethod.POST)
    public String sendMailFromForm(Model model,
                                   @RequestParam("name") String name,
                                   @RequestParam("surname") String surname,
                                   @RequestParam("email") String email,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("message") String message) {

        //ToDO

        return "/";
    }
}