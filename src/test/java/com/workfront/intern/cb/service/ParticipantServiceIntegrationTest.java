package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Team;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParticipantServiceIntegrationTest extends BaseTest {

    // DAO instances
    private ParticipantService participantService;

    // Test helper objects
    private Member testMember;
    private Team testTeam;

    @Before
    public void beforeTest() throws Exception {
        participantService = new ParticipantServiceImpl();

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

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void getMemberId_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void getMemberById_found() throws Exception {
    }

    @Ignore
    @Test
    public void getMemberList_emptyList() throws Exception {
    }

    @Ignore
    @Test
    public void getMemberList_found() throws Exception {
    }

    @Ignore
    @Test
    public void addMember_created() throws Exception {
    }

    @Ignore
    @Test
    public void updateMember() throws Exception {
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void deleteMember_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void deleteMember_found() throws Exception {
    }

    @Ignore
    @Test
    public void deleteAllMembers() throws Exception {
    }

    // endregion

    // region <TEAM>

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void getTeamId_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void getTeamId_found() throws Exception {
    }

    @Ignore
    @Test
    public void getTeamList_emptyList() throws Exception {
    }

    @Ignore
    @Test
    public void getTeamList_found() throws Exception {
    }

    @Ignore
    @Test
    public void addTeam_created() throws Exception {
    }

    @Ignore
    @Test
    public void updateTeam() throws Exception {
    }

    @Ignore
    @Test(expected = ObjectNotFoundException.class)
    public void deleteTeam_notFound() throws Exception {
    }

    @Ignore
    @Test
    public void deleteTeam_found() throws Exception {
    }

    @Ignore
    @Test
    public void deleteAllTeams() throws Exception {
    }

    // endregion
}

