package com.workfront.intern.cb.service;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Team;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.ParticipantDao;
import com.workfront.intern.cb.dao.ParticipantDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import javax.sql.DataSource;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ParticipantServiceUnitTest extends BaseTest {
    protected DataSource dataSource;
    private ParticipantDao participantDao;
    private Member testMember;
    private Team testTeam;
    private ParticipantService participantService;

    @Before
    public void beforeTest() throws Exception {
        participantDao = Mockito.mock(ParticipantDaoImpl.class);
        participantService = new ParticipantServiceImpl();
        Whitebox.setInternalState(participantService, "participantDao", participantDao);

        testMember = createRandomMember();
        testTeam = createRandomTeam();
    }

    // region <MEMBER>

    @After
    public void afterTest() {
    }

    @Test(expected = RuntimeException.class)
    public void addMember_DAOError() throws Exception {
        when(participantDao.addParticipant(testMember)).thenThrow(FailedOperationException.class);
        participantService.addParticipant(testMember);
    }

    @Test()
    public void addMember_DAOSuccess() throws Exception {
        participantService.addParticipant(testMember);
        verify(participantDao).addParticipant(testMember);
    }

    @Test(expected = RuntimeException.class)
    public void getMemberById_DAOError() throws Exception {
        when(participantDao.getOne(Member.class, NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        participantService.getOne(Member.class, NON_EXISTING_ID);
    }

    @Test()
    public void getMemberById_DAOSuccess() throws Exception {
        participantService.getOne(Member.class, NON_EXISTING_ID);
        verify(participantDao).getOne(Member.class, NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getMemberList_DAOError() throws Exception {
        when(participantDao.getAll(Member.class)).thenThrow(FailedOperationException.class);
        participantService.getAll(Member.class);
    }

    @Test()
    public void getMemberList_DAOSuccess() throws Exception {
        participantService.getAll(Member.class);
        verify(participantDao).getAll(Member.class);
    }

    @Test(expected = RuntimeException.class)
    public void updateMember_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(participantDao).update(NON_EXISTING_ID, testMember);
        participantService.update(NON_EXISTING_ID, testMember);
    }

    @Test()
    public void updateMember_DAOSuccess() throws Exception {
        participantService.update(NON_EXISTING_ID, testMember);
        verify(participantDao).update(NON_EXISTING_ID, testMember);
    }

    @Test(expected = RuntimeException.class)
    public void deleteMemberById_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(participantDao).delete(NON_EXISTING_ID);
        participantService.delete(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void deleteAllMembers_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(participantDao).deleteAll(Member.class);
        participantService.deleteAll(Member.class);
    }

    @Test()
    public void deleteAllMembers_DAOSuccess() throws Exception {
        participantService.deleteAll(Member.class);
        verify(participantDao).deleteAll(Member.class);
    }

    // endregion

    // region <TEAM>

    @Test(expected = RuntimeException.class)
    public void addTeam_DAOError() throws Exception {
        when(participantDao.getOne(Team.class, NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        participantService.getOne(Team.class, NON_EXISTING_ID);
    }

    @Test()
    public void addTeam_DAOSuccess() throws Exception {
        participantService.getOne(Team.class, NON_EXISTING_ID);
        verify(participantDao).getOne(Team.class, NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getTeamById_DAOError() throws Exception {
        when(participantDao.getOne(Team.class, NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        participantService.getOne(Team.class, NON_EXISTING_ID);
    }

    @Test()
    public void getTeamById_DAOSuccess() throws Exception {
        participantService.getOne(Team.class, NON_EXISTING_ID);
        verify(participantDao).getOne(Team.class, NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getTeamList_DAOError() throws Exception {
        when(participantDao.getAll(Team.class)).thenThrow(FailedOperationException.class);
        participantService.getAll(Team.class);
    }

    @Test()
    public void getTeamList_DAOSuccess() throws Exception {
        participantService.getAll(Team.class);
        verify(participantDao).getAll(Team.class);
    }

    @Test(expected = RuntimeException.class)
    public void updateTeam_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(participantDao).update(NON_EXISTING_ID, testTeam);
        participantService.update(NON_EXISTING_ID, testTeam);
    }

    @Test()
    public void updateTeam_DAOSuccess() throws Exception {
        participantService.update(NON_EXISTING_ID, testTeam);
        verify(participantDao).update(NON_EXISTING_ID, testTeam);}

    @Test(expected = RuntimeException.class)
    public void deleteTeamById_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(participantDao).delete(NON_EXISTING_ID);
        participantService.delete(NON_EXISTING_ID);
    }

    @Test()
    public void deleteTeamById_DAOSuccess() throws Exception {
        participantService.delete(NON_EXISTING_ID);
        verify(participantDao).delete(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void deleteAllTeams_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(participantDao).deleteAll(Team.class);
        participantService.deleteAll(Team.class);
    }

    @Test()
    public void deleteAllTeams_DAOSuccess() throws Exception {
        participantService.deleteAll(Team.class);
        verify(participantDao).deleteAll(Team.class);
    }

    // endregion
}