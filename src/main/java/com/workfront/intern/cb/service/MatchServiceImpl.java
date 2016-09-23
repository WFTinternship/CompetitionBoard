package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchServiceImpl implements MatchService {
    private static final Logger LOG = Logger.getLogger(MatchServiceImpl.class);

    @Autowired
    private MatchDao matchDao;

    /**
     * Adds new match in db
     */
    @Override
    public Match addMatch(Match match) {
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
    public Match getMatchById(int id) {
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
    public Match getMatchByGroupId(int id) {
        try {
            return matchDao.getMatchByGroupId(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Match instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all matches by group
     */
    @Override
    public List<Match> getMatchListByGroup(int id) {
        try {
            return matchDao.getMatchListByGroup(id);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Updates match in db
     */
    @Override
    public void updateMatch(int id, Match match) {
        try {
            matchDao.updateMatch(id, match);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Match instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Deletes match by id
     */
    @Override
    public void deleteMatch(int id) {
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
    public void deleteAll() {
        try {
            matchDao.deleteAll();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}