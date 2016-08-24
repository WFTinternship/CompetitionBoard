package com.workfront.intern.cb.controller.tournament;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.TournamentService;
import com.workfront.intern.cb.web.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class SearchTournamentsByNameController {
    @Autowired
    ManagerService managerService;

    @Autowired
    TournamentService tournamentService;

    @RequestMapping("/search-result")
    public String start(Model model) {
        return Params.PAGE_SEARCH_TOURNAMENT_BY_NAME_RESULT;
    }

    @RequestMapping("/searchTournamentByNameForm")
    public String searchTournamentsByName(Model model,
                                          @RequestParam("searchStr") String searchTournamentStr,
                                          HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Tournament> tournamentList = tournamentService.getTournamentListByName(searchTournamentStr);

        int listSize = tournamentList.size();
        if (listSize != 0) {
            List<Manager> managerList = managerService.getManagerList();
            model.addAttribute("searchResultList", tournamentList);
            model.addAttribute("searchResultListManager", managerList);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("noSearchResultMsg", "No tournament/s found");
            return "redirect" + Params.PAGE_INDEX;
        }

        return Params.PAGE_SEARCH_TOURNAMENT_BY_NAME_RESULT;
    }
}
