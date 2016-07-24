package com.workfront.intern.cb.dao;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Tournament;
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

    @Test
    public void add_dbError() {
        // Test method
        tournamentDao.addTournament(new Tournament());
    }

    @Test
    public void getTournamentById_dbError() {
        tournamentDao.getTournamentById(NON_EXISTING_ID);
    }

    @Test
    public void getTournamentListByManager_dbError() {
        tournamentDao.getTournamentListByManager(NON_EXISTING_ID);
    }

    @Test
    public void getTournamentList_dbError() {
        tournamentDao.getTournamentList();
    }

    @Test
    public void updateTournament_dbError() {
        Tournament testTournament = createRandomTournament();
        tournamentDao.updateTournament(NON_EXISTING_ID, testTournament);
    }

    @Test
    public void deleteTournamentById_dbError() {
        tournamentDao.deleteTournamentById(NON_EXISTING_ID);
    }

    @Test
    public void deleteAll_dbError() {
        tournamentDao.deleteAll();
    }
}