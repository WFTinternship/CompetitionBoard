package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.TournamentDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TournamentServiceImpl implements TournamentService {
    private static final Logger LOG = Logger.getLogger(TournamentServiceImpl.class);

    @Autowired
    private TournamentDao tournamentDao;

    /**
     * Adds new tournament in db
     */
    @Override
    public Tournament addTournament(Tournament tournament) {
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
     * Returns all tournament by name
     */
    @Override
    public List<Tournament> getTournamentListByName(String tournamentName) {
        try {
            return tournamentDao.getTournamentListByName(tournamentName);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Tournament instance with name=%s not found", tournamentName));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all tournament by manager id
     */
    @Override
    public List<Tournament> getTournamentListByManager(int managerId) {
        try {
            return tournamentDao.getTournamentListByManager(managerId);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all tournament
     */
    @Override
    public List<Tournament> getTournamentList() {
        try {
            return tournamentDao.getTournamentList();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

	@Override
	public boolean tournamentStarted(int tournamentId) {
		try {
			return tournamentDao.tournamentStarted(tournamentId);
		} catch (FailedOperationException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
     * Updates existing tournament in db
     */
    @Override
    public void updateTournament(int id, Tournament tournament) {
        try {
            tournamentDao.updateTournament(id, tournament);
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

	@Override
	public void setCompleted(int tournamentId) {
		try {
			tournamentDao.setCompleted(tournamentId, true);
		} catch (FailedOperationException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
     * Deletes tournament by id
     */
    @Override
    public void deleteTournamentById(int id) {
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
    public void deleteAll() {
        try {
            tournamentDao.deleteAll();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}