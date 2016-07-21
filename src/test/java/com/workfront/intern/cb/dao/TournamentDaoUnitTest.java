package com.workfront.intern.cb.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.workfront.intern.cb.common.Tournament;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class TournamentDaoUnitTest {
    DataSource dataSource;
    TournamentDao tournamentDao;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        dataSource = Mockito.mock(DataSource.class);

        Connection conn = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);

        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        when(conn.prepareStatement(any(String.class), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(SQLException.class);

        when(conn.prepareStatement(any(String.class), anyInt())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        tournamentDao = Mockito.mock(TournamentDaoImpl.class);
        when(tournamentDao.addTournament(any(Tournament.class))).thenCallRealMethod();
        Whitebox.setInternalState(tournamentDao, "dataSource", dataSource);
    }

    @After
    public void afterTest() {
    }

    @Test
    public void add_dbError() {
        // Test method
        boolean result = tournamentDao.addTournament(new Tournament());

        assertEquals("addTournament method return value is incorrect", true, result);

        // Verify resources closes
        Mockito.verify((TournamentDaoImpl)tournamentDao).closeResources(any(Connection.class), any(PreparedStatement.class), any(ResultSet.class));
    }
 }
