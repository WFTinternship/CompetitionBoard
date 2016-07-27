package com.workfront.intern.cb.dao;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
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

public class GroupDaoUnitTest extends BaseTest {
    DataSource dataSource;
    GroupDao groupDao;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        dataSource = Mockito.mock(DataSource.class);
        Connection conn = Mockito.mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        when(conn.prepareStatement(any(String.class), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(SQLException.class);

        groupDao = new GroupDaoImpl(dataSource);
    }

    @After
    public void afterTest() {
    }

    @Test(expected = FailedOperationException.class)
    public void add_dbError() throws FailedOperationException {
        groupDao.addGroup(new Group());
    }

    @Test(expected = FailedOperationException.class)
    public void getGroupByTournamentList_dbError() throws FailedOperationException {
        groupDao.getGroupByTournamentList(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void getAllGroups_dbError() throws FailedOperationException {
        groupDao.getGroupByTournamentList(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void updateGroup_dbError() throws FailedOperationException {
        Group testGroup = createRandomGroup();
        groupDao.updateGroup(NON_EXISTING_ID, testGroup);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteGroup_dbError() throws ObjectNotFoundException, FailedOperationException {
        groupDao.deleteGroup(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteAll_dbError() throws ObjectNotFoundException, FailedOperationException {
        groupDao.deleteAll();
    }
}
