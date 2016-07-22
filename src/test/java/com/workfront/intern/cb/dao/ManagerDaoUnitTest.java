package com.workfront.intern.cb.dao;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
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
    DataSource dataSource;
    ManagerDao managerDao;
    Manager testManager;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        dataSource = Mockito.mock(DataSource.class);
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

    @Test
    public void addManager_dbError() {
        managerDao.addManager(testManager);
    }

    @Test
    public void getManagerById_dbError() {
        managerDao.getManagerById(NON_EXISTING_ID);
    }

    @Test
    public void getManagerByLogin_dbError() {
        managerDao.getManagerByLogin(NON_EXISTING_LOGIN);
    }

    @Test
    public void getManagerList_dbError() {
        managerDao.getManagerList();
    }

    @Test
    public void updateManager_dbError() {
        managerDao.updateManager(NON_EXISTING_ID, testManager);
    }

    @Test
    public void deleteManagerById_dbError() {
        managerDao.deleteManagerById(NON_EXISTING_ID);
    }


    @Test
    public void deleteAll_dbError() {
        managerDao.deleteAll();
    }
}
