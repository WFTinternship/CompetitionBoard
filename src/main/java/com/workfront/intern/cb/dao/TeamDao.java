package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Team;

import java.util.List;

public interface TeamDao {
    int getTeamById(int id);
    List<Team> getAllTeams();
    void updateMember(Team team);
    void deleteMember(Team team);
}
