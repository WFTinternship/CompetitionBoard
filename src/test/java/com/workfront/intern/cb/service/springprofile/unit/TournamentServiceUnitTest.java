package com.workfront.intern.cb.service.springprofile.unit;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.TournamentDao;
import com.workfront.intern.cb.dao.TournamentDaoImpl;
import com.workfront.intern.cb.service.TournamentService;
import com.workfront.intern.cb.service.TournamentServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
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

    // region <TEST CASES>

    @Test(expected = RuntimeException.class)
    public void addTournament_DAOError() throws Exception {
        when(tournamentDao.addTournament(testTournament)).thenThrow(FailedOperationException.class);
        tournamentService.addTournament(testTournament);
    }

    @Test()
    public void addTournament_DAOSuccess() throws Exception {
        tournamentService.addTournament(testTournament);
        verify(tournamentDao).addTournament(testTournament);
    }

    @Test(expected = RuntimeException.class)
    public void getTournamentById_DAOError() throws Exception {
        when(tournamentDao.getTournamentById(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        tournamentService.getTournamentById(NON_EXISTING_ID);
    }

    @Test()
    public void getTournamentById_DAOSuccess() throws Exception {
        tournamentService.getTournamentById(NON_EXISTING_ID);
        verify(tournamentDao).getTournamentById(NON_EXISTING_ID);
    }

    @Test()
    public void getTournamentByName_DAOSuccess() throws Exception {
        tournamentService.getTournamentListByName(TOURNAMENT_NAME);
        verify(tournamentDao).getTournamentListByName(TOURNAMENT_NAME);
    }

    @Test(expected = RuntimeException.class)
    public void getTournamentListByManager_DAOError() throws Exception {
        when(tournamentDao.getTournamentListByManager(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        tournamentService.getTournamentListByManager(NON_EXISTING_ID);
    }

    @Test()
    public void getTournamentListByManager_DAOSuccess() throws Exception {
        tournamentService.getTournamentListByManager(NON_EXISTING_ID);
        verify(tournamentDao).getTournamentListByManager(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getTournamentList_DAOError() throws Exception {
        when(tournamentDao.getTournamentList()).thenThrow(FailedOperationException.class);
        tournamentService.getTournamentList();
    }

    @Test()
    public void getTournamentList_DAOSuccess() throws Exception {
        tournamentService.getTournamentList();
        verify(tournamentDao).getTournamentList();
    }

    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void updateTournament_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(tournamentDao).updateTournament(NON_EXISTING_ID, testTournament);
        tournamentService.updateTournament(NON_EXISTING_ID, testTournament);
    }

    @Test()
    public void updateTournament_DAOSuccess() throws Exception {
        tournamentService.updateTournament(NON_EXISTING_ID, testTournament);
        verify(tournamentDao).updateTournament(NON_EXISTING_ID, testTournament);
    }

    @Test(expected = RuntimeException.class)
    public void deleteTournamentById_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(tournamentDao).deleteTournamentById(NON_EXISTING_ID);
        tournamentService.deleteTournamentById(NON_EXISTING_ID);
    }

    @Test()
    public void deleteTournamentById_DAOSuccess() throws Exception {
        tournamentService.deleteTournamentById(NON_EXISTING_ID);
        verify(tournamentDao).deleteTournamentById(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void deleteAll_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(tournamentDao).deleteAll();
        tournamentService.deleteAll();
    }

    @Test()
    public void deleteAll_DAOSuccess() throws Exception {
        tournamentService.deleteAll();
        verify(tournamentDao).deleteAll();
    }

    // endregion
}