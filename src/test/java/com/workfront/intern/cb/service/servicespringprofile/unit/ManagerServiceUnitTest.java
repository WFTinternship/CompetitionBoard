package com.workfront.intern.cb.service.servicespringprofile.unit;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.ManagerDaoImpl;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.ManagerServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;

import static org.mockito.Mockito.*;

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

    @Test()
    public void addManager_DAOSuccess() throws Exception {
        managerService.addManager(testManager);
        verify(managerDao).addManager(testManager);
    }

    @Test(expected = RuntimeException.class)
    public void getManagerById_DAOError() throws Exception {
        when(managerDao.getManagerById(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        managerService.getManagerById(NON_EXISTING_ID);
    }

    @Test()
    public void getManagerById_DAOSuccess() throws Exception {
        managerService.getManagerById(NON_EXISTING_ID);
        verify(managerDao).getManagerById(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getManagerByLogin_DAOError() throws Exception {
        when(managerDao.getManagerByLogin(NON_EXISTING_LOGIN)).thenThrow(FailedOperationException.class);
        managerService.getManagerByLogin(NON_EXISTING_LOGIN);
    }

    @Test()
    public void getManagerByLogin_DAOSuccess() throws Exception {
        managerService.getManagerByLogin(NON_EXISTING_LOGIN);
        verify(managerDao).getManagerByLogin(NON_EXISTING_LOGIN);
    }

    @Test(expected = RuntimeException.class)
    public void getManagerList_DAOError() throws Exception {
        when(managerDao.getManagerList()).thenThrow(FailedOperationException.class);
        managerService.getManagerList();
    }

    @Test()
    public void getManagerList_DAOSuccess() throws Exception {
        managerService.getManagerList();
        verify(managerDao).getManagerList();
    }

    @Test(expected = RuntimeException.class)
    public void updateManager_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(managerDao).updateManager(NON_EXISTING_ID, testManager);
        managerService.updateManager(NON_EXISTING_ID, testManager);
    }

    @Test()
    public void updateManager_DAOSuccess() throws Exception {
        managerService.updateManager(NON_EXISTING_ID, testManager);
        verify(managerDao).updateManager(NON_EXISTING_ID, testManager);
    }


    @Test(expected = RuntimeException.class)
    public void deleteManagerById_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(managerDao).deleteManagerById(NON_EXISTING_ID);
        managerService.deleteManagerById(NON_EXISTING_ID);
    }

    @Test()
    public void deleteManagerById_DAOSuccess() throws Exception {
        managerService.deleteManagerById(NON_EXISTING_ID);
        verify(managerDao).deleteManagerById(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void deleteAll_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(managerDao).deleteAll();
        managerService.deleteAll();
    }

    @Test()
    public void deleteAll_DAOSuccess() throws Exception {
        managerService.deleteAll();
        verify(managerDao).deleteAll();
    }

    // endregion
}