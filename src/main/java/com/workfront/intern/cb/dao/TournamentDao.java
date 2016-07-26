package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface TournamentDao {

    // CREATE
    Tournament addTournament(Tournament tournament) throws FailedOperationException;

    // READ
    Tournament getTournamentById(int id) throws ObjectNotFoundException, FailedOperationException;
    List<Tournament> getTournamentListByManager(int id) throws FailedOperationException;
    List<Tournament> getTournamentList() throws FailedOperationException;

    // UPDATE
    void updateTournament(int id, Tournament tournament) throws FailedOperationException;

    // DELETE
    void deleteTournamentById(int id) throws ObjectNotFoundException, FailedOperationException;
    void deleteAll() throws FailedOperationException;
}
