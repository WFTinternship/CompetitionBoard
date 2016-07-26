package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface ParticipantDao {

    // CREATE
    Participant addParticipant(Participant participant);

    // READ
    Participant getOne(Class<? extends Participant> cls, int id) throws FailedOperationException, ObjectNotFoundException;
    List<? extends Participant> getAll(Class<? extends Participant> cls) throws FailedOperationException;

    // UPDATE
    Participant update(Participant participant);

    // DELETE
    void delete(Class<? extends Participant> cls, int id) throws ObjectNotFoundException;
    void deleteAll(Class<? extends Participant> cls) throws ObjectNotFoundException;
}
