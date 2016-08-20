package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.DBManager;
import com.workfront.intern.cb.dao.GroupDao;
import com.workfront.intern.cb.dao.GroupDaoImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupServiceImpl implements GroupService {
    private static final Logger LOG = Logger.getLogger(GroupServiceImpl.class);

    private GroupDao groupDao = new GroupDaoImpl(DBManager.getDataSource());

    /**
     * Adds new group in db
     */
    @Override
    public Group addGroup(Group group) {
        try {
            return groupDao.addGroup(group);
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns group by id
     */
    @Override
    public Group getGroupById(int id) {
        try {
            return groupDao.getGroupById(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Group instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all groups by tournament id
     */
    @Override
    public List<Group> getTournamentGroups(int tournamentId) {
        try {
            return groupDao.getTournamentGroups(tournamentId);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all groups
     */
    @Override
    public List<Group> getAllGroups() {
        try {
            return groupDao.getAllGroups();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all participants groups
     */
    @Override
    public List<Participant> getGroupParticipants(int groupId) {
        try {
            return groupDao.getGroupParticipants(groupId);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Updates existing group in db
     */
    @Override
    public void updateGroup(int id, Group group) {
        try {
            groupDao.updateGroup(id, group);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Group instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Deletes manager by id
     */
    @Override
    public void deleteGroup(int id) {
        try {
            groupDao.deleteGroup(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Group instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Removes all managers
     */
    @Override
    public void deleteAll() {
        try {
            groupDao.deleteAll();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
