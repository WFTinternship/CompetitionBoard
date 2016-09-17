package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Participant;

import java.util.List;

public interface ParticipantService {

    // CREATE
    Participant addParticipant(Participant participant);
    void addIDs(int groupId, int tournamentID);

    // READ
    Participant getOne(Class<? extends Participant> cls, int id);
    List<? extends Participant> getParticipantsByTournamentId(Class<? extends Participant> cls, int tournamentId);
    List<? extends Participant> getAll(Class<? extends Participant> cls);

    // UPDATE
    void update(int id, Participant participant);

    // DELETE
//    void delete(Class<? extends Participant> cls, int id);
    void delete(int id);
    void deleteAll(Class<? extends Participant> cls);
}