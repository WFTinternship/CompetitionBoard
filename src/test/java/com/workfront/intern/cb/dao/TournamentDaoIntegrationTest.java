package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.util.StringHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

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
        // Testing method
        Tournament tournament = tournamentDao.getTournamentById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, tournament);
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
        boolean deleted = tournamentDao.deleteTournamentById(testTournament.getTournamentId());

        assertTrue(deleted);

        // Testing method
        List<Tournament> tournamentList = tournamentDao.getTournamentList();

        assertNotNull(tournamentList);
        assertEquals(0, tournamentList.size());
    }

    @Test
    public void getTournamentList_found() {
        // Testing method
        List<Tournament> tournamentList = tournamentDao.getTournamentList();

        assertNotNull(tournamentList);
        assertEquals(1, tournamentList.size());

        Tournament tournament = tournamentList.get(0);

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
    public void getTournamentListByManager_emptyList() {
        boolean deleted = tournamentDao.deleteTournamentById(testTournament.getTournamentId());

        assertTrue(deleted);

        // Testing method
        int targetId = testManager.getId();
        List<Tournament> tournamentList = tournamentDao.getTournamentListByManager(targetId);

        assertNotNull(tournamentList);
        assertEquals(0, tournamentList.size());
    }

    @Test
    public void getTournamentListByManager_found() {
        // Testing method
        int targetId = testManager.getId();
        List<Tournament> tournamentList = tournamentDao.getTournamentListByManager(targetId);

        assertNotNull(tournamentList);
        assertEquals(1, tournamentList.size());

        Tournament tournament = tournamentList.get(0);

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
    public void addTournament_created() {
//        // Initialize random tournament instance
//        testManager = createRandomManager();
//        managerDao.addManager(testManager);
//
//        int targetId = testManager.getId();
//
//        Tournament tournament = createRandomTournament();
//        tournament.setManagerId(targetId);
//
//        assertEquals(0, tournament.getTournamentId());
//
//        // Testing method
//        boolean added = tournamentDao.addTournament(tournament);
//
//        assertTrue(added);
//        assertTrue(tournament.getTournamentId() > 0);
    }

    @Test
    public void updateTournament() {
        String updatedTournamentName = "UPDATED, THE BEST OF IF THE BEST";
        Timestamp startDate = Timestamp.valueOf("2020-08-08 10:00:00");
        Timestamp endDate = Timestamp.valueOf("2020-08-08 20:00:00");
        String location = "Yerevan, Armenia";
        String  updatedTournamentDescription = "UPDATED, Tournament begins gentlemen, welcome";

        int targetId = testManager.getId();

        // Testing method
        Tournament tournament = createRandomTournament();
        tournament.setTournamentName(updatedTournamentName);
        tournament.setStartDate(startDate);
        tournament.setEndDate(endDate);
        tournament.setLocation(location);
        tournament.setTournamentDescription(updatedTournamentDescription);
//        testTournament.setTournamentFormatId(tournamentFormat.getFormatId());
        tournament.setManagerId(targetId);

        boolean updated = new TournamentDaoImpl().updateTournament(tournament);
        // ????
        testTournament  = new TournamentDaoImpl().getTournamentById(targetId);

        assertTrue(updated);
//        assertEquals(testTournament.getTournamentId(), tournament.getTournamentId());
        assertEquals(testTournament.getTournamentName(), tournament.getTournamentName());
    }

    @Test
    public void deleteTournamentById_notFound() {
        boolean deleted = tournamentDao.deleteTournamentById(NON_EXISTING_ID);

        assertFalse(deleted);
    }

    @Test
    public void deleteTournamentById_found() {
        boolean deleted = tournamentDao.deleteTournamentById(testTournament.getTournamentId());

        assertTrue(deleted);
    }

    @Test
    public void deleteAll() {
        boolean deleted = tournamentDao.deleteTournamentById(testTournament.getTournamentId());

        assertTrue(deleted);
    }
}
