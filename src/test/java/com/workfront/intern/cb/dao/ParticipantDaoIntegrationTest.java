package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

public class ParticipantDaoIntegrationTest extends BaseTest {

    // DAO instances
    private ParticipantDao participantDao;

    // Test helper objects
    private Member testMember;
    private Team testTeam;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() {
        participantDao = new ParticipantDaoImpl(dataSource);
        cleanUp();

        testMember = createRandomMember();
        assertEquals(0, testMember.getId());
        participantDao.addParticipant(testMember);
        assertTrue(testMember.getId() > 0);

        testTeam = createRandomTeam();
        assertEquals(0, testTeam.getId());
        participantDao.addParticipant(testTeam);
        assertTrue(testTeam.getId() > 0);
    }

    @After
    public void afterTest() {
        cleanUp();
    }

    private void cleanUp(){
        participantDao.deleteAll(Member.class);
        participantDao.deleteAll(Team.class);
    }

    // region <MEMBER>

    @Test
    public void getMemberId_notFound() {
        // Testing method
        Participant member = participantDao.getOne(Member.class, NON_EXISTING_ID);
        assertNull(MESSAGE_TEST_COMPLETED_ERROR, member);
    }

    @Test
    public void getMemberById_found() {
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
    }

    @Test
    public void getMemberList_emptyList() {
        int targetId = testMember.getId();

        // Testing method
        boolean deleted = participantDao.delete(Member.class, targetId);
        assertTrue(deleted);

        // Testing method
        List<Member> memberList = (List<Member>) participantDao.getAll(Member.class);

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }

    @Test
    public void getMemberList_found() {
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
    }

    @Test
    public void addMember_created() {
        // Initialize random manager instance
        Member member = createRandomMember();
        assertEquals(0, member.getId());

        // Testing method
        boolean added = participantDao.addParticipant(member);

        assertTrue(added);
        assertTrue(member.getId() > 0);
    }

    @Test
    public void updateMember() {
        int targetId = testMember.getId();

        // Testing method
        boolean update = participantDao.update(testMember);
        assertTrue(update);

        // Initialize random manager instance
        Member member = (Member) participantDao.getOne(Member.class, targetId);

        assertEquals(testMember.getId(), member.getId());
        assertEquals(testMember.getAvatar(), member.getAvatar());
        assertEquals(testMember.getParticipantInfo(), member.getParticipantInfo());
        assertEquals(testMember.getSurName(), member.getSurName());
        assertEquals(testMember.getPosition(), member.getPosition());
        assertEquals(testMember.getEmail(), member.getEmail());
    }

    @Test
    public void deleteMember_notFound() {
        // Testing method
        boolean deleted = participantDao.delete(Member.class, NON_EXISTING_ID);
        assertFalse(deleted);
    }

    @Test
    public void deleteMember_found() {
        int targetId = testMember.getId();

        // Testing method
        boolean deleted = participantDao.delete(Member.class, targetId);
        assertTrue(deleted);
    }

    @Test
    public void deleteAllMembers() {
        // Testing method
        boolean deleted = participantDao.deleteAll(Member.class);
        assertTrue(deleted);
    }

    // endregion

    // region <TEAM>

    @Test
    public void getTeamId_notFound() {
        // Testing method
        Participant team = participantDao.getOne(Team.class, NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, team);
    }

    @Test
    public void getTeamId_found() {
        int targetId = testTeam.getId();

        // Testing method
        Team team = (Team) participantDao.getOne(Team.class, targetId);

        assertNotNull(team);
        assertEquals(testTeam.getId(), team.getId());
        assertEquals(testTeam.getAvatar(), testTeam.getAvatar());
        assertEquals(testTeam.getParticipantInfo(), testTeam.getParticipantInfo());
        assertEquals(testTeam.getTeamName(), team.getTeamName());
    }

    @Test
    public void getTeamList_emptyList() {
        int targetId = testTeam.getId();

        // Testing method
        boolean deleted = participantDao.delete(Team.class, targetId);
        assertTrue(deleted);

        // Testing method
        List<Team> teamList = (List<Team>) participantDao.getAll(Team.class);

        assertNotNull(teamList);
        assertEquals(0, teamList.size());
    }

    @Test
    public void getTeamList_found() {
        // Testing method
        List<Team> teamList = (List<Team>) participantDao.getAll(Team.class);

        assertNotNull(teamList);
        assertEquals(1, teamList.size());

        Team team = teamList.get(0);
        assertEquals(testTeam.getId(), team.getId());
        assertEquals(testTeam.getAvatar(), testTeam.getAvatar());
        assertEquals(testTeam.getParticipantInfo(), testTeam.getParticipantInfo());
        assertEquals(testTeam.getTeamName(), team.getTeamName());
    }

    @Test
    public void addTeam_created() {
        // Initialize random manager instance
        Team team = createRandomTeam();
        assertEquals(0, team.getId());

        // Testing method
        boolean added = participantDao.addParticipant(team);

        assertTrue(added);
        assertTrue(team.getId() > 0);
    }

    @Test
    public void updateTeam() {
        int targetId = testTeam.getId();

        // Testing method
        boolean update = participantDao.update(testTeam);
        assertTrue(update);

        // Initialize random manager instance
        Team team = (Team) participantDao.getOne(Team.class, targetId);

        assertEquals(testTeam.getId(), team.getId());
        assertEquals(testTeam.getAvatar(), testTeam.getAvatar());
        assertEquals(testTeam.getParticipantInfo(), testTeam.getParticipantInfo());
        assertEquals(testTeam.getTeamName(), team.getTeamName());
    }

    @Test
    public void deleteTeam_notFound() {
        // Testing method
        boolean deleted = participantDao.delete(Member.class, NON_EXISTING_ID);
        assertFalse(deleted);
    }

    @Test
    public void deleteTeam_found() {
        int targetId = testTeam.getId();

        // Testing method
        boolean deleted = participantDao.delete(Team.class, targetId);
        assertTrue(deleted);
    }

    @Test
    public void deleteAllTeams() {
        // Testing method
        boolean deleted = participantDao.deleteAll(Member.class);
        assertTrue(deleted);
    }

    // endregion
}