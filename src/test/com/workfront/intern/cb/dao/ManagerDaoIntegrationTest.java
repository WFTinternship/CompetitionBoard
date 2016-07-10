package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ManagerDaoIntegrationTest extends BaseTest {

    private ManagerDao managerDao = new ManagerDaoImpl();
    private Manager manager;

    private Manager createManager() {
        manager = new Manager();
        return manager;
    }

    @Before
    public void beforeTest() {
        manager = createManager();
        managerDao.addManager(manager);
    }

    @After
    public void afterTest() {
        if (manager != null) {
            managerDao.deleteManagerById(manager.getId());
        }
    }


    // region <TEST CASES>
//    @Test
//    public void getManagerById_notFound() {
//
//    }

    @Test
    public void getManagerById_found() {
        Manager manager = new ManagerDaoImpl().getManagerById(5);
        assertNotNull(".....", manager);

    }

//    @Test
//    public void getManagerByLogin_notFound() {
//
//    }
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
