package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface ParticipantDao {

    // CREATE
    Participant addParticipant(Participant participant) throws FailedOperationException;

    // READ
    Participant getOne(Class<? extends Participant> cls, int id) throws FailedOperationException, ObjectNotFoundException;
    List<? extends Participant> getParticipantListByGroupWithRelativeObjects(Class<? extends Participant> cls, int groupId) throws FailedOperationException;
    List<? extends Participant> getParticipantListByGroupId(Class<? extends Participant> cls, int groupId) throws FailedOperationException;
    List<? extends Participant> getParticipantListByTournamentId(Class<? extends Participant> cls, int tournamentId) throws FailedOperationException;
    List<? extends Participant> getParticipantListByName(Class<? extends Participant> cls, String participantName) throws FailedOperationException, ObjectNotFoundException;
    List<? extends Participant> getAll(Class<? extends Participant> cls) throws FailedOperationException;
    int getParticipantsCountByGroupId(int groupId) throws FailedOperationException;

    // UPDATE
    void update(int id, Participant participant) throws ObjectNotFoundException, FailedOperationException;

    // DELETE
    void delete(int id) throws ObjectNotFoundException, FailedOperationException;
    void deleteAll(Class<? extends Participant> cls) throws FailedOperationException;
}