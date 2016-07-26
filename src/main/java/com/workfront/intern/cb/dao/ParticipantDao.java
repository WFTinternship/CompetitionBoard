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
    List<? extends Participant> getAll(Class<? extends Participant> cls) throws FailedOperationException;

    // UPDATE
    void update(Participant participant) throws FailedOperationException;

    // DELETE
    void delete(Class<? extends Participant> cls, int id) throws ObjectNotFoundException, FailedOperationException;
    void deleteAll(Class<? extends Participant> cls) throws FailedOperationException;
}
