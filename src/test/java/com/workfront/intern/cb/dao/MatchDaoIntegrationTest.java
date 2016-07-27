package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

public class MatchDaoIntegrationTest extends BaseTest {

    // DAO instances
    private ManagerDao managerDao;
    private TournamentDao tournamentDao;
    private GroupDao groupDao;
    private MatchDao matchDao;

    // Test helper objects
    protected Manager testManager;
    protected Tournament testTournament;
    private Group testGroup;
    private Match testMatch;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() throws Exception {
        managerDao = new ManagerDaoImpl(dataSource);
        tournamentDao = new TournamentDaoImpl(dataSource);
        groupDao = new GroupDaoImpl(dataSource);
        matchDao = new MatchDaoImpl(dataSource);

        // Delete all remaining objects
        cleanUp();


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
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        matchDao.deleteAll();
        groupDao.deleteAll();
        tournamentDao.deleteAll();
        managerDao.deleteAll();
    }

    // region <TEST CASES>

    @Test
    public void addMatch_created() throws Exception {
        int groupId = testGroup.getGroupId();

        // Initialize random match instance
        Match match = createRandomMatch();
        match.setGroupId(groupId);

        assertEquals(0, match.getMatchId());

        // Testing method
        matchDao.addMatch(match);
        assertTrue(match.getMatchId() > 0);

        matchDao.deleteMatch(match.getMatchId());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void getMatchId_notFound() throws Exception{
        // Testing method
        Match match = matchDao.getMatchById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, match);
    }

    @Test
    public void getMatchById_found() throws ObjectNotFoundException, FailedOperationException {
        int matchId = testMatch.getMatchId();

        // Testing method
        Match match = matchDao.getMatchById(matchId);

        assertNotNull(match);
        assertEquals(testMatch.getMatchId(), match.getMatchId());
        assertEquals(testMatch.getGroupId(), match.getGroupId());
        assertEquals(testMatch.getParticipantOneId(), match.getParticipantOneId());
        assertEquals(testMatch.getParticipantTwoId(), match.getParticipantTwoId());
        assertEquals(testMatch.getScoreParticipantOne(), match.getScoreParticipantOne());
        assertEquals(testMatch.getScoreParticipantTwo(), match.getScoreParticipantTwo());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void getMatchByGroupId_notFound() throws Exception {
        // Testing method
        Match match = matchDao.getMatchByGroupId(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, match);
    }

    @Test
    public void getMatchByGroupId_found() throws Exception {
        int groupId = testGroup.getGroupId();

        // Testing method
        Match match = matchDao.getMatchByGroupId(groupId);

        assertNotNull(match);
        assertEquals(testMatch.getMatchId(), match.getMatchId());
        assertEquals(testMatch.getGroupId(), match.getGroupId());
        assertEquals(testMatch.getParticipantOneId(), match.getParticipantOneId());
        assertEquals(testMatch.getParticipantTwoId(), match.getParticipantTwoId());
        assertEquals(testMatch.getScoreParticipantOne(), match.getScoreParticipantOne());
        assertEquals(testMatch.getScoreParticipantTwo(), match.getScoreParticipantTwo());
    }

    @Test
    public void getMatchList_emptyList() throws Exception {
        int matchId = testMatch.getMatchId();
        int groupId = testGroup.getGroupId();

        // Testing method
        matchDao.deleteMatch(matchId);

        // Testing method
        List<Match> matchList = matchDao.getMatchListByGroup(groupId);

        assertNotNull(matchList);
        assertEquals(0, matchList.size());
    }

    @Test
    public void getMatchList_found() throws Exception {
        int groupId = testGroup.getGroupId();

        // Testing method
        List<Match> matchList = matchDao.getMatchListByGroup(groupId);

        assertNotNull(matchList);
        assertEquals(1, matchList.size());

        Match match = matchList.get(0);
        assertEquals(testMatch.getMatchId(), match.getMatchId());
        assertEquals(testMatch.getGroupId(), match.getGroupId());
        assertEquals(testMatch.getParticipantOneId(), match.getParticipantOneId());
        assertEquals(testMatch.getParticipantTwoId(), match.getParticipantTwoId());
        assertEquals(testMatch.getScoreParticipantOne(), match.getScoreParticipantOne());
        assertEquals(testMatch.getScoreParticipantTwo(), match.getScoreParticipantTwo());
    }

    @Test
    public void updateMatch() throws Exception {
        int matchId = testMatch.getMatchId();
        int groupId = testGroup.getGroupId();
        int participantOneId = 10;
        int participantTwoId = 15;
        int scoreParticipantOne = 20;
        int scoreParticipantTwo = 30;

        // Testing method
        Match match = createRandomMatch();
        match.setMatchId(matchId);
        match.setGroupId(groupId);
        match.setParticipantOneId(participantOneId);
        match.setParticipantTwoId(participantTwoId);
        match.setScoreParticipantOne(scoreParticipantOne);
        match.setScoreParticipantTwo(scoreParticipantTwo);

        // Testing method
        matchDao.updateMatch(matchId, match);
        testMatch = matchDao.getMatchById(matchId);

        assertEquals(testMatch.getMatchId(), match.getMatchId());
        assertEquals(testMatch.getGroupId(), match.getGroupId());
        assertEquals(testMatch.getParticipantOneId(), match.getParticipantOneId());
        assertEquals(testMatch.getParticipantTwoId(), match.getParticipantTwoId());
        assertEquals(testMatch.getScoreParticipantOne(), match.getScoreParticipantOne());
        assertEquals(testMatch.getScoreParticipantTwo(), match.getScoreParticipantTwo());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void deleteMatch_notFound() throws Exception {
        // Testing method
        matchDao.deleteMatch(NON_EXISTING_ID);
    }

    @Test
    public void deleteMatch_found() throws Exception {
        int matchId = testMatch.getMatchId();

        // Testing method
        matchDao.deleteMatch(matchId);
    }

    @Test
    public void deleteAll() throws Exception {
        int groupId = testGroup.getGroupId();

        // Testing method
        matchDao.deleteAll();

        List<Match> matchList = matchDao.getMatchListByGroup(groupId);
        assertEquals(0, matchList.size());
    }

    // endregion
}