package com.workfront.intern.cb.service.servicespringprofile.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.Team;
import com.workfront.intern.cb.service.ParticipantService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ParticipantServiceIntegrationTest extends BaseTest {

    @Autowired
    private ParticipantService participantService;

    // Test helper objects
    private Member testMember;
    private Team testTeam;

    @Before
    public void beforeTest() throws Exception {
        // Delete all remaining objects
        cleanUp();

        // Initialize random member instance
        testMember = createRandomMember();
        assertEquals(0, testMember.getId());
        participantService.addParticipant(testMember);
        assertTrue(testMember.getId() > 0);

        // Initialize random team instance
        testTeam = createRandomTeam();
        assertEquals(0, testTeam.getId());
        participantService.addParticipant(testTeam);
        assertTrue(testTeam.getId() > 0);
    }

    @After
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        participantService.deleteAll(Member.class);
        participantService.deleteAll(Team.class);
    }

    // region <MEMBER>

    @Test(expected = RuntimeException.class)
    public void getMemberId_notFound() throws Exception {
        // Testing method
        Participant member = participantService.getOne(Member.class, NON_EXISTING_ID);
        assertNull(MESSAGE_TEST_COMPLETED_ERROR, member);
    }

    @Test
    public void getMemberById_found() throws Exception {
        int targetId;
        targetId = testMember.getId();

        // Testing method
        Member member = (Member) participantService.getOne(Member.class, targetId);

        assertNotNull(member);
        assertEquals(testMember.getId(), member.getId());
        assertEquals(testMember.getAvatar(), member.getAvatar());
        assertEquals(testMember.getParticipantInfo(), member.getParticipantInfo());
        assertEquals(testMember.getSurName(), member.getSurName());
        assertEquals(testMember.getPosition(), member.getPosition());
        assertEquals(testMember.getEmail(), member.getEmail());
    }

    @Test
    public void getMemberList_emptyList() throws Exception {
        int targetId;
        targetId = testMember.getId();

        // Testing method
        participantService.delete(targetId);

        // Testing method
        List<Member> memberList = (List<Member>) participantService.getAll(Member.class);

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }

    @Test
    public void getMemberList_found() throws Exception {
        // Testing method
        List<Member> memberList;
        memberList = (List<Member>) participantService.getAll(Member.class);

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
    public void addMember_created() throws Exception {
        // Initialize random manager instance
        Member member = createRandomMember();
        assertEquals(0, member.getId());

        // Testing method
        participantService.addParticipant(member);
        assertTrue(member.getId() > 0);
    }

    @Test
    public void updateMember() throws Exception {
        int targetId;
        targetId = testMember.getId();

        // Testing method
        participantService.update(targetId, testMember);

        // Initialize random manager instance
        Member member = (Member) participantService.getOne(Member.class, targetId);

        assertEquals(testMember.getId(), member.getId());
        assertEquals(testMember.getAvatar(), member.getAvatar());
        assertEquals(testMember.getParticipantInfo(), member.getParticipantInfo());
        assertEquals(testMember.getSurName(), member.getSurName());
        assertEquals(testMember.getPosition(), member.getPosition());
        assertEquals(testMember.getEmail(), member.getEmail());
    }

    @Test(expected = RuntimeException.class)
    public void deleteMember_notFound() throws Exception {
        // Testing method
        participantService.delete(NON_EXISTING_ID);
    }

    @Test
    public void deleteMember_found() throws Exception {
        int targetId = testMember.getId();

        // Testing method
        participantService.delete(targetId);
    }

    @Test
    public void deleteAllMembers() throws Exception {
        // Testing method
        participantService.deleteAll(Member.class);

    }
    // endregion

    // region <TEAM>
    @Test(expected = RuntimeException.class)
    public void getTeamId_notFound() throws Exception {
        // Testing method
        Participant team = participantService.getOne(Team.class, NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, team);
    }

    @Test
    public void getTeamId_found() throws Exception {
        int targetId;
        targetId = testTeam.getId();

        // Testing method
        Team team = (Team) participantService.getOne(Team.class, targetId);

        assertNotNull(team);
        assertEquals(testTeam.getId(), team.getId());
        assertEquals(testTeam.getAvatar(), testTeam.getAvatar());
        assertEquals(testTeam.getParticipantInfo(), testTeam.getParticipantInfo());
        assertEquals(testTeam.getTeamName(), team.getTeamName());
    }

    @Test
    public void getTeamList_emptyList() throws Exception {
        int targetId;
        targetId = testTeam.getId();

        // Testing method
        participantService.delete(targetId);

        // Testing method
        List<Team> teamList = (List<Team>) participantService.getAll(Team.class);

        assertNotNull(teamList);
        assertEquals(0, teamList.size());
    }

    @Test
    public void getTeamList_found() throws Exception {
        // Testing method
        List<Team> teamList;
        teamList = (List<Team>) participantService.getAll(Team.class);

        assertNotNull(teamList);
        assertEquals(1, teamList.size());

        Team team = teamList.get(0);
        assertEquals(testTeam.getId(), team.getId());
        assertEquals(testTeam.getAvatar(), testTeam.getAvatar());
        assertEquals(testTeam.getParticipantInfo(), testTeam.getParticipantInfo());
        assertEquals(testTeam.getTeamName(), team.getTeamName());
    }

    @Test
    public void addTeam_created() throws Exception {
        // Initialize random manager instance
        Team team = createRandomTeam();
        assertEquals(0, team.getId());

        // Testing method
        participantService.addParticipant(team);

        assertTrue(team.getId() > 0);
    }

    @Test
    public void updateTeam() throws Exception {
        int targetId;
        targetId = testTeam.getId();

        // Testing method
        participantService.update(targetId, testTeam);

        // Initialize random manager instance
        Team team = (Team) participantService.getOne(Team.class, targetId);

        assertEquals(testTeam.getId(), team.getId());
        assertEquals(testTeam.getAvatar(), testTeam.getAvatar());
        assertEquals(testTeam.getParticipantInfo(), testTeam.getParticipantInfo());
        assertEquals(testTeam.getTeamName(), team.getTeamName());
    }

    @Test(expected = RuntimeException.class)
    public void deleteTeam_notFound() throws Exception {
        // Testing method
        participantService.delete(NON_EXISTING_ID);
    }

    @Test
    public void deleteTeam_found() throws Exception {
        int targetId = testTeam.getId();

        // Testing method
        participantService.delete(targetId);
    }

    @Test
    public void deleteAllTeams() throws Exception {
        // Testing method
        participantService.deleteAll(Member.class);
    }

    // endregion
}

