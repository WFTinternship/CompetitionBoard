package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Group;

import java.util.List;

public interface GroupService {

    // CREATE
    Group addGroup(Group group);

    // READ
    Group getGroupById(int id);
    List<Group> getGroupListByName (String groupName);
    List<Group> getTournamentGroups(int tournamentId);
    List<Group> getAllGroups();

    // UPDATE
    void updateGroup(int id, Group group);
//    void assignParticipant(int tournamentId, int groupId, Participant participant);
//    void removeParticipant(int tournamentId, int groupId, int participantId);
    void removeAllParticipants(int groupId);
    void removeAll();

    // DELETE
    void deleteGroup(int id);
    void deleteAll();


}
