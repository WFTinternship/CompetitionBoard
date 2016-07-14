package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Tournament;

import java.util.List;

public interface TournamentDao {

    // CREATE
    boolean addTournament(Tournament tournament);

    // READ
    Tournament getTournamentById(int id);

    List<Tournament> getTournamentListByManager(int id);

    List<Tournament> getTournamentList();

    // UPDATE
    boolean updateTournament(int id, Tournament tournament);


    // DELETE
    boolean deleteTournamentById(int id);

    boolean deleteAll();
}
