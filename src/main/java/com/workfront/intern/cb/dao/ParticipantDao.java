package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Participant;

import java.util.List;

public interface ParticipantDao {
    Participant getParticipantById(int id);
    List<Participant> getParticipantList();
    boolean addParticipant(Participant participant);
    boolean updateParticipant(int participantId, boolean isTeam, String avatar, String participantInfo);
    boolean deleteParticipantById(int id);
}
