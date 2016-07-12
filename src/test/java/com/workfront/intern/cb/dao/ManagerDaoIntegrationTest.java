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

    private Manager createManager() {
        manager = new Manager();
        manager.setLogin(MANAGER_LOGIN);
        manager.setPassword(MANAGER_PASSWORD);

        return manager;
    }

//    private List<Manager> createManagerList() {
//        resultList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            manager = createManager();
//            resultList.add(manager);
//        }
//        return resultList;
//    }

    @Before
    public void beforeTest() {
        manager = createManager();
        managerDao.addManager(manager);
    }

    @After
    public void afterTest() {
        managerDao.deleteManagerById(manager.getId());
        if (result != null){
            managerDao.deleteManagerById(result.getId());
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

    @Test
    public void getManagerList_emptyList() {
        //TODO
//        List<Manager> emptyList;
//        emptyList = managerDao.getManagerList();
//        emptyList.removeAll
//
//        assertEquals(MESSAGE_TEST_COMPLITE_ERROR, 0, emptyList.size());
    }

    @Test
    public void getManagerList_found() {
        List<Manager> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            resultList.add(manager);
        }
        resultList = managerDao.getManagerList();

        assertNotNull(MESSAGE_TEST_COMPLITE_ERROR, resultList);
    }

    @Test
    public void addManager_invalidData() {

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
