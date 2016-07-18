package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MemberDaoIntegrationTest extends BaseTest {

    // DAO instances
    private ParticipantDao participantDao;
    private MemberDao memberDao;

    // Test helper objects
    private Participant testParticipant;
    private Member testMember;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() {
        participantDao = new ParticipantDaoImpl(dataSource);
        memberDao = new MemberDaoImpl(dataSource);

        // Delete all remaining objects
        memberDao.deleteAll();
        participantDao.deleteAll();

        // Initialize random member instance
        testParticipant = createRandomMemberParticipant();

        // Save to database
        participantDao.addParticipant(testParticipant);

        // Initialize random member instance
        testMember = createRandomMember();
        testMember.setMemberId(testParticipant.getId());


        // Save to database
        memberDao.addMember(testMember);

        // Validate ID
        assertTrue(testMember.getId() > 0);
    }

    @After
    public void afterTest() {

    }

    @Test
    public void getMemberId_notFound() {
        // Testing method
        Member member = memberDao.getMemberById(NON_EXISTING_ID);

        assertNull(MESSAGE_TEST_COMPLETED_ERROR, member);
    }

    @Test
    public void getMemberById_found() {
        int targetId = testMember.getMemberId();
        Member member = memberDao.getMemberById(targetId);

        assertNotNull(member);
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
    public void deleteMember_deleted() {

    }

    @Test
    public void deleteAll() {

    }
}