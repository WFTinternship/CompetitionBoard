package com.workfront.intern.cb.dao.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.*;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.GroupDao;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.ParticipantDao;
import com.workfront.intern.cb.dao.TournamentDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings({"SpringJavaAutowiredMembersInspection", "unchecked"})
public class ParticipantDaoIntegrationTest extends BaseTest {

    // DAO instances
    @Autowired
    private ParticipantDao participantDao;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private TournamentDao tournamentDao;
    @Autowired
    private GroupDao groupDao;

    private Manager testManager;
    private Tournament testTournament;
    private Member testMember;
    private Team testTeam;
    private Group testGroup;

    @Before
    public void beforeTest() throws Exception {
        // Delete all remaining objects
        cleanUp();

        // Initialize random MANAGER instance
        testManager = createRandomManager();
        assertEquals(0, testManager.getId());
        // Save to DB
        managerDao.addManager(testManager);
        assertTrue(testManager.getId() > 0);

        // Initialize random TOURNAMENT instance
        testTournament = createRandomTournament();
        testTournament.setManagerId(testManager.getId());
        assertEquals(0, testTournament.getTournamentId());
        // Save to DB
        tournamentDao.addTournament(testTournament);
        assertTrue(testTournament.getTournamentId() > 0);

        // Initialize random GROUP instance
        testGroup = createRandomGroup();
        testGroup.setTournamentId(testTournament.getTournamentId());
        assertEquals(0, testGroup.getGroupId());
        // Save to DB
        groupDao.addGroup(testGroup);
        assertTrue(testGroup.getGroupId() > 0);

        // Initialize random MEMBER instance
        testMember = createRandomMember();
        testMember.setTournamentId(testTournament.getTournamentId());
        // Save to DB
        participantDao.addParticipant(testMember);
        assertTrue(testMember.getId() > 0);

        groupDao.assignParticipant(testTournament.getTournamentId(), testGroup.getGroupId(), testMember);
        assertTrue(testMember.getId() > 0);

    }

    @After
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        participantDao.deleteAll(Member.class);
        participantDao.deleteAll(Team.class);
        groupDao.removeAll();
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
    @Ignore
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
    @Ignore
    @Test
    public void getMembersByGroupWithRelativeObjects_emptyList() throws Exception {
        int groupId = testGroup.getGroupId();

        // Testing method
        cleanUp();

        // Testing method
        List<Member> memberList = (List<Member>) participantDao.
                getParticipantListByGroupWithRelativeObjects(Member.class, groupId);

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }
    @Ignore
    @Test
    public void getMemberListByTournamentId_emptyList() throws Exception {
        int tournamentId = testTournament.getTournamentId();

        // Testing method
        cleanUp();

        // Testing method
        List<Member> memberList = (List<Member>) participantDao.getParticipantListByTournamentId(Member.class, tournamentId);

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }
    @Ignore
    @Test
    public void getMemberListByTournamentId_found() throws Exception {
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
    @Ignore
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
    @Ignore
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
    @Ignore
    @Test
    public void getMemberListByMemberName_emptyList() throws Exception {
        int targetId = testMember.getId();

        // Testing method
        participantDao.delete(targetId);

        // Testing method
        List<Member> memberList = (List<Member>) participantDao.getParticipantListByName(Member.class, NON_EXISTING_PARTICIPANT_NAME );

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }
    @Ignore
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
    @Ignore
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
    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void deleteMember_notFound() throws Exception {
        // Testing method
        participantDao.delete(NON_EXISTING_ID);
    }
    @Ignore
    @Test
    public void deleteMember_found() throws Exception {
        int targetId = testMember.getId();

        // Testing method
        participantDao.delete(targetId);
    }
    @Ignore
    @Test
    public void deleteAllMembers() throws Exception {
        // Testing method
        participantDao.deleteAll(Member.class);
    }
    // endregion

    // region <TEAM>
    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void getTeamId_notFound() throws Exception {
        // Testing method
        Participant team = participantDao.getOne(Team.class, NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, team);
    }
    @Ignore
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
    @Ignore
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
    @Ignore
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
    @Ignore
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
    @Ignore
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
    @Ignore
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
    @Ignore
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
    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void deleteTeam_notFound() throws Exception {
        // Testing method
        participantDao.delete(NON_EXISTING_ID);
    }
    @Ignore
    @Test
    public void deleteTeam_found() throws Exception {
        int targetId = testTeam.getId();

        // Testing method
        participantDao.delete(targetId);
    }
    @Ignore
    @Test
    public void deleteAllTeams() throws Exception {
        // Testing method
        participantDao.deleteAll(Member.class);
    }

    // endregion
}