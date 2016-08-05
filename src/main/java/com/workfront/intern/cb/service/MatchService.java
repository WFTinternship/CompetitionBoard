package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface MatchService {

    // CREATE
    Match addMatch(Match match);

    // READ
    Match getMatchById(int id);
    Match getMatchByGroupId(int id);
    List<Match> getMatchListByGroup(int id);

    // UPDATE
    void updateMatch(int id, Match match);

    // DELETE
    void deleteMatch(int id);
    void deleteAll();
}