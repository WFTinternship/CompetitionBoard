package com.workfront.intern.cb.service.servicespringprofile.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.TournamentFormat;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.TournamentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class TournamentServiceIntegrationTest extends BaseTest {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private TournamentService tournamentService;

    // Test helper objects
    private Manager testManager;
    private Tournament testTournament;

    @Before
    public void beforeTest() throws Exception {
        // Delete all remaining objects
        cleanUp();

        // Initialize random manager instance
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to DB
        managerService.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // Initialize random tournament instance
        testTournament = createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());

        // Save to DB
        tournamentService.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);
    }

    @After
    public void afterTest() throws FailedOperationException, ObjectNotFoundException {
        cleanUp();
    }

    private void cleanUp() throws FailedOperationException {
        tournamentService.deleteAll();
        managerService.deleteAll();
    }

    // region <TEST CASES>

    @Test(expected = RuntimeException.class)
    public void getTournamentById_notFound() throws Exception {
        // Testing method
        Tournament tournament = tournamentService.getTournamentById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, tournament);
    }

    @Test
    public void getTournamentById_found() throws Exception {
        int targetId = testTournament.getTournamentId();

        // Testing method
        Tournament tournament = tournamentService.getTournamentById(targetId);

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
    public void getTournamentListByName_emptyList() throws Exception {
        tournamentService.deleteTournamentById(testTournament.getTournamentId());

        // Testing method
        List<Tournament> tournamentListByName = tournamentService.getTournamentListByName(TOURNAMENT_NAME);

        assertNotNull(tournamentListByName);
        assertEquals(0, tournamentListByName.size());
    }

    @Test
    public void getTournamentListByName_found() throws Exception {
        String tournamentName = testTournament.getTournamentName();

        // Testing method
        List<Tournament> tournamentListByName = tournamentService.getTournamentListByName(tournamentName);

        assertNotNull(tournamentListByName);
        assertEquals(1, tournamentListByName.size());

        Tournament tournament = tournamentListByName.get(0);

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
    public void getTournamentList_emptyList() throws Exception {
        tournamentService.deleteTournamentById(testTournament.getTournamentId());

        // Testing method
        List<Tournament> tournamentList = tournamentService.getTournamentList();

        assertNotNull(tournamentList);
        assertEquals(0, tournamentList.size());
    }

    @Test
    public void getTournamentList_found() throws Exception {
        // Testing method
        List<Tournament> tournamentList = tournamentService.getTournamentList();

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
    public void getTournamentListByManager_emptyList() throws Exception {
        // Testing method
        tournamentService.deleteTournamentById(testTournament.getTournamentId());

        int targetId = testManager.getId();
        List<Tournament> tournamentList = tournamentService.getTournamentListByManager(targetId);

        assertNotNull(tournamentList);
        assertEquals(0, tournamentList.size());
    }

    @Test
    public void getTournamentListByManager_found() throws Exception {
        int targetId = testManager.getId();

        // Testing method
        List<Tournament> tournamentList = tournamentService.getTournamentListByManager(targetId);

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
    public void addTournament_created() throws Exception {
        // Initialize random tournament instance
        int targetId = testManager.getId();

        // Testing method
        Tournament tournament = createRandomTournament();
        tournament.setManagerId(targetId);
        assertEquals(0, tournament.getTournamentId());

        // Testing method
        tournamentService.addTournament(tournament);
        assertTrue(tournament.getTournamentId() > 0);

        tournamentService.deleteTournamentById(tournament.getTournamentId());
    }

    @Test
    public void updateTournament() throws Exception {
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
        tournamentService.updateTournament(tournamentId, tournament);
        testTournament = tournamentService.getTournamentById(tournamentId);

        assertEquals(testTournament.getTournamentId(), tournament.getTournamentId());
        assertEquals(testTournament.getTournamentName(), tournament.getTournamentName());
        assertEquals(testTournament.getStartDate(), tournament.getStartDate());
        assertEquals(testTournament.getEndDate(), tournament.getEndDate());
        assertEquals(testTournament.getLocation(), tournament.getLocation());
        assertEquals(testTournament.getTournamentDescription(), tournament.getTournamentDescription());
        assertEquals(testTournament.getTournamentFormatId(), tournament.getTournamentFormatId());
        assertEquals(testTournament.getManagerId(), tournament.getManagerId());
    }

    @Test(expected = RuntimeException.class)
    public void deleteTournamentById_notFound() throws Exception {
        tournamentService.deleteTournamentById(NON_EXISTING_ID);

    }

    @Test
    public void deleteTournamentById_found() throws Exception {
        tournamentService.deleteTournamentById(testTournament.getTournamentId());

    }

    @Test
    public void deleteAll() throws Exception {
        tournamentService.deleteAll();

        List<Tournament> tournamentList = tournamentService.getTournamentList();

        assertEquals(0, tournamentList.size());
    }

    // endregion
}