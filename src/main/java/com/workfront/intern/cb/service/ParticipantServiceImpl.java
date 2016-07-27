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

    /**
     * Adds specific participant: member or team
     */
    @Override
    public Participant addParticipant(Participant participant) throws FailedOperationException {
        if (participant instanceof Member) {
            return addMember((Member) participant);
        } else if (participant instanceof Team) {
            return addTeam((Team) participant);
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Gets specific participant - member or team, by id:
     */
    @Override
    public Participant getOne(Class<? extends Participant> cls, int id) throws FailedOperationException, ObjectNotFoundException {
        if (cls.equals(Member.class)) {
            return getMemberById(id);
        } else if (cls.equals(Team.class)) {
            return getTeamById(id);
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Gets specific participant list - memberList or teamList
     */
    @Override
    public List<? extends Participant> getAll(Class<? extends Participant> cls) throws FailedOperationException {
        if (cls.equals(Member.class)) {
            return getMemberList();
        } else if (cls.equals(Team.class)) {
            return getTeamList();
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Updates specific participant - member or team
     */
    @Override
    public void update(Participant participant) throws FailedOperationException {
        if (participant instanceof Member) {
            updateMember((Member) participant);
        } else if (participant instanceof Team) {
            updateTeam((Team) participant);
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Deletes specific participant - member or team, by id:
     */
    @Override
    public void delete(Class<? extends Participant> cls, int id) throws ObjectNotFoundException, FailedOperationException {
        if (cls.equals(Member.class)) {
            deleteMember(id);
        } else if (cls.equals(Team.class)) {
            deleteTeam(id);
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Deletes all specific participant - member or team, by id:
     */
    @Override
    public void deleteAll(Class<? extends Participant> cls) throws FailedOperationException {
        if (cls.equals(Member.class)) {
            deleteAllMembers();
        } else if (cls.equals(Team.class)) {
            deleteAllTeams();
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }


    // region <MEMBER>

    /**
     * Adds member to db
     */
    private Member addMember(Member member) throws FailedOperationException {
        return (Member) participantDao.addParticipant(member);
    }

    /**
     * Gets member list by id
     */
    private Member getMemberById(int id) throws ObjectNotFoundException, FailedOperationException {
        return (Member) participantDao.getOne(Member.class, id);
    }

    /**
     * Gets member list
     */
    private List<Member> getMemberList() throws FailedOperationException {
        return (List<Member>) participantDao.getAll(Member.class);
    }

    /**
     * Updating specific data of member
     */
    private Member updateMember(Member member) throws FailedOperationException {
        participantDao.update(member);
        return member;
    }

    /**
     * Deletes member by id
     */
    private void deleteMember(int id) throws ObjectNotFoundException, FailedOperationException {
        participantDao.delete(Member.class, id);
    }

    /**
     * Deletes all members
     */
    private void deleteAllMembers() throws FailedOperationException {
        participantDao.deleteAll(Member.class);
    }

    // endregion

    // region <TEAM>

    /**
     * Adds team to db
     */
    private Team addTeam(Team team) throws FailedOperationException {
        return (Team) participantDao.addParticipant(team);
    }

    /**
     * Gets team list by id
     */
    private Team getTeamById(int id) throws ObjectNotFoundException, FailedOperationException {
        return (Team) participantDao.getOne(Team.class, id);
    }

    /**
     * Gets team list
     */
    private List<Team> getTeamList() throws FailedOperationException {
        return (List<Team>) participantDao.getAll(Team.class);
    }

    /**
     * Updating specific data of team
     */
    private Team updateTeam(Team team) throws FailedOperationException {
        participantDao.update(team);
        return team;
    }

    /**
     * Deletes team by id
     */
    private void deleteTeam(int id) throws ObjectNotFoundException, FailedOperationException {
        participantDao.delete(Team.class, id);

    }

    /**
     * Deletes all teams
     */
    private void deleteAllTeams() throws FailedOperationException {
        participantDao.deleteAll(Team.class);
    }

    // endregion
}
