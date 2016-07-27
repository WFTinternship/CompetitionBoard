package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.DBManager;
import com.workfront.intern.cb.dao.TournamentDao;
import com.workfront.intern.cb.dao.TournamentDaoImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class TournamentServiceImpl implements TournamentService {
    private static final Logger LOG = Logger.getLogger(TournamentServiceImpl.class);

    private TournamentDao tournamentDao = new TournamentDaoImpl(DBManager.getDataSource());

    /**
     * Adds new tournament in db
     */
    @Override
    public Tournament addTournament(Tournament tournament) throws FailedOperationException {
        try {
            return tournamentDao.addTournament(tournament);
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns tournament by id
     */
    @Override
    public Tournament getTournamentById(int id) {
        try {
            return tournamentDao.getTournamentById(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Tournament instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all tournament by manager id
     */
    @Override
    public List<Tournament> getTournamentListByManager(int id) {
        try {
            return tournamentDao.getTournamentListByManager(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException("Tournament list by manager id not found");
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all tournament
     */
    @Override
    public List<Tournament> getTournamentList() throws FailedOperationException {
        try {
            return tournamentDao.getTournamentList();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Updates existing tournament in db
     */
    @Override
    public void updateTournament(int id, Tournament tournament) throws FailedOperationException {
        try {
            tournamentDao.updateTournament(id, tournament);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Deletes tournament by id
     */
    @Override
    public void deleteTournamentById(int id) throws ObjectNotFoundException, FailedOperationException {
        try {
            tournamentDao.deleteTournamentById(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Manager instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Removes all tournamentS
     */
    @Override
    public void deleteAll() throws FailedOperationException {
        try {
            tournamentDao.deleteAll();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
