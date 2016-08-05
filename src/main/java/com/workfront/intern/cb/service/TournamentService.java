package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface TournamentService {

    // CREATE
    Tournament addTournament(Tournament tournament);

    // READ
    Tournament getTournamentById(int id);
    List<Tournament> getTournamentListByManager(int managerId);
    List<Tournament> getTournamentList();

    // UPDATE
    void updateTournament(int id, Tournament tournament);

    // DELETE
    void deleteTournamentById(int id);
    void deleteAll();
}