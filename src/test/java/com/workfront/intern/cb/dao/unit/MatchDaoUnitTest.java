package com.workfront.intern.cb.dao.unit;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.MatchDao;
import com.workfront.intern.cb.dao.MatchDaoImpl;
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
    private MatchDao matchDao;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        DataSource dataSource = Mockito.mock(DataSource.class);
        Connection conn = Mockito.mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        when(conn.prepareStatement(any(String.class), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(SQLException.class);

        matchDao = new MatchDaoImpl(dataSource);
    }

    @After
    public void afterTest() {
    }

    @Test(expected = FailedOperationException.class)
    public void add_dbError() throws Exception {
        matchDao.addMatch(new Match());
    }

    @Test(expected = FailedOperationException.class)
    public void getMatchById_dbError() throws Exception {
        matchDao.getMatchById(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void getMatchByGroupId_dbError() throws Exception {
        matchDao.getMatchByGroupId(NON_EXISTING_ID);
    }

    @Test
    public void getMatchListByGroup_dbError() {
    }

    @Test(expected = FailedOperationException.class)
    public void updateMatch_dbError() throws Exception {
        Match testMatch = createRandomMatch();
        matchDao.updateMatch(NON_EXISTING_ID, testMatch);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteMatch() throws Exception {
        matchDao.deleteMatch(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteAll() throws Exception {
        matchDao.deleteAll();
    }
}

