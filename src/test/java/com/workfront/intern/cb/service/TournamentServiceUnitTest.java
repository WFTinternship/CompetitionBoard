package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.dao.ManagerDaoImpl;
import com.workfront.intern.cb.dao.TournamentDao;
import com.workfront.intern.cb.dao.TournamentDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;

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




}
