package com.workfront.intern.cb.service.servicespringprofile.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.DataHelper;
import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.service.GroupService;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.TournamentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class GroupServiceIntegrationTest extends BaseTest {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private GroupService groupService;

    private Tournament testTournament;
    private Group testGroup;

    @Before
    public void beforeTest() throws Exception {
        // Delete all remaining objects
        cleanUp();

        // Initialize random manager instance
        Manager testManager = DataHelper.createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to DB
        managerService.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // Initialize random tournament instance
        testTournament = DataHelper.createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());

        // Save to DB
        tournamentService.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);

        // Initialize random group instance
        testGroup = DataHelper.createRandomGroup();
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

    @Test
    public void addGroup_created() throws Exception {
        // Initialize random tournament instance
        int tournamentId;
        tournamentId = testTournament.getTournamentId();

        // Testing method
        Group group = DataHelper.createRandomGroup();
        group.setTournamentId(tournamentId);
        assertEquals(0, group.getGroupId());

        // Testing method
        groupService.addGroup(group);
        assertTrue(group.getTournamentId() > 0);

        groupService.deleteGroup(group.getGroupId());
    }

    @Test(expected = RuntimeException.class)
    public void getGroupById_notFound() throws Exception {
        // Testing method
        Group group = groupService.getGroupById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, group);
    }

    @Test
    public void getGroupById_found() throws Exception {
        int groupId;
        groupId = testGroup.getGroupId();

        // Testing method
        Group group = groupService.getGroupById(groupId);

        assertNotNull(group);
        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipants(), group.getNextRoundParticipants());
    }

    @Test
    public void getGroupByTournamentList_emptyList() throws Exception {
        int groupId;
        groupId = testGroup.getGroupId();
        int tournamentId = testTournament.getTournamentId();

        groupService.deleteGroup(groupId);

        // Testing method
        List<Group> groupList = groupService.getTournamentGroups(tournamentId);

        assertNotNull(groupList);
        assertEquals(0, groupList.size());
    }

    @Test
    public void getGroupByTournamentList_found() throws Exception {
        // Testing method
        int tournamentId;
        tournamentId = testTournament.getTournamentId();
        List<Group> groupList = groupService.getTournamentGroups(tournamentId);

        assertNotNull(groupList);
        assertEquals(1, groupList.size());

        Group group = groupList.get(0);

        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getGroupName(), group.getGroupName());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipants(), group.getNextRoundParticipants());
    }

    @Test
    public void getAllGroups_emptyList() throws Exception {
        int groupId;
        groupId = testGroup.getGroupId();
        groupService.deleteGroup(groupId);

        // Testing method
        List<Group> groupList = groupService.getAllGroups();

        assertNotNull(groupList);
        assertEquals(0, groupList.size());
    }

    @Test
    public void getAllGroups_found() throws Exception {
        // Testing method
        List<Group> groupList;
        groupList = groupService.getAllGroups();

        assertNotNull(groupList);
        assertEquals(1, groupList.size());

        Group group = groupList.get(0);

        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getGroupName(), group.getGroupName());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipants(), group.getNextRoundParticipants());
    }

    @Test
    public void updateGroup() throws Exception {
        int groupId;
        groupId = testGroup.getGroupId();
        int tournamentId = testTournament.getTournamentId();

        // Group new data
        int participantsCount = 10;
        int round = 20;
        int nextRoundParticipants = 30;

        // Testing method
        Group group = DataHelper.createRandomGroup();
        group.setGroupId(groupId);
        group.setParticipantsCount(participantsCount);
        group.setTournamentId(tournamentId);
        group.setRound(round);
        group.setNextRoundParticipants(nextRoundParticipants);

        // Updates specific tournament in db
        groupService.updateGroup(groupId, group);
        testGroup = groupService.getGroupById(groupId);

        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getGroupName(), group.getGroupName());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipants(), group.getNextRoundParticipants());
    }

    @Test(expected = RuntimeException.class)
    public void deleteGroup_notFound() throws Exception {
        groupService.deleteGroup(NON_EXISTING_ID);
    }

    @Test
    public void deleteGroup_found() throws Exception {
        groupService.deleteGroup(testGroup.getGroupId());
    }

    @Test
    public void deleteAll() throws Exception {
        groupService.deleteAll();

        List<Group> groupList = groupService.getAllGroups();

        assertEquals(0, groupList.size());
    }

    // endregion
}