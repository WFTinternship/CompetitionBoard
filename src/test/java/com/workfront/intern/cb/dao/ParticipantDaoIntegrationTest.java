package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

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
        participantDao.deleteAll(Member.class);
        participantDao.deleteAll(Team.class);
    }

    // region <MEMBER>

    @Test
    public void getMemberId_notFound() {
    }

    @Test
    public void getMemberById_found() {
    }

    @Test
    public void getMemberList_emptyList() {
    }

    @Test
    public void getMemberList_found() {
    }

    @Test
    public void addMember_created() {
    }

    @Test
    public void updateMember() {
    }

    @Test
    public void deleteMember_found() {

    }

    // endregion


    // region <TEAM>

    @Test
    public void getTeamId_notFound() {
    }

    @Test
    public void getTeamId_found() {
    }

    @Test
    public void getTeamList_emptyList() {
    }

    @Test
    public void getTeamList_found() {
    }

    @Test
    public void addTeam_created() {
    }

    @Test
    public void updateTeam() {
    }

    @Test
    public void deleteTeam_found() {

    }

    // endregion
}
