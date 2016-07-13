package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Team;

import java.util.List;

public interface TeamDao {
    Team getTeamById(int id);

    List<Team> getTeamList();

    boolean addTeam(Team team);

    boolean updateMember(Team team);

    boolean deleteMember(int id);
}
