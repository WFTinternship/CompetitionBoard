package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;

import java.util.List;

public interface GroupDao {
    // CREATE
    boolean addGroup(Group group);

    // READ
    Group getGroupById(int id);

    List<Group> getTournamentGroups(int tournamentId);

    List<Group> getAllGroups();

    // UPDATE
    boolean updateGroup(Group group);

    // DELETE
    boolean deleteGroup(int id);

    boolean deleteAll();
}
