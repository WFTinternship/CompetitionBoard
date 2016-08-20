package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.Team;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.DBManager;
import com.workfront.intern.cb.dao.ParticipantDao;
import com.workfront.intern.cb.dao.ParticipantDaoImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticipantServiceImpl implements ParticipantService {
    private static final Logger LOG = Logger.getLogger(ParticipantServiceImpl.class);

    private ParticipantDao participantDao = new ParticipantDaoImpl(DBManager.getDataSource());

    /**
     * Adds specific participant: member or team
     */
    @Override
    public Participant addParticipant(Participant participant) {
        try {
            if (participant instanceof Member) {
                return participantDao.addParticipant(participant);
            } else if (participant instanceof Team) {
                return participantDao.addParticipant(participant);
            } else
                throw new RuntimeException("Unknown participant type");
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Gets specific participant - member or team, by id:
     */
    @Override
    public Participant getOne(Class<? extends Participant> cls, int id) {
        try {
            if (cls.equals(Member.class)) {
                return participantDao.getOne(Member.class, id);
            } else if (cls.equals(Team.class)) {
                return participantDao.getOne(Team.class, id);
            } else {
                throw new RuntimeException("Unknown participant type");
            }
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        } catch (ObjectNotFoundException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(String.format("Participant instance with id=%s not found", id));
        }
    }

    /**
     * Gets specific participant list - memberList or teamList
     */
    @Override
    public List<? extends Participant> getAll(Class<? extends Participant> cls) {
        try {
            if (cls.equals(Member.class)) {
                return participantDao.getAll(Member.class);
            } else if (cls.equals(Team.class)) {
                return participantDao.getAll(Team.class);
            } else {
                throw new RuntimeException("Unknown participant type");
            }
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Updates specific participant - member or team
     */
    @Override
    public void update(int id, Participant participant) {
        try {
            if (participant instanceof Member) {
                participantDao.update(id, participant);
            } else {
                participantDao.update(id, participant);
            }
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        } catch (ObjectNotFoundException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException("Participant instance with id=%s not updated");
        }
    }

    /**
     * Deletes specific participant - member or team, by id:
     */
    @Override
    public void delete(int id) {
        try {
            participantDao.delete(id);
        } catch (ObjectNotFoundException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(String.format("Participant instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * Deletes all specific participants - member or team, by id:
     */
    @Override
    public void deleteAll(Class<? extends Participant> cls) {

        try {
            if (cls.equals(Member.class)) {
                participantDao.deleteAll(Member.class);
            } else if (cls.equals(Team.class)) {
                participantDao.deleteAll(Team.class);
            } else {
                throw new RuntimeException("Unknown participant type");
            }
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }
}