package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.TournamentService;
import com.workfront.intern.cb.web.util.Helpers;
import com.workfront.intern.cb.web.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = {"/tournament-page"})
    public String toTournamentPage(Model model,
                                   HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        int managerId = manager.getId();

        List<Tournament> tournamentList = tournamentService.getTournamentListByManager(managerId);
        model.addAttribute("tournamentListByManager", tournamentList);

        return Params.PAGE_TOURNAMENT;
    }

    // region <SEARCH TOURNAMENT>

    @RequestMapping(value = {"/search-result"})
    public String toSearchResultPage(Model model) {
        return Params.PAGE_SEARCH_TOURNAMENT_BY_NAME;
    }

    @RequestMapping(value = {"/searchTournamentByName-form"})
    public String searchTournamentsByName(Model model,
                                          @RequestParam("searchStr") String searchTournamentStr) {

        List<Tournament> tournamentList = tournamentService.getTournamentListByName(searchTournamentStr);

        int listSize = tournamentList.size();
        if (listSize != 0) {
//            List<Manager> managerList = managerService.getManagerList();
            model.addAttribute("searchResultList", tournamentList);
            model.addAttribute("managerService", managerService);
        } else {
            model.addAttribute("noSearchResultMsg", "No tournament(s) found with provided search criteria");
            return "index";
        }
        return Params.PAGE_SEARCH_TOURNAMENT_BY_NAME;
    }
    // endregion

    @RequestMapping(value = {"/all-tournaments-page"})
    public String allTournament(Model model, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        List<Tournament> allTournamentList = tournamentService.getTournamentList();


        session.setAttribute("managerService", managerService);
        session.setAttribute("allTournamentList", allTournamentList);

        return Params.PAGE_ALL_AVAILABLE_TOURNAMENTS;
    }

    // region <ADD TOURNAMENT>

    @RequestMapping(value = {"/addTournament-page"})
    public String toAddTournamentPage(Model model) {
        return Params.PAGE_ADD_TOURNAMENT;
    }

    @RequestMapping(value = {"/addTournamentForm"}, method = RequestMethod.POST)
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
            tournament.setStartDate(Helpers.stringParseToTimeStamp(startDate));
            tournament.setEndDate(Helpers.stringParseToTimeStamp(endDate));
            tournament.setLocation(location);
            tournament.setTournamentDescription(description);
            tournament.setTournamentFormatId(format);
            tournament.setManagerId(managerId);

            tournamentService.addTournament(tournament);

            List<Tournament> tournamentListByManager = tournamentService.getTournamentListByManager(managerId);
            session.setAttribute("tournamentListByManager", tournamentListByManager);

        } catch (RuntimeException ex) {
            // Checking duplicate of manager name during registration
            session.setAttribute("existsTournament", "Sorry, but tournament with this name exists");

            return "redirect:addTournament-page";
        }

        return "redirect:tournament-page";
    }
    // endregion

    // region <EDIT(UPDATE) TOURNAMENT>

    @RequestMapping(value = "/updateTournament", method = RequestMethod.POST)
    public String updateTournament(Model model, HttpServletRequest request) {
        int tournamentId = 544;

        String nameUpdate = request.getParameter("nameUpdate");
        String startDateUpdate = request.getParameter("startDateUpdate");
        String endDateUpdate = request.getParameter("endDateUpdate");
        String locationUpdate = request.getParameter("locationUpdate");
        String descriptionUpdate = request.getParameter("descriptionUpdate");

        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        tournament.setTournamentName(nameUpdate);
        //TODO: update timestamps based on User input
//        tournament.setStartDate(startDateUpdate);
//        tournament.setEndDate(endDateUpdate);
        tournament.setLocation(locationUpdate);
        tournament.setTournamentDescription(descriptionUpdate);

        tournamentService.updateTournament(tournamentId, tournament);

        return "redirect:tournament-page";
    }

    // endregion

    // region <DELETE TOURNAMENT>

    @RequestMapping(value = "deleteTournament-form", method = RequestMethod.POST)
    public String deleteTournament(Model model, @RequestParam("tournament") int tournamentId) {

        tournamentService.deleteTournamentById(tournamentId);

        return "redirect:tournament-page";
    }

    // endregion
}