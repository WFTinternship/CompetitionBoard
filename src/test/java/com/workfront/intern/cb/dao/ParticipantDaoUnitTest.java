package com.workfront.intern.cb.dao;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Team;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
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

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        dataSource = Mockito.mock(DataSource.class);
        Connection conn = Mockito.mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        when(conn.prepareStatement(any(String.class), eq(Statement.RETURN_GENERATED_KEYS))).thenThrow(SQLException.class);

        participantDao = new ParticipantDaoImpl(dataSource);
    }

    @After
    public void afterTest() {
    }


    // region <MEMBER>

    @Test
    public void addMember_dbError() throws Exception {
        participantDao.addParticipant(new Member());
    }

    @Test
    public void getOneMember_dbError() throws ObjectNotFoundException, FailedOperationException {
        participantDao.getOne(Member.class, NON_EXISTING_ID);
    }

    @Test
    public void getAllMember_dbError() throws FailedOperationException {
        participantDao.getAll(Member.class);
    }

    @Test
    public void updateMember_dbError() throws Exception {
        Member testMember = createRandomMember();
        participantDao.update(testMember);
    }

    @Test
    public void deleteMember_dbError() throws ObjectNotFoundException, FailedOperationException {
        participantDao.delete(Member.class, NON_EXISTING_ID);
    }

    @Test
    public void deleteAllMember() throws ObjectNotFoundException, FailedOperationException {
        participantDao.deleteAll(Member.class);
    }

    // endregion


    // region <TEAM>

    @Test
    public void addTeam_dbError() throws Exception {
        participantDao.addParticipant(new Team());
    }

    @Test
    public void getOneTeam_dbError() throws ObjectNotFoundException, FailedOperationException {
        participantDao.getOne(Team.class, NON_EXISTING_ID);
    }

    @Test
    public void getAllTeam_dbError() throws FailedOperationException {
        participantDao.getAll(Team.class);
    }

    @Test
    public void updateTeam_dbError() throws Exception {
        Team testTeam = createRandomTeam();
        participantDao.update(testTeam);
    }

    @Test
    public void deleteTeam_dbError() throws ObjectNotFoundException, FailedOperationException {
        participantDao.delete(Team.class, NON_EXISTING_ID);
    }

    @Test
    public void deleteAllTeam() throws ObjectNotFoundException, FailedOperationException {
        participantDao.deleteAll(Team.class);
    }


    // endregion
}
