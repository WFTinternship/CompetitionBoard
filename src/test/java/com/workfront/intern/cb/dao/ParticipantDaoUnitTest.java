package com.workfront.intern.cb.dao;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class ParticipantDaoUnitTest extends BaseTest {
    DataSource dataSource;
    ParticipantDao participantDao;
    Member testMember;
    Team testTeam;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        dataSource = Mockito.mock(DataSource.class);
        Connection conn = Mockito.mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        when(conn.prepareStatement(any(String.class), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(SQLException.class);

        participantDao = new ParticipantDaoImpl(dataSource);
        testMember = createRandomMember();
        testTeam = createRandomTeam();
    }

    @After
    public void afterTest() {
    }


    // region <MEMBER>

    @Test
    public void addMember_dbError() {
        participantDao.addParticipant(new Member());
    }

    @Test
    public void getOneMember_dbError() {
        participantDao.getOne(Member.class, NON_EXISTING_ID);
    }

    @Test
    public void getAllMember_dbError() {
        participantDao.getAll(Member.class);
    }

    @Test
    public void updateMember_dbError() {
        participantDao.update(testMember);
    }

    @Test
    public void deleteMember_dbError() {
        participantDao.delete(Member.class, NON_EXISTING_ID);
    }

    @Test
    public void deleteAllMember() {
        participantDao.deleteAll(Member.class);
    }

    // endregion


    // region <TEAM>

    @Test
    public void addTeam_dbError() {
        participantDao.addParticipant(new Team());
    }

    @Test
    public void getOneTeam_dbError() {
        participantDao.getOne(Team.class, NON_EXISTING_ID);
    }

    @Test
    public void getAllTeam_dbError() {
        participantDao.getAll(Team.class);
    }

    @Test
    public void updateTeam_dbError() {
        participantDao.update(testMember);
    }

    @Test
    public void deleteTeam_dbError() {
        participantDao.delete(Team.class, NON_EXISTING_ID);
    }

    @Test
    public void deleteAllTeam() {
        participantDao.deleteAll(Team.class);
    }


    // endregion
}
