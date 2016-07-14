package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.util.StringHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ManagerDaoIntegrationTest extends BaseTest {

    private ManagerDao managerDao = new ManagerDaoImpl();
    private Manager testManager;

    @Before
    public void beforeTest() {
        // Delete all remaining objects
        managerDao.deleteAll();

        // Initialize random manager instance
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to database
        managerDao.addManager(testManager);

        // Validate ID
        assertTrue(testManager.getId() > 0);
    }

    @After
    public void afterTest() {
        // Deleting 'manager' of manager type field after passed test
        if (testManager != null) {
            managerDao.deleteManagerById(testManager.getId());
        } else {
            System.out.println("WARNING: testManager was null after test execution");
        }
    }

    // region <TEST CASES>

    @Test
    public void getManagerById_notFound() {
        // Testing method
        Manager manager = managerDao.getManagerById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, manager);
    }

    @Test
    public void getManagerById_found() {
        int targetId = testManager.getId();

        // Testing method
        Manager manager = managerDao.getManagerById(targetId);

        assertNotNull(manager);
        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(StringHelper.passToEncrypt(testManager.getPassword()), manager.getPassword());
    }

    @Test
    public void getManagerByLogin_notFound() {
        // Testing method
        Manager manager = managerDao.getManagerByLogin(NON_EXISTING_LOGIN);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, manager);
    }

    @Test
    public void getManagerByLogin_found() {
        String targetLogin = testManager.getLogin();

        // Testing method
        Manager manager = managerDao.getManagerByLogin(targetLogin);

        assertNotNull(manager);
        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(StringHelper.passToEncrypt(testManager.getPassword()), manager.getPassword());
    }

    @Test
    public void getManagerList_emptyList() {
        boolean deleted = managerDao.deleteManagerById(testManager.getId());
        assertTrue(deleted);

        // Testing method
        List<Manager> managerList = managerDao.getManagerList();

        assertNotNull(managerList);
        assertEquals(0, managerList.size());
    }

    @Test
    public void getManagerList_found() {
        // Testing method
        List<Manager> managerList = managerDao.getManagerList();

        assertNotNull(managerList);
        assertEquals(1, managerList.size());

        Manager manager = managerList.get(0);
        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(StringHelper.passToEncrypt(testManager.getPassword()), manager.getPassword());
    }

    @Test
    public void addManager_created() {
        // Initialize random manager instance
        Manager manager = createRandomManager();
        assertEquals(0, manager.getId());

        // Testing method
        boolean added = managerDao.addManager(manager);

        assertTrue(added);
        assertTrue(manager.getId() > 0);
    }

    @Test
    public void updateManager() {
    }

    @Test
    public void deleteManagerById_notFound() {
        boolean deleted = managerDao.deleteManagerById(NON_EXISTING_ID);

        assertFalse(deleted);
    }

    @Test
    public void deleteManagerById_deleted() {
        boolean deleted = managerDao.deleteManagerById(testManager.getId());

        assertTrue(deleted);
    }

    @Test
    public void deleteAll() {
        //TODO
    }

    // endregion
}
