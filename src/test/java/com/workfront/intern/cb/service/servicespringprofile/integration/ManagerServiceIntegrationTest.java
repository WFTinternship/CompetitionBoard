package com.workfront.intern.cb.service.servicespringprofile.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.util.StringHelper;
import com.workfront.intern.cb.service.ManagerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class ManagerServiceIntegrationTest extends BaseTest {

    @Autowired
    private ManagerService managerService;

    // Test helper objects
    private Manager testManager;

    @Before
    public void beforeTest() throws Exception {
        // Delete all remaining objects
        cleanUp();

        // Initialize random manager instance
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to database
        managerService.addManager(testManager);

        // Validate ID
        assertTrue(testManager.getId() > 0);
    }

    @After()
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        managerService.deleteAll();
    }

    // region <TEST CASES>

    @Test(expected = RuntimeException.class)
    public void getManagerById_notFound() throws Exception {
        // Testing method
        Manager manager = managerService.getManagerById(NON_EXISTING_ID);
        assertNull(MESSAGE_TEST_COMPLETED_ERROR, manager);
    }

    @Test
    public void getManagerById_found() throws Exception {
        int targetId = testManager.getId();

        // Testing method
        Manager manager = managerService.getManagerById(targetId);
        assertNotNull(manager);
    }

    @Test(expected = RuntimeException.class)
    public void getManagerByLogin_notFound() throws Exception {
        // Testing method
        Manager manager = managerService.getManagerByLogin(NON_EXISTING_LOGIN);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, manager);
    }

    @Test
    public void getManagerByLogin_found() throws Exception {
        String targetLogin;
        targetLogin = testManager.getLogin();

        // Testing method
        Manager manager = managerService.getManagerByLogin(targetLogin);

        assertNotNull(manager);
        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(StringHelper.passToEncrypt(testManager.getPassword()), manager.getPassword());
        assertEquals(testManager.getAvatar(), manager.getAvatar());
    }

    public void getManagerList_emptyList() throws Exception {
        int targetId;
        targetId = testManager.getId();
        managerService.deleteManagerById(targetId);

        // Testing method
        List<Manager> managerList = managerService.getManagerList();

        assertNotNull(managerList);
        assertEquals(0, managerList.size());
    }

    @Test
    public void getManagerList_found() throws Exception {
        // Testing method
        List<Manager> managerList;
        managerList = managerService.getManagerList();

        assertNotNull(managerList);
        assertEquals(1, managerList.size());

        Manager manager = managerList.get(0);
        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(StringHelper.passToEncrypt(testManager.getPassword()), manager.getPassword());
    }

    @Test
    public void addManager_created() throws Exception {
        // Initialize random manager instance
        Manager manager = createRandomManager();

        assertEquals(0, manager.getId());

        // Testing method
        managerService.addManager(manager);
        assertTrue(manager.getId() > 0);
    }

    @Test
    public void updateManager() throws Exception {
        int targetId;
        targetId = testManager.getId();
        String passwordUpdate = StringHelper.passToEncrypt("updatedPassword");
        testManager.setPassword(passwordUpdate);

        // Testing method
        managerService.updateManager(targetId, testManager);

        // Initialize random manager instance
        Manager manager = managerService.getManagerById(targetId);

        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(testManager.getPassword(), manager.getPassword());
        assertEquals(testManager.getAvatar(), manager.getAvatar());
    }

    @Test(expected = RuntimeException.class)
    public void deleteManagerById_notFound() throws Exception {
        managerService.deleteManagerById(NON_EXISTING_ID);
    }

    @Test
    public void deleteManagerById_deleted() throws Exception {
        int targetId = testManager.getId();
        managerService.deleteManagerById(targetId);
    }

    @Test
    public void deleteAll() throws Exception {
        managerService.deleteAll();
        List<Manager> managerList = managerService.getManagerList();
        assertEquals(0, managerList.size());
    }

    // endregion
}