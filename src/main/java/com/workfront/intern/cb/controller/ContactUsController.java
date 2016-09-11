package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.web.util.Helpers;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactUsController {
    private static Logger LOG = Logger.getLogger(ContactUsController.class);

    @RequestMapping(value = {"/contact-page"})
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

        String msg = "NAME: " + name + "\n" +
                "SURNAME: " + surname + "\n" +
                "EMAIL: " + email + "\n" +
                "PHONE: " + phone + "\n" +
                "===========================================================================================" + "\n\n" +
                "MESSAGE: " + "\n" + message;

        Helpers.sendEmail(msg);

        return "redirect:/home";
    }
}
