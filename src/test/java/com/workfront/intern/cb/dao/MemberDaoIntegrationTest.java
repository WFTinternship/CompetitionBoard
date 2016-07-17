package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MemberDaoIntegrationTest extends BaseTest {

    // DAO instances
    private MemberDao memberDao;

    // Test helper objects
    private Participant testParticipant = new Member();
    private Member testMember;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() {
        memberDao = new MemberDaoImpl(dataSource);

        // Delete all remaining objects
        memberDao.deleteAll();

        // Initialize random member instance
        testParticipant.setAvatar("participant_avatar");
        testParticipant.setParticipantInfo("participant_bla");

        testMember = createRandomMember();
        testMember.setMemberId(testParticipant.getId());
        testMember.setAvatar(testParticipant.getAvatar());
        testMember.setParticipantInfo(testParticipant.getParticipantInfo());

        assertEquals(0, testMember.getId());

        // Save to database
        memberDao.addMember(testMember);
        assertTrue(testMember.getId() > 0);
    }

    @After
    public void afterTest() {
    }

    @Test
    public void getMemberId_notFound() {
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