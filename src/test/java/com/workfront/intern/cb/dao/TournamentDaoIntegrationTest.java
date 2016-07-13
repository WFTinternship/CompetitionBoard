package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.util.StringHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TournamentDaoIntegrationTest extends BaseTest {
    private ManagerDao managerDao = new ManagerDaoImpl();
    private TournamentDao tournamentDao = new TournamentDaoImpl();
    private Tournament testTournament;
    private Manager testManager;


    private Tournament createRandomTournament() {
        String TOURNAMENT_NAME = "THE BEST OF IF THE BEST";
        String START_DATE = "2020-08-08 10:00:00";
        String END_DATE = "2020-08-08 20:00:00";
        String LOCATION = "Yerevan, Armenia";
        String TOURNAMENT_DESCRIPTION = "Tournament begins gentlemen, welcome";
        int TOURNAMENT_FORMAT_ID = 1;
        int MANAGER_ID = 1;

        return testTournament;
    }

    @Before
    public void beforeTest() {
        // Initialize random manager instance
        testManager = createRandomManager();
        testTournament = createRandomTournament();

        assertEquals(0, testManager.getId());
        assertEquals(0, testTournament.getTournamentId());

        // Save to database
        managerDao.addManager(testManager);
        tournamentDao.addTournament(testTournament);

        // Validate ID
        assertTrue(testManager.getId() > 0);
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


        // Deleting 'manager' of manager type field after passed test
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
        assertEquals(testTournament.getTournamentFormat(), tournament.getTournamentFormat());
        assertEquals(testTournament.getManager(), tournament.getManager());


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
