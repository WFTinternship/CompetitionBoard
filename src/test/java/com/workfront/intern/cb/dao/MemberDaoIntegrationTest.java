package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.util.StringHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MemberDaoIntegrationTest extends BaseTest {
    // DAO instances
    private MemberDao memberDao;

    // Test helper objects
    private Member testMember;

    DataSource dataSource = DBManager.getDataSource();

    @Before
    public void beforeTest() {
        memberDao = new MemberDaoImpl(dataSource);

        // Delete all remaining objects
        memberDao.deleteAll();

        // Initialize random member instance
        testMember = createRandomMember();
        // Validate ID
        assertEquals(0, testMember.getId());

        // Save to database
        memberDao.addMember(testMember);
        testMember.setId(testMember.getMemberId());

        // Validate ID
        assertTrue(testMember.getMemberId() > 0);
    }

    @After
    public void afterTest() {
        // Deleting 'manager' of manager type field after passed test
        if (testMember != null) {
            memberDao.deleteMember(testMember.getMemberId());
        } else {
            System.out.println("WARNING: testManager was null after test execution");
        }
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

        // Testing method
        Member member = memberDao.getMemberById(targetId);

        assertNotNull(member);
    }

    @Test
    public void getMemberList_emptyList() {
        boolean deleted = memberDao.deleteMember(testMember.getMemberId());
        Assert.assertTrue(deleted);

        // Testing method
        List<Member> memberList = memberDao.getMemberList();

        assertNotNull(memberList);
        assertEquals(0, memberList.size());
    }

    @Test
    public void getMemberList_found() {
        // Testing method
        List<Member> memberList = memberDao.getMemberList();

        assertNotNull(memberList);
        assertEquals(1, memberList.size());

        Member member = memberList.get(0);
        assertEquals(testMember.getId(), member.getId());
        assertEquals(testMember.getParticipantInfo(), member.getParticipantInfo());
        assertEquals(testMember.getMemberId(), member.getMemberId());
        assertEquals(testMember.getName(), member.getName());
        assertEquals(testMember.getSurName(), member.getSurName());

        assertEquals(testMember.getPosition(), member.getPosition());
    }

    @Test
    public void addMember_created() {
        // Initialize random manager instance
        Member member = createRandomMember();
        assertEquals(0, member.getId());

        // Testing method
        boolean added = memberDao.addMember(member);

        assertTrue(added);
        assertTrue(member.getId() > 0);

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