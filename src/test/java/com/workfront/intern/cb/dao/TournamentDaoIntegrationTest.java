package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.TournamentFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

public class TournamentDaoIntegrationTest extends BaseTest {

    // DAO instances
    private ManagerDao managerDao;
    private TournamentDao tournamentDao;

    // Test helper objects
    private Manager testManager;
    private Tournament testTournament;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() {
        managerDao = new ManagerDaoImpl(dataSource);
        tournamentDao = new TournamentDaoImpl(dataSource);

        // Delete all remaining objects
        tournamentDao.deleteAll();
        managerDao.deleteAll();

        // Initialize random manager instance
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to DB
        managerDao.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // Initialize random tournament instance
        testTournament = createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());

        // Save to DB
        tournamentDao.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);
    }

    @After
    public void afterTest() {
        final String WARNING_MESSAGE = "WARNING: testTournament was null after test execution";

        // Deleting 'tournament' of manager type field after passed test
        if (testTournament != null) {
            tournamentDao.deleteTournamentById(testTournament.getTournamentId());
        } else {
            System.out.println(WARNING_MESSAGE);
        }

        // Deleting 'manager' of manager type field after passed test
        if (testManager != null) {
            managerDao.deleteManagerById(testManager.getId());
        } else {
            System.out.println(WARNING_MESSAGE);
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
        // Testing method
        boolean deleted = tournamentDao.deleteTournamentById(testTournament.getTournamentId());
        assertTrue(deleted);

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
        // Initialize random tournament instance
        int targetId = testManager.getId();

        // Testing method
        Tournament tournament = createRandomTournament();
        tournament.setManagerId(targetId);
        assertEquals(0, tournament.getTournamentId());

        // Testing method
        boolean added = tournamentDao.addTournament(tournament);

        assertTrue(added);
        assertTrue(tournament.getTournamentId() > 0);

        tournamentDao.deleteTournamentById(tournament.getTournamentId());
    }

    @Test
    public void updateTournament() {
        // Testing method
        Tournament tournament = createRandomTournament();

        // Tournament new data
        String nameUpdate = "UPDATED, THE BEST OF IF THE BEST";
        Timestamp startDateUpdate = Timestamp.valueOf("2020-08-08 10:00:00");
        Timestamp endDateUpdated = Timestamp.valueOf("2020-08-08 20:00:00");
        String locationUpdated = "Yerevan, Armenia";
        String descriptionUpdated = "UPDATED, Tournament begins gentlemen, welcome";

        int targetId = testManager.getId();
        int tournamentId = testTournament.getTournamentId();

        tournament.setTournamentId(tournamentId);
        tournament.setTournamentName(nameUpdate);
        tournament.setStartDate(startDateUpdate);
        tournament.setEndDate(endDateUpdated);
        tournament.setLocation(locationUpdated);
        tournament.setTournamentDescription(descriptionUpdated);
        tournament.setTournamentFormatId(TournamentFormat.ROUND_ROBBIN.getFormatId());
        tournament.setManagerId(targetId);

        // Updates specific tournament in db
        boolean updated = tournamentDao.updateTournament(tournamentId, tournament);
        testTournament = tournamentDao.getTournamentById(tournamentId);

        assertTrue(updated);
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
        boolean deleteAll = tournamentDao.deleteAll();
        assertTrue(deleteAll);

        List<Tournament> tournamentList = tournamentDao.getTournamentList();
        assertEquals(0, tournamentList.size());
    }
}
