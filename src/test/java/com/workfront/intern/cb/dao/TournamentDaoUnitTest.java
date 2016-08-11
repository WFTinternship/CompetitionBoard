package com.workfront.intern.cb.dao;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
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

public class TournamentDaoUnitTest extends BaseTest {
    DataSource dataSource;
    TournamentDao tournamentDao;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        dataSource = Mockito.mock(DataSource.class);
        Connection conn = Mockito.mock(Connection.class);

        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        when(conn.prepareStatement(any(String.class), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(SQLException.class);

        tournamentDao = new TournamentDaoImpl(dataSource);
    }

    @After
    public void afterTest() {
    }

    @Test(expected = FailedOperationException.class)
    public void add_dbError() throws Exception {
        // Test method
        tournamentDao.addTournament(new Tournament());
    }

    @Test(expected = FailedOperationException.class)
    public void getTournamentById_dbError() throws Exception {
        tournamentDao.getTournamentById(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void getTournamentByName_dbError() throws Exception {
        tournamentDao.getTournamentByName(TOURNAMENT_NAME);
    }

    @Test(expected = FailedOperationException.class)
    public void getTournamentListByManager_dbError() throws Exception {
        tournamentDao.getTournamentListByManager(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void getTournamentList_dbError() throws Exception {
        tournamentDao.getTournamentList();
    }

    @Test(expected = FailedOperationException.class)
    public void updateTournament_dbError() throws Exception {
        Tournament testTournament = createRandomTournament();
        tournamentDao.updateTournament(NON_EXISTING_ID, testTournament);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteTournamentById_dbError() throws Exception {
        tournamentDao.deleteTournamentById(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteAll_dbError() throws Exception {
        tournamentDao.deleteAll();
    }
}