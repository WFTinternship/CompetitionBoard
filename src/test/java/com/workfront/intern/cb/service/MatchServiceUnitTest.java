package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.MatchDao;
import com.workfront.intern.cb.dao.MatchDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class MatchServiceUnitTest extends BaseTest {
    protected DataSource dataSource;
    private MatchDao matchDao;
    private Match testMatch;
    private MatchService matchService;

    @Before
    public void beforeTest() throws Exception {
        matchDao = Mockito.mock(MatchDaoImpl.class);
        matchService = new MatchServiceImpl();
        Whitebox.setInternalState(matchService, "matchDao", matchDao);

        testMatch = createRandomMatch();
    }

    @After
    public void afterTest() {
    }

    @Test(expected = RuntimeException.class)
    public void addMatch_DAOError() throws Exception {
        when(matchDao.addMatch(testMatch)).thenThrow(FailedOperationException.class);
        matchService.addMatch(testMatch);
    }

    @Test(expected = RuntimeException.class)
    public void getMatchById_DAOError() throws Exception {
        when(matchDao.getMatchById(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        matchService.getMatchById(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getMatchByGroupId_DAOError() throws Exception {
        when(matchDao.getMatchByGroupId(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        matchService.getMatchByGroupId(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getMatchListByGroup_DAOError() throws Exception {
        when(matchDao.getMatchListByGroup(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        matchService.getMatchListByGroup(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void updateMatch_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(matchDao).updateMatch(NON_EXISTING_ID, testMatch);
        matchService.updateMatch(NON_EXISTING_ID, testMatch);
    }

    @Test(expected = RuntimeException.class)
    public void deleteMatchById_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(matchDao).deleteMatch(NON_EXISTING_ID);
        matchService.deleteMatch(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void deleteAll_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(matchDao).deleteAll();
        matchService.deleteAll();
    }
}