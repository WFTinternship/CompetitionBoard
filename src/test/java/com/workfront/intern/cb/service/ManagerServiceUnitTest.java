package com.workfront.intern.cb.service;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.ManagerDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.sql.Statement;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class ManagerServiceUnitTest extends BaseTest {
    DataSource dataSource;
    private ManagerDao managerDao;
    private Manager testManager;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        dataSource = Mockito.mock(DataSource.class);
        Connection conn = Mockito.mock(Connection.class);

        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenThrow(FailedOperationException.class);
        when(conn.prepareStatement(any(String.class), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(FailedOperationException.class);

        managerDao = new ManagerDaoImpl(dataSource);
        testManager = createRandomManager();
    }

    @After
    public void afterTest() {
    }

    @Test(expected = FailedOperationException.class)
    public void addManager_dbError() throws FailedOperationException {
        managerDao.addManager(testManager);
    }

    @Test(expected = FailedOperationException.class)
    public void getManagerById_dbError() throws FailedOperationException, ObjectNotFoundException {
        managerDao.getManagerById(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void getManagerByLogin_dbError() throws FailedOperationException, ObjectNotFoundException {
        managerDao.getManagerByLogin(NON_EXISTING_LOGIN);
    }

    @Test(expected = FailedOperationException.class)
    public void getManagerList_dbError() throws FailedOperationException, ObjectNotFoundException {
        managerDao.getManagerList();
    }

    @Test(expected = FailedOperationException.class)
    public void updateManager_dbError() throws FailedOperationException, ObjectNotFoundException {
        managerDao.updateManager(NON_EXISTING_ID, testManager);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteManagerById_dbError() throws FailedOperationException, ObjectNotFoundException {
        managerDao.deleteManagerById(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteAll_dbError() throws FailedOperationException, ObjectNotFoundException {
        managerDao.deleteAll();
    }
}