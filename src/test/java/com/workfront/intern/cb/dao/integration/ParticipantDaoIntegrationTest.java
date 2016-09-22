package com.workfront.intern.cb.dao.integration;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.DataHelper;
import com.workfront.intern.cb.common.*;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings({"SpringJavaAutowiredMembersInspection", "unchecked"})
public class ParticipantDaoIntegrationTest extends BaseTest {

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

    @After
    public void afterTest() throws Exception {
        cleanUp();
    }

    private void cleanUp() throws Exception {
        groupDao.removeAll();
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
        int targetId = testMember1.getId();

        // Testing method
        Member member = (Member) participantDao.getOne(Member.class, targetId);

        assertNotNull(member);
        compareMembers(testMember1, member);
    }
    
    @Test
    public void getMembersByGroupWithRelativeObjects_emptyList() throws Exception {
        Group group = initGroup(testTournament1.getTournamentId());

        // Testing method
        List<Member> memberList = (List<Member>) participantDao
                .getParticipantListByGroupWithRelativeObjects(Member.class, group.getGroupId());

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }
    
    @Test
    public void getMemberListByTournamentId_emptyList() throws Exception {
        Tournament tournament = initTournament();

        // Testing method
        List<Member> memberList = (List<Member>) participantDao
                .getParticipantListByTournamentId(Member.class, tournament.getTournamentId());

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }
    
    @Test
    public void getMemberListByTournamentId_found() throws Exception {
        int targetId = testTournament1.getTournamentId();

        // Testing method
        List<Member> memberList = (List<Member>) participantDao.getParticipantListByTournamentId(Member.class, targetId);

        assertNotNull(memberList);
        assertEquals(2, memberList.size());
        sortById(memberList);

        Member member1 = memberList.get(0);
        Member member2 = memberList.get(1);

        compareMembers(testMember1, member1);
        compareMembers(testMember2, member2);
    }
    
    @Test
    public void getMemberList_emptyList() throws Exception {
        groupDao.removeParticipant(testGroup1.getGroupId(), testMember1.getId());
        groupDao.removeParticipant(testGroup1.getGroupId(), testMember2.getId());

        participantDao.delete(testMember1.getId());
        participantDao.delete(testMember2.getId());

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
        assertEquals(2, memberList.size());
        sortById(memberList);

        Member member1 = memberList.get(0);
        Member member2 = memberList.get(1);

        compareMembers(testMember1, member1);
        compareMembers(testMember2, member2);
    }
    
    @Test
    public void getMemberListByMemberName_emptyList() throws Exception {
        // Testing method
        List<Member> memberList = (List<Member>) participantDao.getParticipantListByName(Member.class, NON_EXISTING_PARTICIPANT_NAME);

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }
    
    @Test
    public void addMember_created() throws Exception {
        int targetId = testTournament1.getTournamentId();

        // Initialize random manager instance
        Member member = DataHelper.createRandomMember();
        member.setTournamentId(targetId);

        assertEquals(0, member.getId());

        // Testing method
        participantDao.addParticipant(member);
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
        participantDao.update(testMember2.getId(), member);

        // Initialize random manager instance
        member = (Member) participantDao.getOne(Member.class, targetId);

        assertEquals(targetId, member.getId());
        assertEquals(newAvatar, member.getAvatar());
        assertEquals(newInfo, member.getParticipantInfo());
        assertEquals(newPosition, member.getPosition());
        assertEquals(newEmail, member.getEmail());
    }
    
    @Test(expected = ObjectNotFoundException.class)
    public void deleteMember_notFound() throws Exception {
        // Testing method
        participantDao.delete(NON_EXISTING_ID);
    }
    
    @Test(expected = ObjectNotFoundException.class)
    public void deleteMember_found() throws Exception {
        Participant newMember = DataHelper.createRandomMember(testTournament1.getTournamentId());
        newMember = participantDao.addParticipant(newMember);

        int id = newMember.getId();
        assertTrue(id > 0);

        // Testing method
        participantDao.delete(id);

        Participant deleted = participantDao.getOne(Member.class, id);
        assertNull(deleted);
    }
    
    @Test
    public void deleteAllMembers() throws Exception {
        groupDao.removeAll();

        // Testing method
        participantDao.deleteAll(Member.class);

        List members = participantDao.getAll(Member.class);
        assertNotNull(members);
        assertEquals(0, members.size());
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
        int targetId = testTeam1.getId();

        // Testing method
        Team team = (Team) participantDao.getOne(Team.class, targetId);

        assertNotNull(team);
        compareTeams(testTeam1, team);
    }
    
    @Test
    public void getTeamListByTournamentId_emptyList() throws Exception {
        Tournament tournament = initTournament();

        // Testing method
        List<Team> teamList = (List<Team>) participantDao
                .getParticipantListByTournamentId(Team.class, tournament.getTournamentId());

        assertNotNull(teamList);
        assertEquals(0, teamList.size());
    }
    
    @Test
    public void getTeamListByTournamentId_found() throws Exception {
        int targetId = testTournament2.getTournamentId();

        // Testing method
        List<Team> teamList = (List<Team>) participantDao.getParticipantListByTournamentId(Team.class, targetId);

        assertNotNull(teamList);
        assertEquals(2, teamList.size());
        sortById(teamList);

        Team team1 = teamList.get(0);
        Team team2 = teamList.get(1);

        compareTeams(testTeam1, team1);
        compareTeams(testTeam2, team2);
    }
    
    @Test
    public void getTeamList_emptyList() throws Exception {
        groupDao.removeParticipant(testGroup2.getGroupId(), testTeam1.getId());
        groupDao.removeParticipant(testGroup2.getGroupId(), testTeam2.getId());

        // Testing method
        participantDao.delete(testTeam1.getId());
        participantDao.delete(testTeam2.getId());

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
        assertEquals(2, teamList.size());
        sortById(teamList);

        Team team1 = teamList.get(0);
        Team team2 = teamList.get(1);

        compareTeams(testTeam1, team1);
        compareTeams(testTeam2, team2);
    }
    
    @Test
    public void addTeam_created() throws Exception {
        int targetId = testTournament1.getTournamentId();

        // Initialize random manager instance
        Team team = DataHelper.createRandomTeam();
        team.setTournamentId(targetId);
        assertEquals(0, team.getId());

        // Testing method
        participantDao.addParticipant(team);

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
        participantDao.update(targetId, team);

        // Initialize random manager instance
        team = (Team) participantDao.getOne(Team.class, targetId);

        assertEquals(targetId, team.getId());
        assertEquals(newAvatar, team.getAvatar());
        assertEquals(newInfo, team.getParticipantInfo());
        assertEquals(newName, team.getTeamName());
    }
    
    @Test(expected = ObjectNotFoundException.class)
    public void deleteTeam_notFound() throws Exception {
        // Testing method
        participantDao.delete(NON_EXISTING_ID);
    }
    
    @Test(expected = ObjectNotFoundException.class)
    public void deleteTeam_found() throws Exception {
        groupDao.removeParticipant(testGroup2.getGroupId(), testTeam2.getId());
        int targetId = testTeam2.getId();

        // Testing method
        participantDao.delete(targetId);

        Team team = (Team)participantDao.getOne(Team.class, targetId);
        assertNull(team);
    }
    
    @Test
    public void deleteAllTeams() throws Exception {
        groupDao.removeAll();

        // Testing method
        participantDao.deleteAll(Team.class);

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