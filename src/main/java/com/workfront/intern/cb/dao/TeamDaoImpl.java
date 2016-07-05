package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Team;

import java.util.List;

public class TeamDaoImpl implements TeamDao {
    @Override
    public int getTeamById(int id) {
        return 0;
    }

    @Override
    public List<Team> getAllTeams() {
        return null;
    }

    @Override
    public boolean updateMember(Team team) {
        return true;
    }

    @Override
    public boolean deleteMember(Team team) {
        return true;
    }

}
