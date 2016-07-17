package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Match;

import java.util.List;

public interface MatchDao {

    // CREATE
    boolean addMatch(Match match);

    // READ
    Match getMatch(int id);

    Match getMatchByGroupId(int id);

    List<Match> getMatchListByGroup(int id);

    // UPDATE
    boolean updateMatch(int id, Match match);

    // DELETE
    boolean deleteMatch(int id);

    boolean deleteAll();
}
