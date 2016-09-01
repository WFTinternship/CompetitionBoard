package com.workfront.intern.cb.service.contollers.unit;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.controller.TournamentController;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.TournamentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.workfront.intern.cb.web.util.Params.PAGE_ALL_AVAILABLE_TOURNAMENTS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TournamentControllerUnitTest extends BaseTest {

    private static TournamentController controller;

    private ManagerService managerService;
    private TournamentService tournamentService;

    private Manager testManager;
    private Tournament testTournament;

    private HttpServletRequest testRequest;
    private HttpServletResponse testResponse;
    private HttpSession testSession;
    private HttpServletResponse response;
    private Model model;

    @Before
    public void beforeTest() {
        controller = new TournamentController();

        testManager = createRandomManager();
        testTournament = createRandomTournament();

        managerService = mock(ManagerService.class);
        tournamentService = mock(TournamentService.class);

        Whitebox.setInternalState(controller, "managerService", managerService);
        Whitebox.setInternalState(controller, "tournamentService", tournamentService);


        testRequest = mock(HttpServletRequest.class);
        testResponse = mock(HttpServletResponse.class);
        testSession = mock(HttpSession.class);
        response = mock(HttpServletResponse.class);
        model = mock(Model.class);

        when(testRequest.getSession()).thenReturn(testSession);
        testManager = createRandomManager();
        testTournament = createRandomTournament();
        when(testRequest.getParameter("usernameLogin")).thenReturn(testManager.getLogin());
        when(testRequest.getParameter("passwordLogin")).thenReturn(testManager.getPassword());
    }

    @After()
    public void afterTest() {
    }

    @Test
    public void getAllTournaments() {
        List<Tournament> allTournamentList = new ArrayList<>();
        allTournamentList.add(testTournament);
        when(tournamentService.getTournamentList()).thenReturn(allTournamentList);
        String result = controller.allTournament(model, testRequest, testResponse);
        verify(testSession).setAttribute("allTournamentList", allTournamentList);

        assertEquals(result, PAGE_ALL_AVAILABLE_TOURNAMENTS);
    }
}