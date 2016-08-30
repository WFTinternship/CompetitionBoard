package com.workfront.intern.cb.service.servicespringprofile.unit;

import com.workfront.intern.cb.BaseTest;
import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.dao.GroupDao;
import com.workfront.intern.cb.dao.GroupDaoImpl;
import com.workfront.intern.cb.service.GroupService;
import com.workfront.intern.cb.service.GroupServiceImpl;
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
public class GroupServiceUnitTest extends BaseTest {
    protected DataSource dataSource;
    private GroupDao groupDao;
    private Group testGroup;
    private GroupService groupService;

    @Before
    public void beforeTest() throws Exception {
        groupDao = Mockito.mock(GroupDaoImpl.class);
        groupService = new GroupServiceImpl();
        Whitebox.setInternalState(groupService, "groupDao", groupDao);

        testGroup = createRandomGroup();
    }

    @After
    public void afterTest() {
    }

    // region <TEST CASES>

    @Test(expected = RuntimeException.class)
    public void addGroup_DAOError() throws Exception {
        when(groupDao.addGroup(testGroup)).thenThrow(FailedOperationException.class);
        groupService.addGroup(testGroup);
    }

    @Test()
    public void addGroup_DAOSuccess() throws Exception {
        groupService.addGroup(testGroup);
        verify(groupDao).addGroup(testGroup);
    }

    @Test(expected = RuntimeException.class)
    public void getGroupById_DAOError() throws Exception {
        when(groupDao.getGroupById(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        groupService.getGroupById(NON_EXISTING_ID);
    }

    @Test()
    public void getGroupById_DAOSuccess() throws Exception {
        groupService.getGroupById(NON_EXISTING_ID);
        verify(groupDao).getGroupById(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getGroupByTournamentList_DAOError() throws Exception {
        when(groupDao.getTournamentGroups(NON_EXISTING_ID)).thenThrow(FailedOperationException.class);
        groupService.getTournamentGroups(NON_EXISTING_ID);
    }

    @Test()
    public void getGroupByTournamentList_DAOsuccess() throws Exception {
        groupService.getTournamentGroups(NON_EXISTING_ID);
        verify(groupDao).getTournamentGroups(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void getAllGroups_DAOError() throws Exception {
        when(groupDao.getAllGroups()).thenThrow(FailedOperationException.class);
        groupService.getAllGroups();
    }

    @Test()
    public void getAllGroups_DAOSuccess() throws Exception {
        groupService.getAllGroups();
        verify(groupDao).getAllGroups();
    }

    @Test(expected = RuntimeException.class)
    public void updateGroup_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(groupDao).updateGroup(NON_EXISTING_ID, testGroup);
        groupService.updateGroup(NON_EXISTING_ID, testGroup);
    }

    @Test()
    public void updateGroup_DAOSuccess() throws Exception {
        groupService.updateGroup(NON_EXISTING_ID, testGroup);
        verify(groupDao).updateGroup(NON_EXISTING_ID, testGroup);
    }

    @Test(expected = RuntimeException.class)
    public void deleteGroupById_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(groupDao).deleteGroup(NON_EXISTING_ID);
        groupService.deleteGroup(NON_EXISTING_ID);
    }

    @Test()
    public void deleteGroupById_DAOSuccess() throws Exception {
        groupService.deleteGroup(NON_EXISTING_ID);
        verify(groupDao).deleteGroup(NON_EXISTING_ID);
    }

    @Test(expected = RuntimeException.class)
    public void deleteAll_DAOError() throws Exception {
        doThrow(FailedOperationException.class).when(groupDao).deleteAll();
        groupService.deleteAll();
    }

    @Test()
    public void deleteAll_DAOSuccess() throws Exception {
        groupService.deleteAll();
        verify(groupDao).deleteAll();
    }

    // endregion
}