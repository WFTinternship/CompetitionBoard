package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;

import java.util.List;

interface GroupDao {
    List<Group> getGroupInTournamentList(int id);
}
