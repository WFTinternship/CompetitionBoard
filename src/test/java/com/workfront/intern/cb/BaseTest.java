package com.workfront.intern.cb;

import com.workfront.intern.cb.common.*;
import com.workfront.intern.cb.dao.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.workfront.intern.cb.DataHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceSpringConfigTest.class)
@ActiveProfiles("test")
public class BaseTest {
    protected final int NON_EXISTING_ID = 9999;
    protected final String NON_EXISTING_LOGIN = "adgOwkJ";
    protected final String NON_EXISTING_PARTICIPANT_NAME = "aksMsaa";
    protected final String NON_EXISTING_GROUP = "adgjOwkJ";
    protected final String TOURNAMENT_NAME = "FIFA 2025";
    protected final String GROUP_NAME = "FIFA 2025";
    protected final String MESSAGE_TEST_COMPLETED_OK = "Test completed successfully!";
    protected final String MESSAGE_TEST_COMPLETED_ERROR = "Test completed with errors :(";

    // DAO instances
    @Autowired
    protected ManagerDao managerDao;
    @Autowired
    protected TournamentDao tournamentDao;
    @Autowired
    protected GroupDao groupDao;
    @Autowired
    protected ParticipantDao participantDao;
    @Autowired
    protected MatchDao matchDao;
    @Autowired
    protected MediaDao mediaDao;

    // Test business objects
    protected Manager testManager;
    protected Tournament testTournament1;
    protected Group testGroup1;
    protected Member testMember1;
    protected Member testMember2;
    protected Tournament testTournament2;
    protected Group testGroup2;
    protected Team testTeam1;
    protected Team testTeam2;

    // region <MANAGER>

    protected void initManagers() throws Exception {
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());
        // Save to DB
        managerDao.addManager(testManager);
        assertTrue(testManager.getId() > 0);
    }

    protected Manager initManager() throws Exception {
        Manager testManager = createRandomManager();
        assertEquals(0, testManager.getId());
        // Save to DB
        managerDao.addManager(testManager);
        assertTrue(testManager.getId() > 0);
        return testManager;
    }

    // endregion

    // region <TOURNAMENT>

    protected void initTournaments() throws Exception {
        // Init personal tournament (T1)
        testTournament1 = createRandomTournament();
        testTournament1.setManagerId(testManager.getId());
        assertEquals(0, testTournament1.getTournamentId());
        // Save to DB
        tournamentDao.addTournament(testTournament1);
        assertTrue(testTournament1.getTournamentId() > 0);
        // Init team tournament (T2)
        testTournament2 = createRandomTournament();
        testTournament2.setManagerId(testManager.getId());
        assertEquals(0, testTournament2.getTournamentId());
        // Save to DB
        tournamentDao.addTournament(testTournament2);
        assertTrue(testTournament2.getTournamentId() > 0);
    }

    protected Tournament initTournament() throws Exception {
        Tournament testTournament = createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());
        // Save to DB
        tournamentDao.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);
        return testTournament;
    }

    // endregion

    // region <GROUP>

    protected void initGroups() throws Exception {
        // Init Group
        testGroup1 = createRandomGroup();
        testGroup1.setTournamentId(testTournament1.getTournamentId());
        assertEquals(0, testGroup1.getGroupId());
        // Save to DB
        groupDao.addGroup(testGroup1);
        assertTrue(testGroup1.getGroupId() > 0);
        // Init Group
        testGroup2 = createRandomGroup();
        testGroup2.setTournamentId(testTournament2.getTournamentId());
        assertEquals(0, testGroup2.getGroupId());
        // Save to DB
        groupDao.addGroup(testGroup2);
        assertTrue(testGroup2.getGroupId() > 0);
    }

    protected Group initGroup(int tournamentId) throws Exception {
        Group testGroup = createRandomGroup();
        testGroup.setTournamentId(tournamentId);
        assertEquals(0, testGroup.getGroupId());
        // Save to DB
        groupDao.addGroup(testGroup);
        assertTrue(testGroup.getGroupId() > 0);
        return testGroup;
    }

    // endregion

    // region <PARTICIPANT : MEMBER>

    protected void initMemberParticipants() throws Exception {
        // Init Member
        testMember1 = createRandomMember();
        testMember1.setTournamentId(testTournament1.getTournamentId());
        // Save to DB
        participantDao.addParticipant(testMember1);
        assertTrue(testMember1.getId() > 0);
        // Init Member
        testMember2 = createRandomMember();
        testMember2.setTournamentId(testTournament1.getTournamentId());
        // Save to DB
        participantDao.addParticipant(testMember2);
        assertTrue(testMember2.getId() > 0);
    }

    protected Member initMemberParticipant(int tournamentId) throws Exception {
        Member testMember = createRandomMember();
        testMember.setTournamentId(tournamentId);
        // Save to DB
        participantDao.addParticipant(testMember);
        assertTrue(testMember.getId() > 0);
        return testMember;
    }

    // endregion

    // region <PARTICIPANT : TEAM>

    protected void initTeamParticipants() throws Exception {
        // Init Team
        testTeam1 = createRandomTeam();
        testTeam1.setTournamentId(testTournament2.getTournamentId());
        // Save to DB
        participantDao.addParticipant(testTeam1);
        assertTrue(testTeam1.getId() > 0);
        // Init Team
        testTeam2 = createRandomTeam();
        testTeam2.setTournamentId(testTournament2.getTournamentId());
        // Save to DB
        participantDao.addParticipant(testTeam2);
        assertTrue(testTeam2.getId() > 0);
    }

    protected Team initTeamParticipant(int tournamentId) throws Exception {
        Team testTeam = createRandomTeam();
        testTeam.setTournamentId(tournamentId);
        // Save to DB
        participantDao.addParticipant(testTeam);
        assertTrue(testTeam.getId() > 0);
        return testTeam;
    }

    // endregion

    // region <MATCH>

    // endregion

    // region <MEDIA>

    // endregion

}