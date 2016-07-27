package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.apache.log4j.Logger;

import java.util.List;

public class ParticipantServiceImpl implements ParticipantService{
    private static final Logger LOG = Logger.getLogger(ParticipantServiceImpl.class);

    @Override
    public Participant addParticipant(Participant participant) throws FailedOperationException {
        return null;
    }

    @Override
    public Participant getOne(Class<? extends Participant> cls, int id) throws FailedOperationException, ObjectNotFoundException {
        return null;
    }

    @Override
    public List<? extends Participant> getAll(Class<? extends Participant> cls) throws FailedOperationException {
        return null;
    }

    @Override
    public void update(Participant participant) throws FailedOperationException {

    }

    @Override
    public void delete(Class<? extends Participant> cls, int id) throws ObjectNotFoundException, FailedOperationException {

    }

    @Override
    public void deleteAll(Class<? extends Participant> cls) throws FailedOperationException {

    }
}
