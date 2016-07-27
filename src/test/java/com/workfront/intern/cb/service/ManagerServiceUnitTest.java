package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.ManagerDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;

import static org.mockito.Mockito.when;

public class ManagerServiceUnitTest extends BaseTest {
    protected DataSource dataSource;
    private ManagerDao managerDao;
    private Manager testManager;
    private ManagerService managerService;

    @Before
    public void beforeTest() throws Exception {
//        dataSource = Mockito.mock(DataSource.class);
        managerDao = Mockito.mock(ManagerDaoImpl.class);

        managerService = new ManagerServiceImpl();
        Whitebox.setInternalState(managerService, "managerDao", managerDao);

        testManager = createRandomManager();
    }

    @After
    public void afterTest() {
    }

    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void addManager_DAOError() throws Exception {
        when(managerDao.addManager(testManager)).thenThrow(FailedOperationException.class);
        managerService.addManager(testManager);
    }

    @Ignore
    @Test(expected = FailedOperationException.class)
    public void getManagerById_dbError() throws Exception {
        managerDao.getManagerById(NON_EXISTING_ID);
    }

    @Ignore
    @Test(expected = FailedOperationException.class)
    public void getManagerByLogin_dbError() throws Exception {
        managerDao.getManagerByLogin(NON_EXISTING_LOGIN);
    }

    @Ignore
    @Test(expected = FailedOperationException.class)
    public void getManagerList_dbError() throws Exception {
        managerDao.getManagerList();
    }

    @Ignore
    @Test(expected = FailedOperationException.class)
    public void updateManager_dbError() throws Exception {
        managerDao.updateManager(NON_EXISTING_ID, testManager);
    }

    @Ignore
    @Test(expected = FailedOperationException.class)
    public void deleteManagerById_dbError() throws Exception {
        managerDao.deleteManagerById(NON_EXISTING_ID);
    }

    @Ignore
    @Test(expected = FailedOperationException.class)
    public void deleteAll_dbError() throws Exception {
        managerDao.deleteAll();
    }
}