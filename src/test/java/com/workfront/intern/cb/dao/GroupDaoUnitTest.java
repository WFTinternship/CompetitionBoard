package com.workfront.intern.cb.dao;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Group;
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

    @Test
    public void add_dbError() {
        groupDao.addGroup(new Group());
    }

    @Test
    public void getGroupByTournamentList_dbError() {
        groupDao.getGroupByTournamentList(NON_EXISTING_ID);
    }

    @Test
    public void getAllGroups_dbError() {
        groupDao.getGroupByTournamentList(NON_EXISTING_ID);
    }

    @Test
    public void updateGroup_dbError() {
        Group testGroup = createRandomGroup();
        groupDao.updateGroup(NON_EXISTING_ID, testGroup);
    }

    @Test
    public void deleteGroup_dbError() {
        groupDao.deleteGroup(NON_EXISTING_ID);
    }

    @Test
    public void deleteAll_dbError() {
        groupDao.deleteAll();
    }
}
