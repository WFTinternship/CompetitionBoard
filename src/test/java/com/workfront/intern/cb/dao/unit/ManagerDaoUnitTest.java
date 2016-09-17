package com.workfront.intern.cb.dao.unit;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.ManagerDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class ManagerDaoUnitTest extends BaseTest {
    private ManagerDao managerDao;
    private Manager testManager;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        DataSource dataSource = Mockito.mock(DataSource.class);
        Connection conn = Mockito.mock(Connection.class);

        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        when(conn.prepareStatement(any(String.class), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(SQLException.class);

        managerDao = new ManagerDaoImpl(dataSource);
        testManager = createRandomManager();
    }

    @After
    public void afterTest() {
    }

    @Test(expected = FailedOperationException.class)
    public void addManager_dbError() throws Exception {
        managerDao.addManager(testManager);
    }

    @Test(expected = FailedOperationException.class)
    public void getManagerById_dbError() throws Exception {
        managerDao.getManagerById(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void getManagerByLogin_dbError() throws Exception {
        managerDao.getManagerByLogin(NON_EXISTING_LOGIN);
    }

    @Test(expected = FailedOperationException.class)
    public void getManagerList_dbError() throws Exception {
        managerDao.getManagerList();
    }

    @Test(expected = FailedOperationException.class)
    public void updateManager_dbError() throws Exception {
        managerDao.updateManager(NON_EXISTING_ID, testManager);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteManagerById_dbError() throws Exception {
        managerDao.deleteManagerById(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteAll_dbError() throws Exception {
        managerDao.deleteAll();
    }
}