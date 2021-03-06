package com.workfront.intern.cb.dao.unit;

import com.mysql.jdbc.Connection;
import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.DataHelper;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Team;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.ParticipantDao;
import com.workfront.intern.cb.dao.ParticipantDaoImpl;
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
    private ParticipantDao participantDao;

    @SuppressWarnings("unchecked")
    @Before
    public void beforeTest() throws Exception {
        DataSource dataSource = Mockito.mock(DataSource.class);
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

    @Test(expected = FailedOperationException.class)
    public void addMember_dbError() throws Exception {
        participantDao.addParticipant(new Member());
    }

    @Test(expected = FailedOperationException.class)
    public void getOneMember_dbError() throws Exception {
        participantDao.getOne(Member.class, NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void getMembersByTournament_dbError() throws Exception {
        participantDao.getParticipantListByTournamentId(Member.class, NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void getAllMember_dbError() throws Exception {
        participantDao.getAll(Member.class);
    }

    @Test(expected = FailedOperationException.class)
    public void updateMember_dbError() throws Exception {
        Member testMember = DataHelper.createRandomMember();
        participantDao.update(NON_EXISTING_ID, testMember);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteMember_dbError() throws Exception {
        participantDao.delete(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteAllMember() throws Exception {
        participantDao.deleteAll(Member.class);
    }

    // endregion

    // region <TEAM>

    @Test(expected = FailedOperationException.class)
    public void addTeam_dbError() throws Exception {
        participantDao.addParticipant(new Team());
    }

    @Test(expected = FailedOperationException.class)
    public void getOneTeam_dbError() throws Exception {
        participantDao.getOne(Team.class, NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void getTeamByTournamentId_dbError() throws Exception {
        participantDao.getParticipantListByTournamentId(Team.class, NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void getAllTeam_dbError() throws Exception {
        participantDao.getAll(Team.class);
    }

    @Test(expected = FailedOperationException.class)
    public void updateTeam_dbError() throws Exception {
        Team testTeam = DataHelper.createRandomTeam();
        participantDao.update(NON_EXISTING_ID, testTeam);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteTeam_dbError() throws Exception {
        participantDao.delete(NON_EXISTING_ID);
    }

    @Test(expected = FailedOperationException.class)
    public void deleteAllTeam() throws Exception {
        participantDao.deleteAll(Team.class);
    }

    // endregion
}
