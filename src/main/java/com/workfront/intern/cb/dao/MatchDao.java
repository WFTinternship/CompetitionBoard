package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface MatchDao {

    // CREATE
    Match addMatch(Match match) throws FailedOperationException;

    // READ
    Match getMatchById(int id) throws FailedOperationException, ObjectNotFoundException;
    Match getMatchByGroupId(int id) throws FailedOperationException, ObjectNotFoundException;
    List<Match> getMatchListByGroup(int id) throws FailedOperationException;
    List<Match> getMatchListByManager(int managerId) throws FailedOperationException;
    List<Match> getAllMatchList() throws FailedOperationException;

    // UPDATE
    void updateMatch(int id, Match match) throws ObjectNotFoundException, FailedOperationException;

    // DELETE
    void deleteMatch(int id) throws ObjectNotFoundException, FailedOperationException;
    void deleteAll() throws FailedOperationException;
}