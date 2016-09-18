package com.workfront.intern.cb.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    private static Logger LOG = Logger.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "/home"})
    public String toHomePage(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
}