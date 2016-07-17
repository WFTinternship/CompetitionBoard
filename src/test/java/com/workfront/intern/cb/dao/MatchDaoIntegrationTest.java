package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.Tournament;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

import static org.junit.Assert.*;

public class MatchDaoIntegrationTest extends BaseTest {

    // DAO instances
    private ManagerDao managerDao;
    private TournamentDao tournamentDao;
    private GroupDao groupDao;
    private MatchDao matchDao;


    // Test helper objects
    private Manager testManager;
    private Tournament testTournament;
    private Group testGroup;
    private Match testMatch;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() {
        managerDao = new ManagerDaoImpl(dataSource);
        tournamentDao = new TournamentDaoImpl(dataSource);
        groupDao = new GroupDaoImpl(dataSource);
        matchDao = new MatchDaoImpl(dataSource);

        // Delete all remaining objects
        matchDao.deleteAll();
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


        // Initialize random match instance
        testMatch = createRandomMatch();
        testMatch.setGroupId(testGroup.getGroupId());
        assertEquals(0, testMatch.getMatchId());

        // Save to DB
        matchDao.addMatch(testMatch);
        assertTrue(testMatch.getMatchId() > 0);
    }

    @After
    public void afterTest() {
        final String WARNING_MESSAGE = "WARNING: testTournament was null after test execution";

        // Deleting 'match' of manager type field after passed test
        if (testMatch != null) {
            managerDao.deleteManagerById(testMatch.getMatchId());
        } else {
            System.out.println(WARNING_MESSAGE);
        }

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
    public void addMatch_created() {

    }

    @Test
    public void getMatchId_notFound() {



    }

    @Test
    public void getMatchById_found() {
        int targetId = testGroup.getGroupId();

        // Testing method
        Match match = matchDao.getMatchByGroupId(targetId);

        assertNotNull(match);
        assertEquals(testMatch.getMatchId(), match.getMatchId());
        assertEquals(testMatch.getGroupId(), match.getGroupId());
        assertEquals(testMatch.getParticipantOneId(), match.getParticipantOneId());
        assertEquals(testMatch.getParticipantTwoId(), match.getParticipantTwoId());
        assertEquals(testMatch.getScoreParticipantOne(), match.getScoreParticipantOne());
        assertEquals(testMatch.getScoreParticipantTwo(), match.getScoreParticipantTwo());

    }

    @Test
    public void getMatchByGroupId_notFound() {
        boolean deleted = matchDao.deleteMatch(NON_EXISTING_ID);

        assertFalse(deleted);
    }

    @Test
    public void getMatchByGroupId_found() {

    }

    @Test
    public void getMatchList_emptyList() {

    }

    @Test
    public void getMatchList_found() {

    }

    @Test
    public void updateMatch() {

    }

    @Test
    public void deleteMatch_deleted() {

    }

    @Test
    public void deleteAll() {

    }


    // endregion
}
