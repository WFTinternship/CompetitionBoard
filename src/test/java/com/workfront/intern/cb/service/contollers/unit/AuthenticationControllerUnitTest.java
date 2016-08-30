//package com.workfront.intern.cb.service.contollers.unit;
//
//import com.workfront.intern.cb.BaseTest;
//import com.workfront.intern.cb.common.Manager;
//import com.workfront.intern.cb.controller.AuthenticationController;
//import com.workfront.intern.cb.service.ManagerService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.internal.util.reflection.Whitebox;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import static org.mockito.Mockito.*;
//
//public class AuthenticationControllerUnitTest extends BaseTest {
//
//    private static AuthenticationController controller;
//
//    private ManagerService managerService;
//    private Manager testManager;
//    private HttpServletRequest testRequest;
//    private HttpSession testSession;
//    private HttpServletResponse response;
//
//    @Before
//    public void beforeTest() {
//        controller = new AuthenticationController();
//
//        testManager = createRandomManager();
//        managerService = mock(ManagerService.class);
//        Whitebox.setInternalState(controller, "managerService", managerService);
//
//        testRequest = mock(HttpServletRequest.class);
//        testSession = mock(HttpSession.class);
//        response = mock(HttpServletResponse.class);
//
//        when(testRequest.getParameter("usernameLogin")).thenReturn("admin");
//        when(testRequest.getParameter("passwordLogin")).thenReturn("123");
//        when(testRequest.getSession()).thenReturn(testSession);
//    }
//
//    @After()
//    public void afterTest() {
//        controller = null;
//        testManager = null;
//        managerService = null;
//    }
//
//    @Test()
//    public void Success() throws Exception {
//        managerService.addManager(testManager);
//        verify(managerService).addManager(testManager);
//
//        when(managerService.addManager(anyObject())).thenReturn(testManager);
//
//        JsonResponse response = userController.login(testRequest);
//
//        assertEquals("status is incorrect", response.getStatus(), ACTION_SUCCESS);
//        verify(testSession).setAttribute("user", testUser)
//    }
//
//}
