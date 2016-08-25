package com.workfront.intern.cb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactUsController {

    @RequestMapping("/contact-page")
    public String toContactUsPage(Model model) {
        return "contact/contact-us";
    }
}
