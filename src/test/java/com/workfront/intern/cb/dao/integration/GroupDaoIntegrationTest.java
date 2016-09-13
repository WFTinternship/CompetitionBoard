package com.workfront.intern.cb.dao.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

public class GroupDaoIntegrationTest extends BaseTest {

    // DAO instances
    private ManagerDao managerDao;
    private TournamentDao tournamentDao;
    private GroupDao groupDao;

    private Tournament testTournament;
    private Group testGroup;

    private DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() throws Exception {
        managerDao = new ManagerDaoImpl(dataSource);
        tournamentDao = new TournamentDaoImpl(dataSource);
        groupDao = new GroupDaoImpl(dataSource);

        // Delete all remaining objects
        cleanUp();

        // Initialize random manager instance
        Manager testManager = createRandomManager();
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


        // Initialize random group instance
        testGroup = createRandomGroup();
        testGroup.setTournamentId(testTournament.getTournamentId());
        assertEquals(0, testGroup.getGroupId());

        // Save to DB
        groupDao.addGroup(testGroup);
        assertTrue(testGroup.getGroupId() > 0);
    }

    @After
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        groupDao.deleteAll();
        tournamentDao.deleteAll();
        managerDao.deleteAll();
    }

    // region <TEST CASES>

    @Test
    public void addGroup_created() throws Exception {
        // Initialize random tournament instance
        int tournamentId = testTournament.getTournamentId();

        // Testing method
        Group group = createRandomGroup();
        group.setTournamentId(tournamentId);
        assertEquals(0, group.getGroupId());

        // Testing method
        groupDao.addGroup(group);
        assertTrue(group.getTournamentId() > 0);

        groupDao.deleteGroup(group.getGroupId());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void getGroupById_notFound() throws Exception {
        // Testing method
        Group group = groupDao.getGroupById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, group);
    }

    @Test
    public void getGroupById_found() throws Exception {
        int groupId = testGroup.getGroupId();

        // Testing method
        Group group = groupDao.getGroupById(groupId);

        assertNotNull(group);
        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipants(), group.getNextRoundParticipants());
    }

    @Test
    public void getGroupByTournamentList_emptyList() throws Exception {
        int groupId = testGroup.getGroupId();
        int tournamentId = testTournament.getTournamentId();

        groupDao.deleteGroup(groupId);

        // Testing method
        List<Group> groupList = groupDao.getTournamentGroups(tournamentId);

        assertNotNull(groupList);
        assertEquals(0, groupList.size());
    }

    @Test
    public void getGroupByTournamentList_found() throws Exception {
        // Testing method
        int tournamentId = testTournament.getTournamentId();
        List<Group> groupList = groupDao.getTournamentGroups(tournamentId);

        assertNotNull(groupList);
        assertEquals(1, groupList.size());

        Group group = groupList.get(0);

        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipants(), group.getNextRoundParticipants());
    }

    @Test
    public void getAllGroups_emptyList() throws Exception {
        int groupId = testGroup.getGroupId();
        groupDao.deleteGroup(groupId);

        // Testing method
        List<Group> groupList = groupDao.getAllGroups();

        assertNotNull(groupList);
        assertEquals(0, groupList.size());
    }

    @Test
    public void getAllGroups_found() throws Exception {
        // Testing method
        List<Group> groupList = groupDao.getAllGroups();

        assertNotNull(groupList);
        assertEquals(1, groupList.size());

        Group group = groupList.get(0);

        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipants(), group.getNextRoundParticipants());
    }

    @Test
    public void updateGroup() throws Exception {
        int groupId = testGroup.getGroupId();
        int tournamentId = testTournament.getTournamentId();

        // Group new data
        int participantsCount = 10;
        int round = 20;
        int nextRoundParticipants = 30;

        // Testing method
        Group group = createRandomGroup();
        group.setGroupId(groupId);
        group.setParticipantsCount(participantsCount);
        group.setTournamentId(tournamentId);
        group.setRound(round);
        group.setNextRoundParticipants(nextRoundParticipants);

        // Updates specific tournament in db
        groupDao.updateGroup(groupId, group);
        testGroup = groupDao.getGroupById(groupId);

        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipants(), group.getNextRoundParticipants());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void deleteGroup_notFound() throws Exception {
        groupDao.deleteGroup(NON_EXISTING_ID);
    }

    @Test
    public void deleteGroup_found() throws Exception {
        groupDao.deleteGroup(testGroup.getGroupId());
    }

    @Test
    public void deleteAll() throws Exception {
        groupDao.deleteAll();

        List<Group> groupList = groupDao.getAllGroups();

        assertEquals(0, groupList.size());
    }

    // endregion
}
