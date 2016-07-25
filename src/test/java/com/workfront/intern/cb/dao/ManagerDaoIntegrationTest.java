package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.common.util.StringHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerDaoIntegrationTest extends BaseTest {

    // DAO instances
    private ManagerDao managerDao;

    // Test helper objects
    private Manager testManager;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() throws FailedOperationException, ObjectNotFoundException {
        managerDao = new ManagerDaoImpl(dataSource);

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
    public void afterTest() throws FailedOperationException, ObjectNotFoundException {
        // Deleting 'manager' of manager type field after passed test
        if (testManager != null) {
            managerDao.deleteManagerById(testManager.getId());
        } else {
            System.out.println("WARNING: testManager was null after test execution");
        }
    }

    // region <TEST CASES>

    @Test(expected = ObjectNotFoundException.class)
    public void getManagerById_notFound() throws Exception {
        // Testing method
        Manager manager = managerDao.getManagerById(NON_EXISTING_ID);
        assertNull(MESSAGE_TEST_COMPLETED_ERROR, manager);
    }

    @Test
    public void getManagerById_found() throws FailedOperationException, ObjectNotFoundException {
        int targetId = testManager.getId();

        // Testing method
        Manager manager = managerDao.getManagerById(targetId);

        assertNotNull(manager);
        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(StringHelper.passToEncrypt(testManager.getPassword()), manager.getPassword());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void getManagerByLogin_notFound() throws FailedOperationException, ObjectNotFoundException {
        // Testing method
        Manager manager = managerDao.getManagerByLogin(NON_EXISTING_LOGIN);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, manager);
    }

    @Test
    public void getManagerByLogin_found() throws FailedOperationException, ObjectNotFoundException {
        String targetLogin = testManager.getLogin();

        // Testing method
        Manager manager = managerDao.getManagerByLogin(targetLogin);

        assertNotNull(manager);
        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(StringHelper.passToEncrypt(testManager.getPassword()), manager.getPassword());
    }

    @Test
    public void getManagerList_emptyList() throws FailedOperationException, ObjectNotFoundException {
        managerDao.deleteManagerById(testManager.getId());

        // Testing method
        List<Manager> managerList = managerDao.getManagerList();

        assertNotNull(managerList);
        assertEquals(0, managerList.size());
    }

    @Test
    public void getManagerList_found() throws FailedOperationException, ObjectNotFoundException {
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
    public void addManager_created() throws FailedOperationException, ObjectNotFoundException  {
        // Initialize random manager instance
        Manager manager = createRandomManager();
        assertEquals(0, manager.getId());

        // Testing method
        managerDao.addManager(manager);
        assertTrue(manager.getId() > 0);
    }

    @Test
    public void updateManager() throws FailedOperationException, ObjectNotFoundException {
        // Testing method
        String passwordUpdate = StringHelper.passToEncrypt("updatedPassword");
        int targetId = testManager.getId();
        testManager.setPassword(passwordUpdate);

        managerDao.updateManager(targetId, testManager);

        // Initialize random manager instance
        Manager manager = managerDao.getManagerById(targetId);

        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(testManager.getPassword(), manager.getPassword());
    }

    @Test
    public void deleteManagerById_notFound() throws FailedOperationException, ObjectNotFoundException {
        managerDao.deleteManagerById(NON_EXISTING_ID);

//        assertFalse(deleted);
    }

    @Test
    public void deleteManagerById_deleted() throws FailedOperationException, ObjectNotFoundException {
        managerDao.deleteManagerById(testManager.getId());

//        assertTrue(deleted);
    }

    @Test
    public void deleteAll() throws FailedOperationException, ObjectNotFoundException {
        managerDao.deleteAll();

        List<Manager> managerList = managerDao.getManagerList();
        assertEquals(0, managerList.size());
    }
    // endregion
}
