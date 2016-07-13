package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Tournament;

import java.util.List;

interface TournamentDao {
    Tournament getTournamentById(int id);

    List<Tournament> getTournamentListByManager(int id);

    List<Tournament> getTournamentList();

    boolean addTournament(Tournament tournament);

    boolean updateTournament(Tournament tournament);

    boolean deleteTournamentById(int id);

    boolean deleteTournamentAll();

}
