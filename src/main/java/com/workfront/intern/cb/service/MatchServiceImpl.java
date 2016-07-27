package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.*;
import org.apache.log4j.Logger;

import java.util.List;

public class MatchServiceImpl implements MatchService {
    private static final Logger LOG = Logger.getLogger(MatchServiceImpl.class);

    private MatchDao matchDao = new MatchDaoImpl(DBManager.getDataSource());

    /**
     * Adds new match in db
     */
    @Override
    public Match addMatch(Match match) throws FailedOperationException {
        try {
            return matchDao.addMatch(match);
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns match by id
     */
    @Override
    public Match getMatchById(int id) throws FailedOperationException, ObjectNotFoundException {
        try {
            return matchDao.getMatchById(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Match instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Gets match group by id
     */
    @Override
    public Match getMatchByGroupId(int id) throws FailedOperationException, ObjectNotFoundException {
        return null;
    }

    /**
     * Returns all matches by group
     */
    @Override
    public List<Match> getMatchListByGroup(int id) throws FailedOperationException {
        return null;
    }

    /**
     * Updates match in db
     */
    @Override
    public void updateMatch(int id, Match match) throws FailedOperationException {
        try {
            matchDao.updateMatch(id, match);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Deletes match by id
     */
    @Override
    public void deleteMatch(int id) throws ObjectNotFoundException, FailedOperationException {
        try {
            matchDao.deleteMatch(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Match instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Removes all matches
     */
    @Override
    public void deleteAll() throws FailedOperationException {
        try {
            matchDao.deleteAll();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
