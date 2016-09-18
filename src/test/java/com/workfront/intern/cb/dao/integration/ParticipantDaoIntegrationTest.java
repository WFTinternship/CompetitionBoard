package com.workfront.intern.cb.dao.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.*;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

public class ParticipantDaoIntegrationTest extends BaseTest {

    // DAO instances
    private ManagerDao managerDao;
    private TournamentDao tournamentDao;
    private GroupDao groupDao;
    private ParticipantDao participantDao;

    private Tournament testTournament;
    private Member testMember;
    private Team testTeam;

    private DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() throws Exception {
        managerDao = new ManagerDaoImpl(dataSource);
        tournamentDao = new TournamentDaoImpl(dataSource);
        groupDao = new GroupDaoImpl(dataSource);
        participantDao = new ParticipantDaoImpl(dataSource);

        // Delete all remaining objects
        cleanUp();

        // region <MANAGER>

        // Initialize random manager instance
        Manager testManager = createRandomManager();
        assertEquals(0, testManager.getId());

        // Save to DB
        managerDao.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // endregion

        // region <TOURNAMENT>

        // Initialize random tournament instance
        testTournament = createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());

        // Save to DB
        tournamentDao.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);

        // endregion

        // region <GROUP>

        // Initialize random tournament instance
        Group testGroup = createRandomGroup();
        testGroup.setTournamentId(testTournament.getTournamentId());
        assertEquals(0, testGroup.getGroupId());

        // Save to DB
        groupDao.addGroup(testGroup);
        assertTrue(testGroup.getGroupId() > 0);

        // endregion

        // region <MEMBER>

        // Initialize random member instance
        testMember = createRandomMember();
        testMember.setTournamentId(testTournament.getTournamentId());
        assertEquals(0, testMember.getId());

        // Save to DB
        participantDao.addParticipant(testMember);
        assertTrue(testMember.getId() > 0);

        // endregion

        // region <TEAM>

        // Initialize random team instance
        testTeam = createRandomTeam();
        testTeam.setTournamentId(testTournament.getTournamentId());
        assertEquals(0, testTeam.getId());

        // Save to DB
        participantDao.addParticipant(testTeam);
        assertTrue(testTeam.getId() > 0);

        // endregion
    }

    @After
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        participantDao.deleteAll(Member.class);
        participantDao.deleteAll(Team.class);
        groupDao.deleteAll();
        tournamentDao.deleteAll();
        managerDao.deleteAll();
    }

    // region <MEMBER>

    @Test(expected = ObjectNotFoundException.class)
    public void getMemberId_notFound() throws Exception {
        // Testing method
        Participant member = participantDao.getOne(Member.class, NON_EXISTING_ID);
        assertNull(MESSAGE_TEST_COMPLETED_ERROR, member);
    }

    @Test
    public void getMemberById_found() throws Exception {
        int targetId = testMember.getId();

        // Testing method
        Member member = (Member) participantDao.getOne(Member.class, targetId);

        assertNotNull(member);
        assertEquals(testMember.getId(), member.getId());
        assertEquals(testMember.getAvatar(), member.getAvatar());
        assertEquals(testMember.getParticipantInfo(), member.getParticipantInfo());
        assertEquals(testMember.getSurName(), member.getSurName());
        assertEquals(testMember.getPosition(), member.getPosition());
        assertEquals(testMember.getEmail(), member.getEmail());
        assertEquals(testMember.getTournamentId(), member.getTournamentId());
    }

    @Test
    public void getMemberListByTournamentId_emptyList() throws Exception {
        int targetId = testTournament.getTournamentId();

        // Testing method
        cleanUp();

        // Testing method
        List<Member> memberList = (List<Member>) participantDao.getParticipantListByTournamentId(Member.class, targetId);

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }

    @Test
    public void getMemberListByTournamentI_found() throws Exception {
        int targetId;
        targetId = testTournament.getTournamentId();

        // Testing method
        List<Member> memberList = (List<Member>) participantDao.getParticipantListByTournamentId(Member.class, targetId);

        assertNotNull(memberList);
        assertEquals(1, memberList.size());

        Member member = memberList.get(0);
        assertEquals(testMember.getId(), member.getId());
        assertEquals(testMember.getAvatar(), member.getAvatar());
        assertEquals(testMember.getParticipantInfo(), member.getParticipantInfo());
        assertEquals(testMember.getSurName(), member.getSurName());
        assertEquals(testMember.getPosition(), member.getPosition());
        assertEquals(testMember.getEmail(), member.getEmail());
        assertEquals(testMember.getTournamentId(), member.getTournamentId());
    }

    @Test
    public void getMemberList_emptyList() throws Exception {
        int targetId = testMember.getId();

        // Testing method
        participantDao.delete(targetId);

        // Testing method
        List<Member> memberList = (List<Member>) participantDao.getAll(Member.class);

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }

    @Test
    public void getMemberList_found() throws Exception {
        // Testing method
        List<Member> memberList = (List<Member>) participantDao.getAll(Member.class);

        assertNotNull(memberList);
        assertEquals(1, memberList.size());

        Member member = memberList.get(0);
        assertEquals(testMember.getId(), member.getId());
        assertEquals(testMember.getAvatar(), member.getAvatar());
        assertEquals(testMember.getParticipantInfo(), member.getParticipantInfo());
        assertEquals(testMember.getSurName(), member.getSurName());
        assertEquals(testMember.getPosition(), member.getPosition());
        assertEquals(testMember.getEmail(), member.getEmail());
        assertEquals(testMember.getTournamentId(), member.getTournamentId());
    }

    @Test
    public void addMember_created() throws Exception {
        int targetId = testTournament.getTournamentId();

        // Initialize random manager instance
        Member member = createRandomMember();
        member.setTournamentId(targetId);

        assertEquals(0, member.getId());

        // Testing method
        participantDao.addParticipant(member);
        assertTrue(member.getId() > 0);
    }

    @Test
    public void updateMember() throws Exception {
        int targetId = testMember.getId();

        // Testing method
        participantDao.update(targetId, testMember);

        // Initialize random manager instance
        Member member = (Member) participantDao.getOne(Member.class, targetId);

        assertEquals(testMember.getId(), member.getId());
        assertEquals(testMember.getAvatar(), member.getAvatar());
        assertEquals(testMember.getParticipantInfo(), member.getParticipantInfo());
        assertEquals(testMember.getSurName(), member.getSurName());
        assertEquals(testMember.getPosition(), member.getPosition());
        assertEquals(testMember.getEmail(), member.getEmail());
        assertEquals(testMember.getTournamentId(), member.getTournamentId());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void deleteMember_notFound() throws Exception {
        // Testing method
        participantDao.delete(NON_EXISTING_ID);
    }

    @Test
    public void deleteMember_found() throws Exception {
        int targetId = testMember.getId();

        // Testing method
        participantDao.delete(targetId);
    }

    @Test
    public void deleteAllMembers() throws Exception {
        // Testing method
        participantDao.deleteAll(Member.class);
    }
    // endregion

    // region <TEAM>

    @Test(expected = ObjectNotFoundException.class)
    public void getTeamId_notFound() throws Exception {
        // Testing method
        Participant team = participantDao.getOne(Team.class, NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, team);
    }

    @Test
    public void getTeamId_found() throws Exception {
        int targetId = testTeam.getId();

        // Testing method
        Team team = (Team) participantDao.getOne(Team.class, targetId);

        assertNotNull(team);
        assertEquals(testTeam.getId(), team.getId());
        assertEquals(testTeam.getAvatar(), testTeam.getAvatar());
        assertEquals(testTeam.getParticipantInfo(), testTeam.getParticipantInfo());
        assertEquals(testTeam.getTeamName(), team.getTeamName());
        assertEquals(testTeam.getTournamentId(), team.getTournamentId());
    }

    @Test
    public void getTeamListByTournamentId_emptyList() throws Exception {
        int targetId = testTournament.getTournamentId();

        // Testing method
        cleanUp();

        // Testing method
        List<Team> teamList = (List<Team>) participantDao.getParticipantListByTournamentId(Team.class, targetId);

        assertNotNull(teamList);
        assertEquals(0, teamList.size());
    }

    @Test
    public void getTeamListByTournamentI_found() throws Exception {
        int targetId = testTournament.getTournamentId();

        // Testing method
        List<Team> teamList = (List<Team>) participantDao.getParticipantListByTournamentId(Team.class, targetId);

        assertNotNull(teamList);
        assertEquals(1, teamList.size());

        Team team = teamList.get(0);
        assertEquals(testTeam.getId(), team.getId());
        assertEquals(testTeam.getAvatar(), team.getAvatar());
        assertEquals(testTeam.getParticipantInfo(), team.getParticipantInfo());
        assertEquals(testTeam.getTeamName(), team.getTeamName());
        assertEquals(testTeam.getTournamentId(), team.getTournamentId());
    }

    @Test
    public void getTeamList_emptyList() throws Exception {
        int targetId = testTeam.getId();

        // Testing method
        participantDao.delete(targetId);

        // Testing method
        List<Team> teamList = (List<Team>) participantDao.getAll(Team.class);

        assertNotNull(teamList);
        assertEquals(0, teamList.size());
    }

    @Test
    public void getTeamList_found() throws Exception {
        // Testing method
        List<Team> teamList = (List<Team>) participantDao.getAll(Team.class);

        assertNotNull(teamList);
        assertEquals(1, teamList.size());

        Team team = teamList.get(0);
        assertEquals(testTeam.getId(), team.getId());
        assertEquals(testTeam.getAvatar(), testTeam.getAvatar());
        assertEquals(testTeam.getParticipantInfo(), testTeam.getParticipantInfo());
        assertEquals(testTeam.getTeamName(), team.getTeamName());
        assertEquals(testTeam.getTournamentId(), team.getTournamentId());
    }

    @Test
    public void addTeam_created() throws Exception {
        int targetId = testTournament.getTournamentId();

        // Initialize random manager instance
        Team team = createRandomTeam();
        team.setTournamentId(targetId);
        assertEquals(0, team.getId());

        // Testing method
        participantDao.addParticipant(team);

        assertTrue(team.getId() > 0);
    }

    @Test
    public void updateTeam() throws Exception {
        int targetId = testTeam.getId();

        // Testing method
        participantDao.update(targetId, testTeam);

        // Initialize random manager instance
        Team team = (Team) participantDao.getOne(Team.class, targetId);

        assertEquals(testTeam.getId(), team.getId());
        assertEquals(testTeam.getAvatar(), testTeam.getAvatar());
        assertEquals(testTeam.getParticipantInfo(), testTeam.getParticipantInfo());
        assertEquals(testTeam.getTeamName(), team.getTeamName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void deleteTeam_notFound() throws Exception {
        // Testing method
        participantDao.delete(NON_EXISTING_ID);
    }

    @Test
    public void deleteTeam_found() throws Exception {
        int targetId = testTeam.getId();

        // Testing method
        participantDao.delete(targetId);
    }

    @Test
    public void deleteAllTeams() throws Exception {
        // Testing method
        participantDao.deleteAll(Member.class);
    }

    // endregion
}