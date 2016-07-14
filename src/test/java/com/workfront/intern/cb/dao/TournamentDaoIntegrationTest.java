package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.TournamentFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class TournamentDaoIntegrationTest extends BaseTest {
    private ManagerDao managerDao = new ManagerDaoImpl();
    private TournamentDao tournamentDao = new TournamentDaoImpl();

    private Tournament testTournament;
    private Manager testManager;

    private Tournament createRandomTournament() {
        testTournament = new Tournament();
        int targetId = testManager.getId();

        String tournamentName = "THE BEST OF IF THE BEST";
        Timestamp startDate = Timestamp.valueOf("2020-08-08 10:00:00");
        Timestamp endDate = Timestamp.valueOf("2020-08-08 20:00:00");
        String location = "Yerevan, Armenia";
        String tournamentDescription = "Tournament begins gentlemen, welcome";
        int tournamentFormatId = TournamentFormat.OLYMPIC.getFormatId();

        // Sets specific data to testTournament
        testTournament.setTournamentName(tournamentName);
        testTournament.setStartDate(startDate);
        testTournament.setEndDate(endDate);
        testTournament.setLocation(location);
        testTournament.setTournamentDescription(tournamentDescription);
        testTournament.setTournamentFormatId(tournamentFormatId);
        testTournament.setManagerId(targetId);

        return testTournament;
    }

    @Before
    public void beforeTest() {
        // Initialize random manager instance
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());
        managerDao.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // Initialize random tournament instance
        testTournament = createRandomTournament();
        assertEquals(0, testTournament.getTournamentId());
        tournamentDao.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);
    }

    @After
    public void afterTest() {
        // Deleting 'manager' of manager type field after passed test
        if (testManager != null) {
            managerDao.deleteManagerById(testManager.getId());
        } else {
            System.out.println("WARNING: testManager was null after test execution");
        }

//        // Deleting 'manager' of manager type field after passed test
        if (testTournament != null) {
            tournamentDao.deleteTournamentById(testTournament.getTournamentId());
        } else {
            System.out.println("WARNING: testTournament was null after test execution");
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
    public void updateTournament_updated() {
    }

    @Test
    public void deleteTournamentById_notFound() {
    }

    @Test
    public void deleteTournamentById_found() {
    }
}
