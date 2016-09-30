package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Match;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchDaoImpl extends GenericDao implements MatchDao {
    private static final Logger LOG = Logger.getLogger(MatchDaoImpl.class);

    public MatchDaoImpl(@Autowired DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets match by id
     */
    @Override
    public Match getMatchById(int id) throws FailedOperationException, ObjectNotFoundException {
        String sql = "SELECT * FROM `match` WHERE match_id=?";
        return getMatchFromSpecQuery(sql, id);
    }

    /**
     * Gets match by specific group id
     */
    @Override
    public Match getMatchByGroupId(int id) throws ObjectNotFoundException, FailedOperationException {
        String sql = "SELECT * FROM `match` WHERE group_id=?";
        return getMatchFromSpecQuery(sql, id);
    }

    /**
     * Returns all match list in db by group
     */
    @Override
    public List<Match> getMatchListByGroup(int id) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Match> matchList = new ArrayList<>();

        String sql = "SELECT * FROM `match` WHERE group_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            matchList = mapList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return matchList;
    }

    /**
     * Returns all match list in db by manager
     */
    @Override
    public List<Match> getMatchListByManager(int managerId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Match> matchList = new ArrayList<>();

        String sql = "SELECT * from competition_board.`match` m " +
                "INNER JOIN `group` g on m.group_id=g.group_id " +
                "INNER JOIN tournament t on g.tournament_id=t.tournament_id " +
                "WHERE manager_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, managerId);
            rs = ps.executeQuery();
            matchList = mapList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return matchList;
    }

    /**
     * Returns all match list
     */
    @Override
    public List<Match> getAllMatchList() throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Match> matchList = new ArrayList<>();

        String sql = "SELECT * from competition_board.`match` m " +
                "INNER JOIN `group` g on m.group_id=g.group_id " +
                "INNER JOIN tournament t on g.tournament_id=t.tournament_id ";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            matchList = mapList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return matchList;
    }

    /**
     * Adds match in to db
     */
    @Override
    public Match addMatch(Match match) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO `match` (group_id, participant_1_id, participant_2_id, score_participant_1, score_participant_2, match_score) VALUE(?,?,?,?,?,?);";
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
            ps.setInt(6, match.getMatchScore());

            // insert base participant info
            ps.executeUpdate();

            int id = acquireGeneratedKey(ps);
            match.setMatchId(id);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return match;
    }

    /**
     * Updates match by id
     */
    @Override
    public void updateMatch(int id, Match match) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE `match` SET group_id=?, participant_1_id=?, participant_2_id=?,  " +
                "score_participant_1=?, score_participant_2=?, match_score=? WHERE match_id=?";
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
            ps.setInt(6, match.getMatchScore());
            ps.setInt(7, match.getMatchId());

            // Execute statement
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ObjectNotFoundException(String.format("Match with id[%d] not found", id));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
    }

    /**
     * Deletes match by id
     */
    @Override
    public void deleteMatch(int id) throws ObjectNotFoundException, FailedOperationException {
        String sql = "DELETE FROM `match` WHERE match_id=?";
        deleteEntry(sql, id);
    }

    /**
     * Deletes all match
     */
    @Override
    public void deleteAll() throws FailedOperationException {
        String sql = "DELETE FROM `match`";
        deleteAllEntries(sql);
    }

    /**
     * Returns match by specific sql query
     */
    private Match getMatchFromSpecQuery(String sql, int id) throws ObjectNotFoundException, FailedOperationException {
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
            match = mapObject(rs);
            if (match == null) {
                throw new ObjectNotFoundException(String.format("Manager with id[%d] not found", id));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return match;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Match mapObject(ResultSet rs) {
        List<Match> entities = mapList(rs);
        return entities.size() == 0 ? null : entities.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected List<Match> mapList(ResultSet rs) {
        List<Match> resultList = new ArrayList<>();
        try {
            while (rs.next()) {
                Match match = new Match();

                match.setMatchId(rs.getInt("match_id"));
                match.setGroupId(rs.getInt("group_id"));
                match.setParticipantOneId(rs.getInt("participant_1_id"));
                match.setParticipantTwoId(rs.getInt("participant_2_id"));
                match.setScoreParticipantOne(rs.getInt("score_participant_1"));
                match.setScoreParticipantTwo(rs.getInt("score_participant_2"));
                match.setMatchScore(rs.getInt("match_score"));

                resultList.add(match);
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
        return resultList;
    }

}