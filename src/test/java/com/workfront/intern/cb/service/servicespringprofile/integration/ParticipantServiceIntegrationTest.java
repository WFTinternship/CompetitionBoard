package com.workfront.intern.cb.service.servicespringprofile.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.*;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.service.GroupService;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.service.ParticipantService;
import com.workfront.intern.cb.service.TournamentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.workfront.intern.cb.DataHelper.*;
import static org.junit.Assert.*;

@Component
public class ParticipantServiceIntegrationTest extends BaseTest {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ParticipantService participantService;

    @Before
    public void beforeTest() throws Exception {
        // Delete all remaining objects
        cleanUp();

        // Initialize random MANAGER instance
        initManagers();

        // Initialize random TOURNAMENT instance
        initTournaments();

        // Initialize random GROUP instance
        initGroups();

        // Initialize random MEMBER instance
        initMemberParticipants();

        // Initialize random TEAM instance
        initTeamParticipants();

        groupDao.assignParticipant(testTournament1.getTournamentId(), testGroup1.getGroupId(), testMember1);
        groupDao.assignParticipant(testTournament1.getTournamentId(), testGroup1.getGroupId(), testMember2);

        groupDao.assignParticipant(testTournament2.getTournamentId(), testGroup2.getGroupId(), testTeam1);
        groupDao.assignParticipant(testTournament2.getTournamentId(), testGroup2.getGroupId(), testTeam2);
    }

    // endregion

    @After
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        groupDao.removeAll();
        participantService.deleteAll(Member.class);
        participantService.deleteAll(Team.class);
        groupService.deleteAll();
        tournamentService.deleteAll();
        managerService.deleteAll();
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
        targetId = testMember1.getId();

        // Testing method
        Member member = (Member) participantService.getOne(Member.class, targetId);

        assertNotNull(member);
        assertEquals(testMember1.getId(), member.getId());
        assertEquals(testMember1.getAvatar(), member.getAvatar());
        assertEquals(testMember1.getParticipantInfo(), member.getParticipantInfo());
        assertEquals(testMember1.getSurName(), member.getSurName());
        assertEquals(testMember1.getPosition(), member.getPosition());
        assertEquals(testMember1.getEmail(), member.getEmail());
        assertEquals(testMember1.getTournamentId(), member.getTournamentId());
    }

    @Test
    public void getMemberList_emptyList() throws Exception {
        groupDao.removeAll();

        // Testing method
        participantService.delete(testMember1.getId());
        participantService.delete(testMember2.getId());

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
        assertEquals(2, memberList.size());
        sortById(memberList);

        Member member1 = memberList.get(0);
        Member member2 = memberList.get(1);

        compareMembers(testMember1, member1);
        compareMembers(testMember2, member2);
    }

    @Test
    public void getMemberListByTournamentId_emptyList() throws Exception {
        int targetId;
        targetId = testTournament1.getTournamentId();

        // Testing method
        cleanUp();

        // Testing method
        List<Member> memberList = (List<Member>) participantService.getParticipantsByTournamentId(Member.class, targetId);

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }

    @Test
    public void getMemberListByTournamentI_found() throws Exception {
        int targetId = testTournament1.getTournamentId();

        // Testing method
        List<Member> memberList = (List<Member>) participantService.getParticipantsByTournamentId(Member.class, targetId);

        assertNotNull(memberList);
        assertEquals(2, memberList.size());
        sortById(memberList);

        Member member1 = memberList.get(0);
        Member member2 = memberList.get(1);

        compareMembers(testMember1, member1);
        compareMembers(testMember2, member2);
    }

    @Test
    public void addMember_created() throws Exception {
        int targetId;
        targetId = testTournament1.getTournamentId();

        // Initialize random manager instance
        Member member = createRandomMember();
        member.setTournamentId(targetId);

        assertEquals(0, member.getId());

        // Testing method
        participantService.addParticipant(member);
        assertTrue(member.getId() > 0);
    }

    @Test
    public void updateMember() throws Exception {
        int targetId = testMember2.getId();
        String newEmail = "new-" + testMember2.getEmail();
        String newPosition = "new-" + testMember2.getPosition();
        String newAvatar = "new-" + testMember2.getAvatar();
        String newInfo = "new-" + testMember2.getParticipantInfo();

        Member member = (Member)participantDao.getOne(Member.class, targetId);
        member.setEmail(newEmail);
        member.setPosition(newPosition);
        member.setAvatar(newAvatar);
        member.setParticipantInfo(newInfo);

        // Testing method
        participantService.update(targetId, member);

        // Initialize random manager instance
        member = (Member) participantService.getOne(Member.class, targetId);

        assertEquals(targetId, member.getId());
        assertEquals(newAvatar, member.getAvatar());
        assertEquals(newInfo, member.getParticipantInfo());
        assertEquals(newPosition, member.getPosition());
        assertEquals(newEmail, member.getEmail());
    }

    @Test(expected = RuntimeException.class)
    public void deleteMember_notFound() throws Exception {
        // Testing method
        participantService.delete(NON_EXISTING_ID);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void deleteMember_found() throws Exception {
        groupService.removeAllParticipants(testGroup1.getGroupId());
        int targetId = testMember1.getId();

        // Testing method
        participantService.delete(targetId);

        Participant deleted = participantDao.getOne(Member.class, targetId);
        assertNull(deleted);
    }

    @Test
    public void deleteAllMembers() throws Exception {
        groupDao.removeAll();

        // Testing method
        participantService.deleteAll(Member.class);

        List members = participantDao.getAll(Member.class);
        assertNotNull(members);
        assertEquals(0, members.size());
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
        targetId = testTeam1.getId();

        // Testing method
        Team team = (Team) participantService.getOne(Team.class, targetId);

        assertNotNull(team);
        compareTeams(testTeam1, team);
    }

    @Test
    public void getTeamList_emptyList() throws Exception {
        groupDao.removeAll();

        // Testing method
        participantService.delete(testTeam1.getId());
        participantService.delete(testTeam2.getId());

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
        assertEquals(2, teamList.size());
        sortById(teamList);

        Team team1 = teamList.get(0);
        Team team2 = teamList.get(1);

        compareTeams(testTeam1, team1);
        compareTeams(testTeam2, team2);
    }

    @Test
    public void addTeam_created() throws Exception {
        int targetId;
        targetId = testTournament2.getTournamentId();

        // Initialize random manager instance
        Team team = createRandomTeam();
        team.setTournamentId(targetId);
        assertEquals(0, team.getId());

        // Testing method
        participantService.addParticipant(team);

        assertTrue(team.getId() > 0);
    }

    @Test
    public void updateTeam() throws Exception {
        int targetId = testTeam2.getId();
        String newName = "new-" + testTeam2.getTeamName();
        String newAvatar = "new-" + testTeam2.getAvatar();
        String newInfo = "new-" + testTeam2.getParticipantInfo();

        Team team = (Team)participantDao.getOne(Team.class, targetId);
        team.setTeamName(newName);
        team.setAvatar(newAvatar);
        team.setParticipantInfo(newInfo);

        // Testing method
        participantService.update(targetId, team);

        // Initialize random manager instance
        team = (Team) participantService.getOne(Team.class, targetId);

        assertEquals(targetId, team.getId());
        assertEquals(newAvatar, team.getAvatar());
        assertEquals(newInfo, team.getParticipantInfo());
        assertEquals(newName, team.getTeamName());
    }

    @Test(expected = RuntimeException.class)
    public void deleteTeam_notFound() throws Exception {
        // Testing method
        participantService.delete(NON_EXISTING_ID);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void deleteTeam_found() throws Exception {
        groupService.removeAllParticipants(testGroup2.getGroupId());
        int targetId = testTeam1.getId();

        // Testing method
        participantService.delete(targetId);

        Participant deleted = participantDao.getOne(Team.class, targetId);
        assertNull(deleted);
    }

    @Test
    public void deleteAllTeams() throws Exception {
        groupDao.removeAll();

        // Testing method
        participantService.deleteAll(Team.class);

        List teams = participantDao.getAll(Team.class);
        assertNotNull(teams);
        assertEquals(0, teams.size());
    }

    // endregion

    // region <COMPARE>

    public static void compareMembers(Member expected, Member actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAvatar(), actual.getAvatar());
        assertEquals(expected.getParticipantInfo(), actual.getParticipantInfo());
        assertEquals(expected.getSurName(), actual.getSurName());
        assertEquals(expected.getPosition(), actual.getPosition());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getTournamentId(), actual.getTournamentId());
    }

    public static void compareTeams(Team expected, Team actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAvatar(), actual.getAvatar());
        assertEquals(expected.getParticipantInfo(), actual.getParticipantInfo());
        assertEquals(expected.getTeamName(), actual.getTeamName());
        assertEquals(expected.getTournamentId(), actual.getTournamentId());
    }

    public static void sortById(List<? extends Participant> participantList) {
        Collections.sort(participantList, new Comparator<Participant>() {
            @Override
            public int compare(Participant o1, Participant o2) {
                return o1.getId() - o2.getId();
            }
        });
    }

    // endregion
}

