package com.workfront.intern.cb.dao;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Match;
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

public class MatchDaoUnitTest extends BaseTest{
    DataSource dataSource;
    MatchDao matchDao;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        dataSource = Mockito.mock(DataSource.class);
        Connection conn = Mockito.mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        when(conn.prepareStatement(any(String.class), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(SQLException.class);

        matchDao = new MatchDaoImpl(dataSource);
    }

    @After
    public void afterTest() {
    }

    @Test
    public void add_dbError() throws FailedOperationException {
        matchDao.addMatch(new Match());
    }

    @Test
    public void getMatchById_dbError() throws ObjectNotFoundException, FailedOperationException {
        matchDao.getMatchById(NON_EXISTING_ID);
    }

    @Test
    public void getMatchByGroupId_dbError() throws ObjectNotFoundException, FailedOperationException {
        matchDao.getMatchByGroupId(NON_EXISTING_ID);
    }

    @Test
    public void getMatchListByGroup_dbError() {
    }

    @Test
    public void updateMatch_dbError() throws FailedOperationException {
        Match testMatch = createRandomMatch();
        matchDao.updateMatch(NON_EXISTING_ID, testMatch);
    }

    @Test
    public void deleteMatch() throws Exception {
        matchDao.deleteMatch(NON_EXISTING_ID);
    }

    @Test
    public void deleteAll() throws Exception {
        matchDao.deleteAll();
    }
}

