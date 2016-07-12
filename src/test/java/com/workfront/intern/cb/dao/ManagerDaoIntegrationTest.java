package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerDaoIntegrationTest extends BaseTest {

    private ManagerDao managerDao = new ManagerDaoImpl();
    private Manager manager;
    private Manager result;

    private List<Manager> managerList;

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
        // Deleting 'manager' of manager type field after passed test
        managerDao.deleteManagerById(manager.getId());

        // Deleting 'result' of manager type field after passed test
        if (result != null) {
            managerDao.deleteManagerById(result.getId());
        }

        if (managerList != null) {
            int m = managerList.size();
            for (int i = result.getId(); i < m; i++) {
                managerDao.deleteManagerById(i);
            }
        }
    }

    // region <TEST CASES>
    @Test
    public void getManagerById_notFound() {
        result = managerDao.getManagerById(NON_EXISTING_ID);
        assertNull(MESSAGE_TEST_COMPLITE_ERROR, result);
    }

    @Test
    public void getManagerById_found() {
        assertNotNull(manager.getId());
    }

    @Test
    public void getManagerByLogin_notFound() {
        result = managerDao.getManagerByLogin(MANAGER_NON_EXISTING_LOGIN);
        assertNull(MESSAGE_TEST_COMPLITE_ERROR, result);
    }

    @Test
    public void getManagerByLogin_found() {
        assertNotNull(manager.getLogin());
    }

//    @Test
//    public void getManagerList_emptyList() {
//        resultList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            resultList.add(manager);
//        }
//    }

    @Test
    public void getManagerList_found() {
        managerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result = new Manager();
            result.setLogin(MANAGER_LOGIN + i);
            result.setPassword(MANAGER_PASSWORD + i);
            managerDao.addManager(result);
        }
        managerList = managerDao.getManagerList();
        assertNotNull(MESSAGE_TEST_COMPLITE_ERROR, managerList);
    }

    @Test
    public void addManager_created() {
        result = new Manager();
        result.setLogin(MANAGER_LOGIN);
        result.setPassword(MANAGER_PASSWORD);
        boolean added = managerDao.addManager(result);
        assertTrue(added);
    }

    @Test
    public void deleteManagerById_notFound() {
        boolean deleted = managerDao.deleteManagerById(NON_EXISTING_ID);
        assertFalse(deleted);
    }

    @Test
    public void deleteManagerById_deleted() {
        boolean deleted = managerDao.deleteManagerById(manager.getId());
        assertTrue(deleted);
    }
    // endregion
}
