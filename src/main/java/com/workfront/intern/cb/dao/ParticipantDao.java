package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Participant;

import java.util.List;

public interface ParticipantDao {
    // CREATE
    boolean addParticipant(Participant participant);

    // READ
    Participant getOne(Class<? extends Participant> cls, int id);
    List<? extends Participant> getAll(Class<? extends Participant> cls);

    // UPDATE
    boolean update(Participant participant);

    // DELETE
    boolean delete(Class<? extends Participant> cls, int id);
    boolean deleteAll(Class<? extends Participant> cls);

}
