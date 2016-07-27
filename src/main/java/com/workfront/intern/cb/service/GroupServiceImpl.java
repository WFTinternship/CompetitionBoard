package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.*;
import org.apache.log4j.Logger;

import java.util.List;

public class GroupServiceImpl implements GroupService {
    private static final Logger LOG = Logger.getLogger(GroupServiceImpl.class);

    private GroupDao groupDao = new GroupDaoImpl(DBManager.getDataSource());


    @Override
    public Group addGroup(Group group) throws FailedOperationException {
        try {
            managerDao.addManager(manager);
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
        return manager;    }

    @Override
    public Group getGroupById(int id) throws ObjectNotFoundException, FailedOperationException {
        return null;
    }

    @Override
    public List<Group> getGroupByTournamentList(int tournamentId) throws FailedOperationException {
        return null;
    }

    @Override
    public List<Group> getAllGroups() throws FailedOperationException {
        return null;
    }

    @Override
    public List<Participant> getGroupParticipants(int groupId) throws FailedOperationException {
        return null;
    }

    @Override
    public void updateGroup(int id, Group group) throws FailedOperationException {

    }

    @Override
    public void deleteGroup(int id) throws ObjectNotFoundException, FailedOperationException {

    }

    @Override
    public void deleteAll() throws FailedOperationException {

    }
}
