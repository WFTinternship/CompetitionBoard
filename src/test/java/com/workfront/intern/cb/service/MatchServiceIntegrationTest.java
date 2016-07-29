package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatchServiceIntegrationTest extends BaseTest {

    private ManagerService managerService;
    private TournamentService tournamentService;
    private GroupService groupService;
    private MatchService matchService;

    private Group testGroup;
    private Match testMatch;

    @Before
    public void beforeTest() throws Exception {
        managerService = new ManagerServiceImpl();
        tournamentService = new TournamentServiceImpl();
        groupService = new GroupServiceImpl();
        matchService = new MatchServiceImpl();

        // Delete all remaining objects
        cleanUp();

        // Initialize random manager instance
        Manager testManager = createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to DB
        managerService.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // Initialize random tournament instance
        Tournament testTournament = createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());

        // Save to DB
        tournamentService.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);

        // Initialize random group instance
        testGroup = createRandomGroup();
        testGroup.setTournamentId(testTournament.getTournamentId());
        assertEquals(0, testGroup.getGroupId());

        // Save to DB
        groupService.addGroup(testGroup);
        assertTrue(testGroup.getGroupId() > 0);

        // Initialize random match instance
        testMatch = createRandomMatch();
        testMatch.setGroupId(testGroup.getGroupId());
        assertEquals(0, testMatch.getMatchId());

        // Save to DB
        matchService.addMatch(testMatch);
        assertTrue(testMatch.getMatchId() > 0);
    }

    @After
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        matchService.deleteAll();
        groupService.deleteAll();
        tournamentService.deleteAll();
        managerService.deleteAll();
    }

    // region <TEST CASES>

    @Ignore
    @Test
    public void addMatch_created() throws Exception {
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void getMatchId_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void getMatchById_found() throws ObjectNotFoundException, FailedOperationException {
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void getMatchByGroupId_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void getMatchByGroupId_found() throws Exception {
    }

    @Ignore
    @Test
    public void getMatchList_emptyList() throws Exception {
    }

    @Ignore
    @Test
    public void getMatchList_found() throws Exception {
    }

    @Ignore
    @Test
    public void updateMatch() throws Exception {
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void deleteMatch_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void deleteMatch_found() throws Exception {
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
    }

    // endregion
}