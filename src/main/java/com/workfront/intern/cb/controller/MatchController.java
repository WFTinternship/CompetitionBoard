package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.web.util.Params;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MatchController {

    @RequestMapping(value = {"/match-page"})
    public String toMatchPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        return Params.PAGE_MATCH;
    }

    @RequestMapping(value = {"/all-match-page"})
    public String allMatchPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        return Params.PAGE_ALL_MATCH;
    }
}