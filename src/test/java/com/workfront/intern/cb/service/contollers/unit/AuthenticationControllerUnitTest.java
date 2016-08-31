package com.workfront.intern.cb.service.contollers.unit;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.controller.AuthenticationController;
import com.workfront.intern.cb.service.ManagerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AuthenticationControllerUnitTest extends BaseTest {

    private static AuthenticationController controller;

    private ManagerService managerService;
    private Manager testManager;

    private HttpServletRequest testRequest;
    private HttpServletResponse testResponse;
    private HttpSession testSession;
    private HttpServletResponse response;
    private Model model;

    @Before
    public void beforeTest() {
        controller = new AuthenticationController();

        testManager = createRandomManager();
        managerService = mock(ManagerService.class);
        Whitebox.setInternalState(controller, "managerService", managerService);

        testRequest = mock(HttpServletRequest.class);
        testResponse = mock(HttpServletResponse.class);
        testSession = mock(HttpSession.class);
        response = mock(HttpServletResponse.class);
        model = mock(Model.class);

        when(testRequest.getParameter("usernameLogin")).thenReturn(testManager.getLogin());
        when(testRequest.getParameter("passwordLogin")).thenReturn(testManager.getPassword());
    }

    @After()
    public void afterTest() {
        controller = null;
        testManager = null;
        managerService = null;
    }

    public void login_success() throws Exception {
        when(managerService.getManagerByLogin(testManager.getLogin())).thenReturn(testManager);

        String result = controller.logIn(model, testRequest.getParameter("usernameLogin"),
                                                testRequest.getParameter("passwordLogin"),
                                                testRequest, testResponse);

        verify(testSession).setAttribute("testManager", testManager);
        assertEquals(result, "/");


    }

    @Test
    public void logout_success() {
        String redirectPage = controller.toLogOutPage(model, testRequest, testResponse);
        assertEquals(redirectPage, "redirect:/");
        verify(testSession).setAttribute("manager", null);
        verify(testSession).invalidate();
    }
}
