package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TournamentServiceIntegrationTest extends BaseTest {

    // DAO instances
    private ManagerService managerService;
    private TournamentService tournamentService;

    // Test helper objects
    private Manager testManager;
    private Tournament testTournament;

    @Before
    public void beforeTest() throws FailedOperationException, ObjectNotFoundException {
        managerService = new ManagerServiceImpl();
        tournamentService = new TournamentServiceImpl();

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

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void getTournamentById_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void getTournamentById_found() throws Exception {
    }

    @Ignore
    @Test
    public void getTournamentList_emptyList() throws Exception {
    }

    @Ignore
    @Test
    public void getTournamentList_found() throws Exception {
    }

    @Ignore
    @Test
    public void getTournamentListByManager_emptyList() throws Exception {
    }

    @Ignore
    @Test
    public void getTournamentListByManager_found() throws Exception {
    }

    @Ignore
    @Test
    public void addTournament_created() throws Exception {
    }

    @Ignore
    @Test
    public void updateTournament() throws Exception {
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void deleteTournamentById_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void deleteTournamentById_found() throws Exception {
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
    }

    // endregion
}