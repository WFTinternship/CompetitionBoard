package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.GroupDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class GroupServiceImpl implements GroupService {
    private static final Logger LOG = Logger.getLogger(GroupServiceImpl.class);

    @Autowired
    private GroupDao groupDao;

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
     * Returns all groups by group name
     */
    @Override
    public List<Group> getGroupListByName(String groupName) {
        try {
            return groupDao.getGroupListByName(groupName);
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
	 * Gets/returns group lists seperated for each round
	 */
	@Override
	public Map<Integer, List<Group>> getTournamentGroupsByRounds(int tournamentId) {
		List<Group> tournamentGroups = getTournamentGroups(tournamentId);

		Map<Integer, List<Group>> resultMap = new LinkedHashMap<>();
		for (Group group : tournamentGroups) {
			int round = group.getRound();

			List<Group> roundGroups;
			if (resultMap.containsKey(round)) {
				roundGroups = resultMap.get(round);
			} else {
				roundGroups = new ArrayList<>();
				resultMap.put(round, roundGroups);
			}

			roundGroups.add(group);
		}

		return resultMap;
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

    @Override
    public void assignParticipant(int tournamentId, int groupId, Participant participant) {
		// Acquire transactional connection
		Connection transaction = groupDao.getTransactionalConnection();

        try {
			Group group = groupDao.getGroupById(groupId);
			if (group == null)
				throw new FailedOperationException(String.format("Group with id [%s]not found", groupId));

			// assign participant
            groupDao.assignParticipant(tournamentId, groupId, participant, transaction);

			// update participant count for group
			int participantsCount = group.getParticipantsCount();
			group.setParticipantsCount(++participantsCount);
			groupDao.updateGroup(groupId, group, transaction);

			// commit transaction
			groupDao.commitTransaction(transaction);

        } catch (Exception e) {
			// rollback transaction
			groupDao.rollbackTransaction(transaction);

            throw new RuntimeException(e.getMessage());
        }
	}

    @Override
    public void removeParticipant(int groupId, int participantId) {
        try {
            groupDao.removeParticipant(groupId, participantId);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Group instance with id=%s not found", groupId));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeAllParticipants(int groupId) {
        try {
            groupDao.removeAllParticipants(groupId);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Group instance with id=%s not found", groupId));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeAll() {
        try {
            groupDao.removeAll();
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
            removeAll();
            groupDao.deleteAll();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
