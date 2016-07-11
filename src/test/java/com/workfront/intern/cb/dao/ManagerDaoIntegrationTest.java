package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ManagerDaoIntegrationTest extends BaseTest {

    private ManagerDao managerDao = new ManagerDaoImpl();
    private Manager manager;

    private Manager createManager() {
        manager = new Manager();
        manager.setLogin(MANAGER_LOGIN);
        manager.setPassword(MANAGER_PASSWORD);
        return manager;
    }

    @Before
    public void beforeTest() {
        manager = createManager();
        managerDao.addManager(manager);
    }

    @After
    public void afterTest() {
        managerDao.deleteManagerById(manager.getId());
    }

    // region <TEST CASES>
    @Test
    public void getManagerById_notFound() {

    }

    @Test
    public void getManagerById_found() {
        assertNotNull(manager);
    }

    @Test
    public void getManagerByLogin_notFound() {
        assertEquals("Test is ok!", MANAGER_LOGIN, manager.getLogin());
    }
//
//    @Test
//    public void getManagerByLogin_found() {
//
//    }
//
//    @Test
//    public void getManagerList_emptyList() {
//
//    }
//
//    @Test
//    public void getManagerList_found() {
//
//    }
//
//    @Test
//    public void addManager_invalidData() {
//
//    }
//
//    @Test
//    public void addManager_created() {
//
//    }
//
//    @Test
//    public void deleteManagerById_notFound() {
//
//    }
//
//    @Test
//    public void deleteManagerById_deleted() {
//
//    }

    // endregion
}
