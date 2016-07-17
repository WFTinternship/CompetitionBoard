package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.util.StringHelper;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
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
            conn = DBManager.getPooledConnection();

            // prepare base participant insert query
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, group.getParticipantsCount());
            ps.setInt(2, group.getRound());
            ps.setInt(3, group.getNextRoundParticipnats());
            ps.setInt(4, group.getTournamentId());

            // insert base participant info
            rows = ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                group.setGroupId(rs.getInt(1));
            }
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

        String sql = "SELECT * FROM `group` WHERE tournament_id=?";
        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

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

    @Override
    public List<Group> getTournamentGroups(int tournamentId) {
        return null;
    }

    @Override
    public List<Group> getAllGroups() {
        return null;
    }

    @Override
    public boolean updateGroup(Group group) {
        return false;
    }

    @Override
    public boolean deleteGroup(int id) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    //Extracting specific data of Group from ResultSet
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
