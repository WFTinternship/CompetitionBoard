package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Team;

import java.util.List;

public interface TeamDao {

    // CREATE
    boolean addTeam(Team team);

    //READ
    Team getTeamById(int id);

    List<Team> getTeamList();

    // UPDATE
    boolean updateMember(Team team);

    // DELETE
    boolean deleteMember(int id);

    boolean deleteAll();
}
