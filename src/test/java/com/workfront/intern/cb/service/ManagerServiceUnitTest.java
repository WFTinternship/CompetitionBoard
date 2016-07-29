package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.ManagerDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ManagerServiceUnitTest extends BaseTest {
    protected DataSource dataSource;
    private ManagerDao managerDao;
    private Manager testManager;
    private ManagerService managerService;

    @Before
    public void beforeTest() throws Exception {
        managerDao = Mockito.mock(ManagerDaoImpl.class);
        managerService = new ManagerServiceImpl();
        Whitebox.setInternalState(managerService, "managerDao", managerDao);

        testManager = createRandomManager();
    }

    @After
    public void afterTest() {
    }

    // region <TEST CASES>

    @Test(expected = RuntimeException.class)
    public void addManager_DAOError() throws Exception {
        when(managerDao.addManager(testManager)).thenThrow(FailedOperationException.class);
        managerService.addManager(testManager);
    }

    @Test(expected = RuntimeException.class)
    public void getManagerById_DAOError() throws Exception {
        when(managerDao.getManagerById(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        managerService.getManagerById(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getManagerByLogin_DAOError() throws Exception {
        when(managerDao.getManagerByLogin(NON_EXISTING_LOGIN)).thenThrow(FailedOperationException.class);
        managerService.getManagerByLogin(NON_EXISTING_LOGIN);
    }

    @Test(expected = RuntimeException.class)
    public void getManagerList_DAOError() throws Exception {
        when(managerDao.getManagerList()).thenThrow(FailedOperationException.class);
        managerService.getManagerList();
    }

    @Test(expected = RuntimeException.class)
    public void updateManager_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(managerDao).updateManager(NON_EXISTING_ID, testManager);
        managerService.updateManager(NON_EXISTING_ID, testManager);
    }

    @Test(expected = RuntimeException.class)
    public void deleteManagerById_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(managerDao).deleteManagerById(NON_EXISTING_ID);
        managerService.deleteManagerById(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void deleteAll_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(managerDao).deleteAll();
        managerService.deleteAll();
    }

    // endregion
}