package com.workfront.intern.cb.dao;


import com.workfront.intern.cb.common.Participant;

public interface ParticipantDao {
    //CREATE
    boolean addParticipant(Participant participant);

    //READ
    Participant getParticipantById(int id);

    //UPDATE
    boolean updateParticipant(int id, Participant participant);

    //DELETE
    boolean deleteParticipantbyId(int id);

    boolean deleteAll();
}
