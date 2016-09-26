package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface ParticipantService {

    // CREATE
    Participant addParticipant(Participant participant);

    // READ
    Participant getOne(Class<? extends Participant> cls, int id);
    List<? extends Participant> getParticipantListByGroupId(Class<? extends Participant> cls, int groupId);
    List<? extends Participant> getParticipantsByTournamentId(Class<? extends Participant> cls, int tournamentId);
    List<? extends Participant> getParticipantListByName(Class<? extends Participant> cls, String participantName);
    List<? extends Participant> getAll(Class<? extends Participant> cls);

    // UPDATE
    void update(int id, Participant participant);

    // DELETE
//    void delete(Class<? extends Participant> cls, int id);
    void delete(int id);
    void deleteAll(Class<? extends Participant> cls);
}