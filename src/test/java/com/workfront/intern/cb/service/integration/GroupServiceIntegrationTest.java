package com.workfront.intern.cb.service.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GroupServiceIntegrationTest extends BaseTest {

    private ManagerService managerService;
    private TournamentService tournamentService;
    private GroupService groupService;

    private Tournament testTournament;
    private Group testGroup;

    @Before
    public void beforeTest() throws Exception {
        managerService = new ManagerServiceImpl();
        tournamentService = new TournamentServiceImpl();
        groupService = new GroupServiceImpl();

        // Delete all remaining objects
        cleanUp();

        // Initialize random manager instance
        Manager testManager = createRandomManager();
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

        // Initialize random group instance
        testGroup = createRandomGroup();
        testGroup.setTournamentId(testTournament.getTournamentId());
        assertEquals(0, testGroup.getGroupId());

        // Save to DB
        groupService.addGroup(testGroup);
        assertTrue(testGroup.getGroupId() > 0);
    }

    @After
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        groupService.deleteAll();
        tournamentService.deleteAll();
        managerService.deleteAll();
    }

    // region <TEST CASES>

    @Ignore
    @Test
    public void addGroup_created() throws Exception {
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void getGroupById_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void getGroupById_found() throws Exception {
    }

    @Ignore
    @Test
    public void getGroupByTournamentList_emptyList() throws Exception {
    }

    @Ignore
    @Test
    public void getGroupByTournamentList_found() throws Exception {
    }

    @Ignore
    @Test
    public void getAllGroups_emptyList() throws Exception {
    }

    @Ignore
    @Test
    public void getAllGroups_found() throws Exception {
    }

    @Ignore
    @Test
    public void updateGroup() throws Exception {
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void deleteGroup_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void deleteGroup_found() throws Exception {
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
    }

    // endregion
}