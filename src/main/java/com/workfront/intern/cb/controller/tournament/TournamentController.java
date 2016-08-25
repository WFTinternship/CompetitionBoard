package com.workfront.intern.cb.controller.tournament;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.TournamentService;
import com.workfront.intern.cb.web.util.Params;
import com.workfront.intern.cb.web.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TournamentController {

    @Autowired
    ManagerService managerService;

    @Autowired
    TournamentService tournamentService;

    @RequestMapping("/tournament-page")
    public String toTournamentPage(Model model) {
        return Params.PAGE_TOURNAMENT;
    }

    @RequestMapping("/search-result")
    public String toSearchResultPage(Model model) {
        return Params.PAGE_SEARCH_TOURNAMENT_BY_NAME;
    }

    @RequestMapping("/searchTournamentByNameForm")
    public String searchTournamentsByName(Model model,
                                          @RequestParam("searchStr") String searchTournamentStr,
                                          HttpServletRequest request, HttpServletResponse response) {

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

        return Params.PAGE_SEARCH_TOURNAMENT_BY_NAME;
    }

    // region <ADD TOURNAMENT CASES>

    @RequestMapping("/addTournament-page")
    public String toAddTournamentPage(Model model) {
        return Params.PAGE_ADD_TOURNAMENT;   }


    @RequestMapping("/addTournamentForm")
    public String addTournaments(Model model,
                                          @RequestParam("name") String name,
                                          @RequestParam("startDate") String startDate,
                                          @RequestParam("endDate") String endDate,
                                          @RequestParam("location") String location,
                                          @RequestParam("tournament_description") String description,
                                          @RequestParam("format") int format,
                                          HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        int managerId = manager.getId();
        try {
            Tournament tournament = new Tournament();
            tournament.setTournamentName(name);
            tournament.setStartDate(Util.stringParseToTimeStamp(startDate));
            tournament.setEndDate(Util.stringParseToTimeStamp(endDate));
            tournament.setLocation(location);
            tournament.setTournamentDescription(description);
            tournament.setTournamentFormatId(format);
            tournament.setManagerId(managerId);

            tournamentService.addTournament(tournament);

            List<Tournament> tournamentListByManager =  tournamentService.getTournamentListByManager(managerId);
            session.setAttribute("tournamentListByManager", tournamentListByManager);

        } catch (RuntimeException ex) {
            // Checking duplicate of manager name during registration
            request.setAttribute("existsTournament", "Sorry, but tournament with this name exists");
//            request.getRequestDispatcher(Params.PAGE_ADD_TOURNAMENT).include(request, response);
        }

        return "redirect:tournament-page";
    }

    // endregion
}

