package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.GroupService;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.TournamentService;
import com.workfront.intern.cb.web.util.Helpers;
import com.workfront.intern.cb.web.util.Params;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class TournamentController {
    private static Logger LOG = Logger.getLogger(TournamentController.class);

    @Autowired
    ManagerService managerService;

    @Autowired
    TournamentService tournamentService;

    @Autowired
    GroupService groupService;

    @RequestMapping(value = {"/tournament-page"})
    public String toTournamentPage(Model model, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        int managerId = manager.getId();

        List<Tournament> tournamentList = tournamentService.getTournamentListByManager(managerId);
        request.setAttribute("tournamentListByManager", tournamentList);

        return Params.PAGE_TOURNAMENT;
    }

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

    @RequestMapping(value = {"/addTournamentForm"}, method = RequestMethod.GET)
    public String addTournament(Model model,
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
            tournament.setStartDate(Helpers.parseStringToTimeStamp(startDate));
            tournament.setEndDate(Helpers.parseStringToTimeStamp(endDate));
            tournament.setLocation(location);
            tournament.setTournamentDescription(description);
            tournament.setTournamentFormatId(format);
            tournament.setManagerId(managerId);

            tournamentService.addTournament(tournament);

            List<Tournament> tournamentListByManager = tournamentService.getTournamentListByManager(managerId);
            session.setAttribute("tournamentListByManager", tournamentListByManager);

        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            // Checking duplicate of manager name during registration
            request.setAttribute("existsTournament", "Sorry, but tournament with this name exists");

            return "forward:addTournament-page";
        }

        return "redirect:tournament-page";
    }
    // endregion

    // region <EDIT(UPDATE) TOURNAMENT>

    @RequestMapping(value = "/updateTournament", method = RequestMethod.GET)
    public String updateTournament(Model model,
                                   HttpServletRequest request) {

        String nameUpdate = request.getParameter("nameUpdate");
        int tournamentId = Integer.parseInt(request.getParameter("tournamentNameId"));
        Timestamp startDateUpdate = Timestamp.valueOf(request.getParameter("startDateUpdate"));
        Timestamp endDateUpdate = Timestamp.valueOf(request.getParameter("endDateUpdate"));
        String locationUpdate = request.getParameter("locationUpdate");
        String descriptionUpdate = request.getParameter("descriptionUpdate");

        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        tournament.setTournamentName(nameUpdate);
        tournament.setStartDate(startDateUpdate);
        tournament.setEndDate(endDateUpdate);
        tournament.setLocation(locationUpdate);
        tournament.setTournamentDescription(descriptionUpdate);

        tournamentService.updateTournament(tournamentId, tournament);

        return "redirect:tournament-page";
    }

    // endregion

    // region <DELETE TOURNAMENT>

    @RequestMapping(value = "/deleteTournament", method = RequestMethod.GET)
    public String deleteTournament(Model model,
                                   @RequestParam("tournamentNameId") int tournamentId) {

        try {
            tournamentService.deleteTournamentById(tournamentId);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return "redirect:tournament-page";
        }

        return "redirect:tournament-page";
    }

    // endregion
}