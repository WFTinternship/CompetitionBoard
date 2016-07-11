package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Match;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MatchDaoImpl extends GenericDao implements MatchDao{
    @Override
    public Match getMatchById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Match match;
        String sql = "SELECT * FROM `match` WHERE match_id=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                match = new Match();
                match.setMatchId(rs.getInt("match_id"));
            // TODO
//                match.setGroup(Group.get);
            }
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        }
//

        return match = null;
    }

    @Override
    public List<Match> getMatchList() {
        return null;
    }

 }
