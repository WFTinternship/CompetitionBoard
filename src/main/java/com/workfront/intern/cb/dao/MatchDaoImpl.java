package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Match;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDaoImpl extends GenericDao implements MatchDao {
    private static final Logger LOG = Logger.getLogger(MatchDaoImpl.class);

    private DataSource dataSource;

    public MatchDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets match by id
     */
    @Override
    public Match getMatchById(int id) {
        String sql = "SELECT * FROM `match` WHERE match_id=?";
        return getMatchFromSpecQuery(sql, id);

    }

    /**
     * Gets match by specific group id
     */
    @Override
    public Match getMatchByGroupId(int id) {
        String sql = "SELECT * FROM `match` WHERE group_id=?";
        return getMatchFromSpecQuery(sql, id);
    }

    /**
     * Returns all match list in db by group
     */
    @Override
    public List<Match> getMatchListByGroup(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Match match;
        List<Match> matchList = new ArrayList<>();

        String sql = "SELECT * FROM `match` WHERE group_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                match = extractMatchFromResultSet(rs);
                matchList.add(match);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return matchList;
    }

    /**
     * Adds match in to db
     */
    @Override
    public boolean addMatch(Match match) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO `match`(group_id, participant_1_id, participant_2_id, score_participant_1, score_participant_2) VALUE(?,?,?,?,?)";
        int rows = 0;
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // prepare base participant insert query
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, match.getGroupId());
            ps.setInt(2, match.getParticipantOneId());
            ps.setInt(3, match.getParticipantTwoId());
            ps.setInt(4, match.getScoreParticipantOne());
            ps.setInt(5, match.getScoreParticipantTwo());

            // insert base participant info
            rows = ps.executeUpdate();

            int id = acquireGeneratedKey(ps);
            match.setMatchId(id);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return rows == 1;
    }

    /**
     * Updates match by id
     */
    @Override
    public boolean updateMatch(int id, Match match) {
        boolean updated = false;
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE `match` SET group_id=?, participant_1_id=?, participant_2_id=?,  score_participant_1=?, score_participant_2=? " +
                "WHERE match_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, match.getGroupId());
            ps.setInt(2, match.getParticipantOneId());
            ps.setInt(3, match.getParticipantTwoId());
            ps.setInt(4, match.getScoreParticipantOne());
            ps.setInt(5, match.getScoreParticipantTwo());
            ps.setInt(6, match.getMatchId());

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
     * Deletes match by id
     */
    @Override
    public boolean deleteMatch(int id) {
        boolean deleted;
        String sql = "DELETE FROM `match` WHERE match_id=?";

        deleted = deleteEntries(sql, id);
        return deleted;
    }

    /**
     * Deletes all match
     */
    @Override
    public boolean deleteAll() {
        boolean deleted;
        String sql = "DELETE FROM `match`";

        deleted = deleteEntries(sql);
        return deleted;
    }

    /**
     * Extracting specific data of Manager from ResultSet
     */
    private Match extractMatchFromResultSet(ResultSet rs) throws SQLException {
        Match match = new Match();
        match.setMatchId(rs.getInt("match_id"));
        match.setGroupId(rs.getInt("group_id"));
        match.setParticipantOneId(rs.getInt("participant_1_id"));
        match.setParticipantTwoId(rs.getInt("participant_2_id"));
        match.setScoreParticipantOne(rs.getInt("score_participant_1"));
        match.setScoreParticipantTwo(rs.getInt("score_participant_2"));

        return match;
    }

    /**
     * Returns match by specific sql query
     */
    private Match getMatchFromSpecQuery(String sql, int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Match match = null;

        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute statement
            rs = ps.executeQuery();
            if (rs.next()) {
                match = extractMatchFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return match;
    }
}
