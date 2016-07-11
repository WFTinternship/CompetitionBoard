package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;

import java.util.List;

public interface GroupDao {
    List<Group> getGroupListByManager(int id);

    List<Group> getGroupInTournamentList(int id);


}
