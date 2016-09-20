package com.workfront.intern.cb.dao.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.common.util.StringHelper;
import com.workfront.intern.cb.dao.ManagerDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class ManagerDaoIntegrationTest extends BaseTest {

    // DAO instances
    @Autowired
    private ManagerDao managerDao;

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
        managerDao.addManager(testManager);

        // Validate ID
        assertTrue(testManager.getId() > 0);
    }

    @After()
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        managerDao.deleteAll();
    }

    // region <TEST CASES>

    @Test(expected = ObjectNotFoundException.class)
    public void getManagerById_notFound() throws Exception {
        // Testing method
        Manager manager = managerDao.getManagerById(NON_EXISTING_ID);
        assertNull(MESSAGE_TEST_COMPLETED_ERROR, manager);
    }

    @Test
    public void getManagerById_found() throws Exception {
        int targetId = testManager.getId();

        // Testing method
        Manager manager = managerDao.getManagerById(targetId);

        assertNotNull(manager);
        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(StringHelper.passToEncrypt(testManager.getPassword()), manager.getPassword());
        assertEquals(testManager.getAvatar(), manager.getAvatar());

    }

    @Test(expected = ObjectNotFoundException.class)
    public void getManagerByLogin_notFound() throws Exception {
        // Testing method
        Manager manager = managerDao.getManagerByLogin(NON_EXISTING_LOGIN);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, manager);
    }

    @Test
    public void getManagerByLogin_found() throws Exception {
        String targetLogin = testManager.getLogin();

        // Testing method
        Manager manager = managerDao.getManagerByLogin(targetLogin);

        assertNotNull(manager);
        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(StringHelper.passToEncrypt(testManager.getPassword()), manager.getPassword());
    }

    @Test
    public void getManagerList_emptyList() throws Exception {
        int targetId = testManager.getId();
        managerDao.deleteManagerById(targetId);

        // Testing method
        List<Manager> managerList = managerDao.getManagerList();

        assertNotNull(managerList);
        assertEquals(0, managerList.size());
    }

    @Test
    public void getManagerList_found() throws Exception {
        // Testing method
        List<Manager> managerList = managerDao.getManagerList();

        assertNotNull(managerList);
        assertEquals(1, managerList.size());

        Manager manager = managerList.get(0);
        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(StringHelper.passToEncrypt(testManager.getPassword()), manager.getPassword());
        assertEquals(testManager.getAvatar(), manager.getAvatar());
    }

    @Test
    public void addManager_created() throws Exception {
        // Initialize random manager instance
        Manager manager = createRandomManager();

        assertEquals(0, manager.getId());

        // Testing method
        managerDao.addManager(manager);
        assertTrue(manager.getId() > 0);
    }

    @Test
    public void updateManager() throws Exception {
        String passwordUpdate = StringHelper.passToEncrypt("updatedPassword");
        int targetId = testManager.getId();
        testManager.setPassword(passwordUpdate);

        // Testing method
        managerDao.updateManager(targetId, testManager);

        // Initialize random manager instance
        Manager manager = managerDao.getManagerById(targetId);

        assertEquals(testManager.getId(), manager.getId());
        assertEquals(testManager.getLogin(), manager.getLogin());
        assertEquals(testManager.getPassword(), manager.getPassword());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void deleteManagerById_notFound() throws Exception {
        managerDao.deleteManagerById(NON_EXISTING_ID);
    }

    @Test
    public void deleteManagerById_deleted() throws Exception {
        int targetId = testManager.getId();
        managerDao.deleteManagerById(targetId);
    }

    @Test
    public void deleteAll() throws Exception {
        managerDao.deleteAll();
        List<Manager> managerList = managerDao.getManagerList();
        assertEquals(0, managerList.size());
    }

    // endregion
}
