package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Participant;

import java.util.List;

public interface ParticipantService {

    // CREATE
    Participant addParticipant(Participant participant);

    // READ
    Participant getOne(Class<? extends Participant> cls, int id);
    List<? extends Participant> getAll(Class<? extends Participant> cls);

    // UPDATE
    void update(Participant participant);

    // DELETE
    void delete(Class<? extends Participant> cls, int id);
    void deleteAll(Class<? extends Participant> cls);
}
