package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface GroupService {

    // CREATE
    Group addGroup(Group group) throws FailedOperationException;

    // READ
    Group getGroupById(int id) throws ObjectNotFoundException, FailedOperationException;
    List<Group> getGroupByTournamentList(int tournamentId) throws FailedOperationException;
    List<Group> getAllGroups() throws FailedOperationException;
    List<Participant> getGroupParticipants(int groupId) throws FailedOperationException;

    // UPDATE
    void updateGroup(int id, Group group) throws FailedOperationException;

    // DELETE
    void deleteGroup(int id) throws ObjectNotFoundException, FailedOperationException;
    void deleteAll() throws FailedOperationException;
}
