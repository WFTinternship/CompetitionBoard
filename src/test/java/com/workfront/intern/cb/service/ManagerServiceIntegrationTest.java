package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.DBManager;
import com.workfront.intern.cb.dao.ManagerDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

public class ManagerServiceIntegrationTest extends BaseTest{

    // DAO instances
    private ManagerDao managerDao;

    // Test helper objects
    private Manager testManager;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() throws FailedOperationException, ObjectNotFoundException {
    }

    @After()
    public void afterTest() throws FailedOperationException, ObjectNotFoundException {
        // Deleting 'manager' of manager type field after passed test
        if (testManager != null) {
            managerDao.deleteManagerById(testManager.getId());
        } else {
            System.out.println("WARNING: testManager was null after test execution");
        }
    }

    // region <TEST CASES>

    @Test
    public void getManagerById_notFound() throws Exception {

    }

    @Test
    public void getManagerById_found() throws FailedOperationException, ObjectNotFoundException {

    }

    @Test
    public void getManagerByLogin_notFound() throws FailedOperationException, ObjectNotFoundException {
    }

    @Test
    public void getManagerByLogin_found() throws FailedOperationException, ObjectNotFoundException {

    }

    @Test
    public void getManagerList_emptyList() throws FailedOperationException, ObjectNotFoundException {

    }

    @Test
    public void getManagerList_found() throws FailedOperationException, ObjectNotFoundException {

    }

    @Test
    public void addManager_created() throws FailedOperationException, ObjectNotFoundException {

    }

    @Test
    public void updateManager() throws FailedOperationException, ObjectNotFoundException {

    }

    @Test
    public void deleteManagerById_notFound() throws FailedOperationException, ObjectNotFoundException {
    }

    @Test
    public void deleteManagerById_deleted() throws FailedOperationException, ObjectNotFoundException {
    }

    @Test
    public void deleteAll() throws FailedOperationException, ObjectNotFoundException {
    }

    // endregion
}