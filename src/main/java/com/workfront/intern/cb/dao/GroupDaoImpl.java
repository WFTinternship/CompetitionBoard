package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Tournament;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GroupDaoImpl extends GenericDao implements GroupDao {
    private static final Logger LOG = Logger.getLogger(GroupDaoImpl.class);

    @Override
    public boolean addGroup(Group group) {
        return false;
    }

    // Gets group list in specific tournament
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
