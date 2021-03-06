package com.workfront.intern.cb.service.servicespringprofile.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.DataHelper;
import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.service.GroupService;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.MatchService;
import com.workfront.intern.cb.service.TournamentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class MatchServiceIntegrationTest extends BaseTest {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private MatchService matchService;

    private Group testGroup;
    private Match testMatch;

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
        Tournament testTournament = DataHelper.createRandomTournament();
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

        // Initialize random match instance
        testMatch = DataHelper.createRandomMatch();
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

    @Test
    public void addMatch_created() throws Exception {
        int groupId;
        groupId = testGroup.getGroupId();

        // Initialize random match instance
        Match match = DataHelper.createRandomMatch();
        match.setGroupId(groupId);

        assertEquals(0, match.getMatchId());

        // Testing method
        matchService.addMatch(match);
        assertTrue(match.getMatchId() > 0);

        matchService.deleteMatch(match.getMatchId());
    }

    @Test(expected = RuntimeException.class)
    public void getMatchId_notFound() throws Exception {
        // Testing method
        Match match = matchService.getMatchById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, match);
    }

    @Test
    public void getMatchById_found() throws ObjectNotFoundException, FailedOperationException {
        int matchId;
        matchId = testMatch.getMatchId();

        // Testing method
        Match match = matchService.getMatchById(matchId);

        assertNotNull(match);
        assertEquals(testMatch.getMatchId(), match.getMatchId());
        assertEquals(testMatch.getGroupId(), match.getGroupId());
        assertEquals(testMatch.getParticipantOneId(), match.getParticipantOneId());
        assertEquals(testMatch.getParticipantTwoId(), match.getParticipantTwoId());
        assertEquals(testMatch.getScoreParticipantOne(), match.getScoreParticipantOne());
        assertEquals(testMatch.getScoreParticipantTwo(), match.getScoreParticipantTwo());
        assertEquals(testMatch.getMatchScore(), match.getMatchScore());
    }

    @Test(expected = RuntimeException.class)
    public void getMatchByGroupId_notFound() throws Exception {
        // Testing method
        Match match = matchService.getMatchByGroupId(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, match);
    }

    @Test
    public void getMatchByGroupId_found() throws Exception {
        int groupId;
        groupId = testGroup.getGroupId();

        // Testing method
        Match match = matchService.getMatchByGroupId(groupId);

        assertNotNull(match);
        assertEquals(testMatch.getMatchId(), match.getMatchId());
        assertEquals(testMatch.getGroupId(), match.getGroupId());
        assertEquals(testMatch.getParticipantOneId(), match.getParticipantOneId());
        assertEquals(testMatch.getParticipantTwoId(), match.getParticipantTwoId());
        assertEquals(testMatch.getScoreParticipantOne(), match.getScoreParticipantOne());
        assertEquals(testMatch.getScoreParticipantTwo(), match.getScoreParticipantTwo());
        assertEquals(testMatch.getMatchScore(), match.getMatchScore());
    }

    @Test
    public void getMatchList_emptyList() throws Exception {
        int matchId;
        matchId = testMatch.getMatchId();
        int groupId = testGroup.getGroupId();

        // Testing method
        matchService.deleteMatch(matchId);

        // Testing method
        List<Match> matchList = matchService.getMatchListByGroup(groupId);

        assertNotNull(matchList);
        assertEquals(0, matchList.size());
    }

    @Test
    public void getMatchList_found() throws Exception {
        int groupId;
        groupId = testGroup.getGroupId();

        // Testing method
        List<Match> matchList = matchService.getMatchListByGroup(groupId);

        assertNotNull(matchList);
        assertEquals(1, matchList.size());

        Match match = matchList.get(0);
        assertEquals(testMatch.getMatchId(), match.getMatchId());
        assertEquals(testMatch.getGroupId(), match.getGroupId());
        assertEquals(testMatch.getParticipantOneId(), match.getParticipantOneId());
        assertEquals(testMatch.getParticipantTwoId(), match.getParticipantTwoId());
        assertEquals(testMatch.getScoreParticipantOne(), match.getScoreParticipantOne());
        assertEquals(testMatch.getScoreParticipantTwo(), match.getScoreParticipantTwo());
        assertEquals(testMatch.getMatchScore(), match.getMatchScore());
    }

    @Test
    public void updateMatch() throws Exception {
        int matchId;
        matchId = testMatch.getMatchId();
        int groupId = testGroup.getGroupId();
        int participantOneId = 10;
        int participantTwoId = 15;
        int scoreParticipantOne = 20;
        int scoreParticipantTwo = 30;
        int matchScore = 3;

        // Testing method
        Match match = DataHelper.createRandomMatch();
        match.setMatchId(matchId);
        match.setGroupId(groupId);
        match.setParticipantOneId(participantOneId);
        match.setParticipantTwoId(participantTwoId);
        match.setScoreParticipantOne(scoreParticipantOne);
        match.setScoreParticipantTwo(scoreParticipantTwo);
        match.setMatchScore(matchScore);

        // Testing method
        matchService.updateMatch(matchId, match);
        testMatch = matchService.getMatchById(matchId);

        assertEquals(testMatch.getMatchId(), match.getMatchId());
        assertEquals(testMatch.getGroupId(), match.getGroupId());
        assertEquals(testMatch.getParticipantOneId(), match.getParticipantOneId());
        assertEquals(testMatch.getParticipantTwoId(), match.getParticipantTwoId());
        assertEquals(testMatch.getScoreParticipantOne(), match.getScoreParticipantOne());
        assertEquals(testMatch.getScoreParticipantTwo(), match.getScoreParticipantTwo());
        assertEquals(testMatch.getMatchScore(), match.getMatchScore());

    }

    @Test(expected = RuntimeException.class)
    public void deleteMatch_notFound() throws Exception {
        // Testing method
        matchService.deleteMatch(NON_EXISTING_ID);
    }

    @Test
    public void deleteMatch_found() throws Exception {
        int matchId = testMatch.getMatchId();

        // Testing method
        matchService.deleteMatch(matchId);
    }

    @Test
    public void deleteAll() throws Exception {
        int groupId = testGroup.getGroupId();

        // Testing method
        matchService.deleteAll();

        List<Match> matchList = matchService.getMatchListByGroup(groupId);

       assertEquals(0, matchList.size());
    }

    // endregion
}