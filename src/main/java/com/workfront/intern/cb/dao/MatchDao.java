package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface MatchDao {

    // CREATE
    Match addMatch(Match match);

    // READ
    Match getMatchById(int id);
    Match getMatchByGroupId(int id);
    List<Match> getMatchListByGroup(int id);

    // UPDATE
    Match updateMatch(int id, Match match);

    // DELETE
    void deleteMatch(int id) throws ObjectNotFoundException;
    void deleteAll() throws ObjectNotFoundException;
}
