package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.sql.Connection;
import java.util.List;

public interface GroupDao extends IGenericDao {

    // CREATE
    Group addGroup(Group group) throws FailedOperationException;

    // READ
    Group getGroupById(int id) throws ObjectNotFoundException, FailedOperationException;
    List<Group> getGroupListByName (String groupName) throws FailedOperationException;
    List<Group> getTournamentGroups (int tournamentId) throws FailedOperationException;
    List<Group> getAllGroups() throws FailedOperationException;

    // UPDATE
    void updateGroup(int id, Group group) throws ObjectNotFoundException, FailedOperationException;
	void updateGroup(int id, Group group, Connection transaction) throws ObjectNotFoundException, FailedOperationException;
    void assignParticipant(int tournamentId, int groupId, Participant participant) throws ObjectNotFoundException, FailedOperationException;
    void assignParticipant(int tournamentId, int groupId, Participant participant, Connection transaction) throws ObjectNotFoundException, FailedOperationException;
    void removeParticipant(int groupId, int participant) throws ObjectNotFoundException, FailedOperationException;
    void removeAllParticipants(int groupId) throws ObjectNotFoundException, FailedOperationException;
    void removeAll() throws FailedOperationException;

    // DELETE
    void deleteGroup(int id) throws ObjectNotFoundException, FailedOperationException;
    void deleteAll() throws FailedOperationException;
}