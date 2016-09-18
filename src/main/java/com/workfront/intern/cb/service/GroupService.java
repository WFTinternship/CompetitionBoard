package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Participant;

import java.util.List;

public interface GroupService {

    // CREATE
    Group addGroup(Group group);

    // READ
    Group getGroupById(int id);
    List<Group> getTournamentGroups(int tournamentId);
    List<Group> getAllGroups();

    //TODO implements
    List<Participant> getGroupParticipants(int groupId);

    // UPDATE
    void updateGroup(int id, Group group);
    void assignParticipant(int tournamentId, int groupId, Participant participant);
    void removeParticipant(int tournamentId, int groupId, int participantId);

    // DELETE
    void deleteGroup(int id);
    void deleteAll();
}
