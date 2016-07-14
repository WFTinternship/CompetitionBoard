package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Match;

import java.util.List;

public interface MatchDao {

    // CREATE
    boolean addMatch(Match match);

    // READ
    List<Match> getMatchList();

    Match getMatchByGroupId(int id);

    // UPDATE
    boolean updateMatch(Match match);

    // DELETE
    boolean deleteMatch(int id);

    boolean deleteAll();
}
