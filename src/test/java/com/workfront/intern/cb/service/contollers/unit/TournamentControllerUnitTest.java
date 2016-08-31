package com.workfront.intern.cb.service.contollers.unit;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.controller.TournamentController;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.TournamentService;
import org.junit.After;
import org.junit.Before;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TournamentControllerUnitTest extends BaseTest{

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
        managerService = mock(ManagerService.class);
        Whitebox.setInternalState(controller, "managerService", managerService);

        testRequest = mock(HttpServletRequest.class);
        testResponse = mock(HttpServletResponse.class);
        testSession = mock(HttpSession.class);
        response = mock(HttpServletResponse.class);
        model = mock(Model.class);

        when(testRequest.getSession()).thenReturn(testSession);
    }

    @After()
    public void afterTest() {
        controller = null;
        testManager = null;
        managerService = null;
    }

}
