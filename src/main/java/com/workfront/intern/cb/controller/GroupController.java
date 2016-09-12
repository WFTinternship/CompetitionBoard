package com.workfront.intern.cb.controller;


import com.workfront.intern.cb.web.util.Params;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GroupController {
    private static Logger LOG = Logger.getLogger(GroupController.class);

    @RequestMapping(value = {"/group-page"})
    public String toGroupPage(Model model) {

        return Params.PAGE_GROUPS;
    }


    @RequestMapping(value = {"/add-group-page"})
    public String toAddPage(Model model) {

        return Params.PAGE_ADD_GROUP;
    }

    @RequestMapping(value = {"/addGroupForm"})
    public String addGroup(Model model,
                           @RequestParam("nameMember") String nameMember
                           ) {

        return Params.PAGE_GROUPS;
    }
}
