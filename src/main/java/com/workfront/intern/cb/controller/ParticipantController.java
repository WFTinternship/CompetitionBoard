package com.workfront.intern.cb.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ParticipantController {
    private static Logger LOG = Logger.getLogger(ParticipantController.class);

    @RequestMapping(value = {"/participant-page"})
    public String toContactUsPage(Model model) {
        return "participant/participants";
    }

}
