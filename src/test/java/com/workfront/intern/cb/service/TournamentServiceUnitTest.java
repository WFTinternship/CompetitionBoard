package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.TournamentDao;
import com.workfront.intern.cb.dao.TournamentDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class TournamentServiceUnitTest extends BaseTest {
    protected DataSource dataSource;
    private TournamentDao tournamentDao;
    private Tournament testTournament;
    private TournamentService tournamentService;

    @Before
    public void beforeTest() throws Exception {
        tournamentDao = Mockito.mock(TournamentDaoImpl.class);
        tournamentService = new TournamentServiceImpl();
        Whitebox.setInternalState(tournamentService, "tournamentDao", tournamentDao);

        testTournament = createRandomTournament();
    }

    @After
    public void afterTest() {
    }

    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void addTournament_DAOError() throws Exception {
        when(tournamentDao.addTournament(testTournament)).thenThrow(FailedOperationException.class);
        tournamentService.addTournament(testTournament);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void getTournamentById_DAOError() throws Exception {
        when(tournamentDao.getTournamentById(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        tournamentService.getTournamentById(NON_EXISTING_ID);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void getTournamentListByManager_DAOError() throws Exception {
        when(tournamentDao.getTournamentListByManager(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        tournamentService.getTournamentListByManager(NON_EXISTING_ID);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void getTournamentList_DAOError() throws Exception {
        when(tournamentDao.getTournamentList()).thenThrow(FailedOperationException.class);
        tournamentService.getTournamentList();
    }

    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void updateTournament_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(tournamentDao).updateTournament(NON_EXISTING_ID, testTournament);
        tournamentService.updateTournament(NON_EXISTING_ID, testTournament);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void deleteTournamentById_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(tournamentDao).deleteTournamentById(NON_EXISTING_ID);
        tournamentService.deleteTournamentById(NON_EXISTING_ID);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void deleteAll_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(tournamentDao).deleteAll();
        tournamentService.deleteAll();
    }
}
