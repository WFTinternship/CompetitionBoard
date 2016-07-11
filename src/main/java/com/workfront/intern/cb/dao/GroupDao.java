package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Tournament;

import java.util.List;

interface GroupDao {
    int getParticipantsCount(Tournament tournament);
    List<Group> getGroupInTournamentList(int id);
}
