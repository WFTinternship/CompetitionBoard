package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TournamentDaoIntegrationTest extends BaseTest {
    private ManagerDao managerDao = new ManagerDaoImpl();
    private TournamentDao tournamentDao = new TournamentDaoImpl();

    private Tournament testTournament;
    private Manager testManager;

    @Before
    public void beforeTest() {
        // Initialize random manager instance
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());
        managerDao.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // Initialize random tournament instance
        testTournament = createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());
        tournamentDao.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);
    }

    @After
    public void afterTest() {
        // Deleting 'tournament' of manager type field after passed test
        if (testTournament != null) {
            tournamentDao.deleteTournamentById(testTournament.getTournamentId());
        } else {
            System.out.println("WARNING: testTournament was null after test execution");
        }

        // Deleting 'manager' of manager type field after passed test
        if (testManager != null) {
            managerDao.deleteManagerById(testManager.getId());
        } else {
            System.out.println("WARNING: testManager was null after test execution");
        }
    }

    @Test
    public void getTournamentById_notFound() {
    }

    @Test
    public void getTournamentById_found() {
        int targetId = testTournament.getTournamentId();

        // Testing method
        Tournament tournament = tournamentDao.getTournamentById(targetId);

        assertNotNull(tournament);
        assertEquals(testTournament.getTournamentId(), tournament.getTournamentId());
        assertEquals(testTournament.getTournamentName(), tournament.getTournamentName());
        assertEquals(testTournament.getStartDate(), tournament.getStartDate());
        assertEquals(testTournament.getEndDate(), tournament.getEndDate());
        assertEquals(testTournament.getLocation(), tournament.getLocation());
        assertEquals(testTournament.getTournamentDescription(), tournament.getTournamentDescription());
        assertEquals(testTournament.getTournamentFormatId(), tournament.getTournamentFormatId());
        assertEquals(testTournament.getManagerId(), tournament.getManagerId());
    }

    @Test
    public void getTournamentList_emptyList() {
    }

    @Test
    public void getTournamentList_found() {
    }

    @Test
    public void addTournament_created() {
    }

    @Test
    public void updateTournament() {
    }

    @Test
    public void deleteTournamentById_notFound() {
    }

    @Test
    public void deleteTournamentById_found() {
    }

    @Test
    public void deleteAll_deleted() {
    }
}
