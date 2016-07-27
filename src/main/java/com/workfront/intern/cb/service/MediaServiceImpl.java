package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.apache.log4j.Logger;

import java.util.List;

public class MediaServiceImpl implements MatchService{
    private static final Logger LOG = Logger.getLogger(MediaServiceImpl.class);

    @Override
    public Match addMatch(Match match) throws FailedOperationException {
        return null;
    }

    @Override
    public Match getMatchById(int id) throws FailedOperationException, ObjectNotFoundException {
        return null;
    }

    @Override
    public Match getMatchByGroupId(int id) throws FailedOperationException, ObjectNotFoundException {
        return null;
    }

    @Override
    public List<Match> getMatchListByGroup(int id) throws FailedOperationException {
        return null;
    }

    @Override
    public void updateMatch(int id, Match match) throws FailedOperationException {

    }

    @Override
    public void deleteMatch(int id) throws ObjectNotFoundException, FailedOperationException {

    }

    @Override
    public void deleteAll() throws FailedOperationException {

    }
}
