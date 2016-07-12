package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Match;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MatchDaoImpl extends GenericDao implements MatchDao {
    @Override
    public Match getMatchByGroupId(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Match match = null;
        String sql = "SELECT * FROM `match` WHERE group_id=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                match = new Match();
                match.setMatchId(rs.getInt("match_id"));
                match.setGroupId(rs.getInt("group_id"));
                match.setParticipantOneId(rs.getInt("participant_1_id"));
                match.setParticipantTwoId(rs.getInt("participant_1_id"));
                match.setScoreParticipantOne(rs.getInt("score_participant_1"));
                match.setScoreParticipantTwo(rs.getInt("score_participant_2"));

            }
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return match;
    }

    @Override
    public List<Match> getMatchList() {
        return null;
    }

}
