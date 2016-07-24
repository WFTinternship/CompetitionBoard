package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Participant;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl extends GenericDao implements GroupDao {
    private static final Logger LOG = Logger.getLogger(GroupDaoImpl.class);

    private DataSource dataSource;

    public GroupDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Adds group to db
     */
    @Override
    public boolean addGroup(Group group) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rows = 0;

        String sql = "INSERT INTO `group`(participants_count, round, next_round_participants, tournament_id)" +
                "VALUES(?,?,?,?) ";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // prepare base participant insert query
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, group.getParticipantsCount());
            ps.setInt(2, group.getRound());
            ps.setInt(3, group.getNextRoundParticipnats());
            ps.setInt(4, group.getTournamentId());

            // insert base participant info
            rows = ps.executeUpdate();

            int id = acquireGeneratedKey(ps);
            group.setGroupId(id);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }

        return rows == 1;
    }

    /**
     * Gets group list in specific tournament
     */
    @Override
    public Group getGroupById(int id) {
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
            while (rs.next()) {
                group = extractGroupFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return group;
    }

    /**
     * Gets group list by specific tournament id
     */
    @Override
    public List<Group> getGroupByTournamentList(int tournamentId) {
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
        } finally {
            closeResources(conn, ps, rs);
        }
        return groupList;
    }

    /**
     * Get all group
     */
    @Override
    public List<Group> getAllGroups() {
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
        } finally {
            closeResources(conn, ps, rs);
        }
        return groupList;
    }

    //TODO
    /**
     *
     */
    @Override
    public List<Participant> getGroupParticipants(int groupId) {
        return null;
    }

    /**
     * Updates group
     */
    @Override
    public boolean updateGroup(int id, Group group) {
        boolean updated = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE `group` SET participants_count=?, round=?, next_round_participants=?, tournament_id=? WHERE group_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, group.getParticipantsCount());
            ps.setInt(2, group.getRound());
            ps.setInt(3, group.getNextRoundParticipnats());
            ps.setInt(4, group.getTournamentId());
            ps.setInt(5, id);

            // Execute statement
            ps.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return updated;
    }


    /**
     * Deletes group by id
     */
    @Override
    public boolean deleteGroup(int id) {
        boolean deleted;

        String sql = "DELETE FROM `group` WHERE group_id=?";

        deleted = deleteEntries(sql, id);

        return deleted;
    }

    /**
     * Deletes all group
     */
    @Override
    public boolean deleteAll() {
        boolean deleted;

        String sql = "DELETE FROM `group`";

        deleted = deleteEntries(sql);

        return deleted;
    }

    /**
     * Extracting specific data of Group from ResultSet
     */
    private static Group extractGroupFromResultSet(ResultSet rs) {
        Group group = new Group();
        try {
            group.setGroupId(rs.getInt("group_id"));
            group.setParticipantsCount(rs.getInt("participants_count"));
            group.setRound(rs.getInt("round"));
            group.setNextRoundParticipnats(rs.getInt("next_round_participants"));
            group.setTournamentId(rs.getInt("tournament_id"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return group;
    }
}
