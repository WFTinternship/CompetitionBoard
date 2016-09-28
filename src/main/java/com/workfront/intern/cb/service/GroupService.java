package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Participant;

import java.util.List;
import java.util.Map;

public interface GroupService {

    // CREATE
    Group addGroup(Group group);

    // READ
    Group getGroupById(int id);
    List<Group> getAllGroups();
    List<Group> getGroupListByName(String groupName);
    List<Group> getTournamentGroups(int tournamentId);
	Map<Integer, List<Group>> getTournamentGroupsByRounds(int tournamentId);

    // UPDATE
    void updateGroup(int id, Group group);
    void assignParticipant(int tournamentId, int groupId, Participant participant);
    void removeParticipant(int groupId, int participant);
    void removeAllParticipants(int groupId);
    void removeAll();

    // DELETE
    void deleteGroup(int id);
    void deleteAll();
}