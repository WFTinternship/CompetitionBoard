package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl extends GenericDao implements GroupDao {
    private static final Logger LOG = Logger.getLogger(GroupDaoImpl.class);

    public GroupDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Adds group to db
     */
    @Override
    public Group addGroup(Group group) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO `group`(group_name, participants_count, round, next_round_participants, tournament_id)" +
                "VALUES(?,?,?,?,?) ";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // prepare base participant insert query
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, group.getGroupName());
            ps.setInt(2, group.getParticipantsCount());
            ps.setInt(3, group.getRound());
            ps.setInt(4, group.getNextRoundParticipants());
            ps.setInt(5, group.getTournamentId());

            // insert base participant info
            ps.executeUpdate();

            int id = acquireGeneratedKey(ps);
            group.setGroupId(id);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }

        return group;
    }

    /**
     * Gets group by group id
     */
    @Override
    public Group getGroupById(int id) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Group group = null;

        String sql = "SELECT * FROM `group` WHERE group_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // update member data
            rs = ps.executeQuery();
            if (rs.next()) {
                group = extractGroupFromResultSet(rs);
            } else {
                throw new ObjectNotFoundException(String.format("Group with id[%d] not found", id));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return group;
    }

    /**
     * Gets group by group name
     */
    @Override
    public List<Group> getGroupListByName(String inputStr) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Group group;
        List<Group> groupListByName = new ArrayList<>();

        String sql = "SELECT * FROM `group` g WHERE g.group_name LIKE ? ";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.format("%s", inputStr) + "%");

            // Execute statement
            rs = ps.executeQuery();
            while (rs.next()) {
                group = extractGroupFromResultSet(rs);
                groupListByName.add(group);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return groupListByName;
    }

    /**
     * Gets group list by specific tournament id
     */
    @Override
    public List<Group> getTournamentGroups(int tournamentId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Group> groupList = new ArrayList<>();
        Group group;

        String sql = "SELECT * FROM `group` WHERE tournament_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tournamentId);

            // Execute statement
            rs = ps.executeQuery();
            while (rs.next()) {
                group = extractGroupFromResultSet(rs);
                groupList.add(group);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return groupList;
    }

    /**
     * Get all group
     */
    @Override
    public List<Group> getAllGroups() throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Group> groupList = new ArrayList<>();
        Group group;

        String sql = "SELECT * FROM `group`";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);

            // Execute statement
            rs = ps.executeQuery();
            while (rs.next()) {
                group = extractGroupFromResultSet(rs);
                groupList.add(group);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return groupList;
    }

    @Override
    public List<Participant> getGroupParticipants(int groupId) throws FailedOperationException {
        //TODO implement
        return null;
    }

    /**
     * Updates group
     */
    @Override
    public void updateGroup(int id, Group group) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE `group` SET group_name=?, participants_count=?, round=?, next_round_participants=?, tournament_id=? WHERE group_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, group.getGroupName());
            ps.setInt(2, group.getParticipantsCount());
            ps.setInt(3, group.getRound());
            ps.setInt(4, group.getNextRoundParticipants());
            ps.setInt(5, group.getTournamentId());
            ps.setInt(6, id);

            // Execute statement
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ObjectNotFoundException(String.format("Group with id[%d] not found", id));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
    }

    @Override
    public void assignParticipant(int tournamentId, int groupId, Participant participant) throws ObjectNotFoundException, FailedOperationException {

        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO group_participant(group_id, participant_id) VALUES (?,?)";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // prepare base insert query
            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupId);




            // Execute statement
            ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }



    }

    @Override
    public void removeParticipant(int tournamentId, int groupId, int participantId) throws ObjectNotFoundException, FailedOperationException {

    }


    /**
     * Deletes group by id
     */
    @Override
    public void deleteGroup(int id) throws ObjectNotFoundException, FailedOperationException {
        String sql = "DELETE FROM `group` WHERE group_id=?";
        deleteEntry(sql, id);
    }

    /**
     * Deletes all group
     */
    @Override
    public void deleteAll() throws FailedOperationException {
        String sql = "DELETE FROM `group`";
        deleteAllEntries(sql);
    }

    /**
     * Extracting specific data of Group from ResultSet
     */
    private static Group extractGroupFromResultSet(ResultSet rs) {
        Group group = new Group();
        try {
            group.setGroupId(rs.getInt("group_id"));
            group.setGroupName(rs.getString("group_name"));
            group.setParticipantsCount(rs.getInt("participants_count"));
            group.setRound(rs.getInt("round"));
            group.setNextRoundParticipants(rs.getInt("next_round_participants"));
            group.setTournamentId(rs.getInt("tournament_id"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return group;
    }
}