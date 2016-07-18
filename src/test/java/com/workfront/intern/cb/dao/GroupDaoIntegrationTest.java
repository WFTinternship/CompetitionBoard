package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
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

    // Test helper objects
    private Manager testManager;
    private Tournament testTournament;
    private Group testGroup;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() {
        managerDao = new ManagerDaoImpl(dataSource);
        tournamentDao = new TournamentDaoImpl(dataSource);
        groupDao = new GroupDaoImpl(dataSource);


        // Delete all remaining objects
        groupDao.deleteAll();
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


        // Initialize random group instance
        testGroup = createRandomGroup();
        testGroup.setTournamentId(testTournament.getTournamentId());
        assertEquals(0, testGroup.getGroupId());

        // Save to DB
        groupDao.addGroup(testGroup);
        assertTrue(testGroup.getGroupId() > 0);
    }

    @After
    public void afterTest() {
        final String WARNING_MESSAGE = "WARNING: testTournament was null after test execution";

        // Deleting 'group' of manager type field after passed test
        if (testGroup != null) {
            groupDao.deleteGroup(testGroup.getGroupId());
        } else {
            System.out.println(WARNING_MESSAGE);
        }

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

    // region <TEST CASES>

    @Test
    public void addGroup_created() {
        // Initialize random tournament instance
        int tournamentId = testTournament.getTournamentId();

        // Testing method
        Group group = createRandomGroup();
        group.setTournamentId(tournamentId);
        assertEquals(0, group.getGroupId());

        // Testing method
        boolean added = groupDao.addGroup(group);

        assertTrue(added);
        assertTrue(group.getTournamentId() > 0);

        groupDao.deleteGroup(group.getGroupId());
    }

    @Test
    public void getGroupById_notFound() {
        // Testing method
        Group group = groupDao.getGroupById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, group);
    }


    @Test
    public void getGroupById_found() {
        int groupId = testGroup.getGroupId();

        // Testing method
        Group group = groupDao.getGroupById(groupId);

        assertNotNull(group);
        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipnats(), group.getNextRoundParticipnats());
    }

    @Test
    public void getGroupByTournamentList_emptyList() {
        int groupId = testGroup.getGroupId();
        int tournamentId = testTournament.getTournamentId();

        boolean deleted = groupDao.deleteGroup(groupId);

        assertTrue(deleted);

        // Testing method
        List<Group> groupList = groupDao.getGroupByTournamentList(tournamentId);
        assertNotNull(groupList);
        assertEquals(0, groupList.size());
    }

    @Test
    public void getGroupByTournamentList_found() {
        // Testing method
        int tournamentId = testTournament.getTournamentId();
        List<Group> groupList = groupDao.getGroupByTournamentList(tournamentId);

        assertNotNull(groupList);
        assertEquals(1, groupList.size());

        Group group = groupList.get(0);

        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipnats(), group.getNextRoundParticipnats());
    }

    @Test
    public void getAllGroups_emptyList() {
        int groupId = testGroup.getGroupId();
        boolean deleted = groupDao.deleteGroup(groupId);

        assertTrue(deleted);

        // Testing method
        List<Group> groupList = groupDao.getAllGroups();
        assertNotNull(groupList);
        assertEquals(0, groupList.size());
    }

    @Test
    public void getAllGroups_found() {
        // Testing method
        List<Group> groupList = groupDao.getAllGroups();

        assertNotNull(groupList);
        assertEquals(1, groupList.size());

        Group group = groupList.get(0);

        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipnats(), group.getNextRoundParticipnats());
    }

    @Test
    public void updateGroup() {
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
        group.setNextRoundParticipnats(nextRoundParticipants);

        // Updates specific tournament in db
        boolean updated = groupDao.updateGroup(groupId, group);
        testGroup = groupDao.getGroupById(groupId);

        assertTrue(updated);
        assertEquals(testGroup.getGroupId(), group.getGroupId());
        assertEquals(testGroup.getParticipantsCount(), group.getParticipantsCount());
        assertEquals(testGroup.getTournamentId(), group.getTournamentId());
        assertEquals(testGroup.getRound(), group.getRound());
        assertEquals(testGroup.getNextRoundParticipnats(), group.getNextRoundParticipnats());
    }

    @Test
    public void deleteGroup_notFound() {
        boolean deleted = groupDao.deleteGroup(NON_EXISTING_ID);

        assertFalse(deleted);
    }

    @Test
    public void deleteGroup_found() {
        boolean deleted = groupDao.deleteGroup(testGroup.getGroupId());

        assertTrue(deleted);
    }

    @Test
    public void deleteAll() {
        boolean deleteAll = groupDao.deleteAll();
        assertTrue(deleteAll);

        List<Group> groupList = groupDao.getAllGroups();
        assertEquals(0, groupList.size());

    }

    // endregion
}
