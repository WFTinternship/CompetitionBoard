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
        }    }

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

    // region <MEMBER>


    /**
     * Adds member to db
     */
    private Member addMember(Member member){
        return member;
    }

    /**
     * Gets member list by id
     */
    private Member getMemberById(int id){
        return null;
    }

    /**
     * Gets member list
     */
    private List<Member> getMemberList(){
        return null;
    }

    /**
     * Updating specific data of member
     */
    private Member updateMember(Member member){
        return null;
    }

    /**
     * Deletes member by id
     */
    private void deleteMember(int id) throws ObjectNotFoundException, FailedOperationException {

    }

    /**
     * Deletes all members
     */
    private void deleteAllMembers() throws FailedOperationException {

    }

    // endregion

    // region <TEAM>

    /**
     * Adds member to db
     */
    private Team addTeam(Team team){
        return team;
    }

    /**
     * Gets member list by id
     */
    private Team getTeamById(int id){
        return null;
    }

    /**
     * Gets member list
     */
    private List<Team> getTeamList(){
        return null;
    }

    /**
     * Updating specific data of member
     */
    private Member updateTeam(Team team){
        return null;
    }

    /**
     * Deletes member by id
     */
    private void deleteTeam(int id) throws ObjectNotFoundException, FailedOperationException {

    }

    /**
     * Deletes all members
     */
    private void deleteAllTeam() throws FailedOperationException {

    }

    // endregion
}
