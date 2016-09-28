package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Match;

import java.util.List;

public interface MatchService {

    // CREATE
    Match addMatch(Match match);

    // READ
    Match getMatchById(int id);
    Match getMatchByGroupId(int id);
    List<Match> getMatchListByGroup(int id);
    List<Match> getMatchListByManager(int managerId);


    // UPDATE
    void updateMatch(int id, Match match);

    // DELETE
    void deleteMatch(int id);
    void deleteAll();
}