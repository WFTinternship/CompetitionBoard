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

import java.util.List;

public class ParticipantServiceImpl implements ParticipantService {
    private static final Logger LOG = Logger.getLogger(ParticipantServiceImpl.class);

    private ParticipantDao participantDao = new ParticipantDaoImpl(DBManager.getDataSource());

    @Override
    public Participant addParticipant(Participant participant) {
        if (participant instanceof Member) {
            try {
                return participantDao.addParticipant(new Member());
            } catch (FailedOperationException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            }
        } else if (participant instanceof Team) {
            try {
                return participantDao.addParticipant(new Team());
            } catch (FailedOperationException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    @Override
    public Participant getOne(Class<? extends Participant> cls, int id) {
        if (cls.equals(Member.class)) {
            try {
                return participantDao.getOne(Member.class, id);
            } catch (FailedOperationException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            } catch (ObjectNotFoundException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(String.format("Member instance with id=%s not found", id));
            }
        } else {
            if (cls.equals(Team.class)) {
                try {
                    return participantDao.getOne(Team.class, id);
                } catch (FailedOperationException e) {
                    LOG.error(e.getMessage(), e);
                    throw new RuntimeException(e.getMessage());
                } catch (ObjectNotFoundException e) {
                    LOG.error(e.getMessage(), e);
                    throw new RuntimeException(String.format("Team instance with id=%s not found", id));
                }
            } else {
                throw new RuntimeException("Unknown participant type");
            }
        }
    }

    @Override
    public List<? extends Participant> getAll(Class<? extends Participant> cls) {
        if (cls.equals(Member.class)) {
            try {
                return participantDao.getAll(Member.class);
            } catch (FailedOperationException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            }
        } else if (cls.equals(Team.class)) {
            try {
                return participantDao.getAll(Team.class);
            } catch (FailedOperationException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    @Override
    public void update(Participant participant) {
        if (participant instanceof Member) {
            try {
                participantDao.update(new Member());
            } catch (FailedOperationException e) {
                throw new RuntimeException("Member instance with id=%s not updated");
            }
        } else if (participant instanceof Team) {
            try {
                participantDao.update(new Team());
            } catch (FailedOperationException e) {
                throw new RuntimeException("Team instance with id=%s not updated");
            }
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    @Override
    public void delete(Class<? extends Participant> cls, int id) {
        if (cls.equals(Member.class)) {
            try {
                participantDao.delete(Member.class, id);
            } catch (ObjectNotFoundException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(String.format("Member instance with id=%s not found", id));
            } catch (FailedOperationException e) {
                LOG.error(e.getMessage(), e);
            }
        } else if (cls.equals(Team.class)) {
            try {
                participantDao.delete(Team.class, id);
            } catch (ObjectNotFoundException e) {
                LOG.error(e.getMessage(), e);
            } catch (FailedOperationException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(String.format("Team instance with id=%s not found", id));
            }
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    @Override
    public void deleteAll(Class<? extends Participant> cls) {
        if (cls.equals(Member.class)) {
            try {
                participantDao.deleteAll(Member.class);
            } catch (FailedOperationException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            }
        } else if (cls.equals(Team.class)) {
            try {
                participantDao.deleteAll(Team.class);
            } catch (FailedOperationException e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }
}
