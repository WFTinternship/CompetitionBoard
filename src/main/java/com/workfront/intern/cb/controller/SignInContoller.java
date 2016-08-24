package com.workfront.intern.cb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInContoller {

    @RequestMapping(value = "/signIn")
    public String toSignIn() {
        return "to-signIn";
    }

  }
