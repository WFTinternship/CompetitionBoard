package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Team;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeamDaoImpl extends GenericDao implements TeamDao {
    private static final Logger LOG = Logger.getLogger(MemberDaoImpl.class);

    //Gets team by memberId
    @Override
    public Team getTeamById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Team team = null;
        String sql = "SELECT * FROM participant p INNER JOIN team t ON p.participant_id=t.team_id WHERE team_id=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            try {
                conn = dataSource.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    team = extractTeamFromResultSet(rs);
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return team;
    }

    @Override
    public List<Team> getTeamList() {
        return null;
    }

    @Override
    public boolean addTeam(Team team) {
        return false;
    }

    @Override
    public boolean updateMember(Team team) {
        return false;
    }

    @Override
    public boolean deleteMember(int id) {
        return false;
    }

    private static Team extractTeamFromResultSet(ResultSet rs) {
        Team team = new Team();
        try {
            team.setId(rs.getInt("participant_id"));
            team.setAvatar(rs.getString("avatar"));
            team.setParticipantInfo(rs.getString("participant_info"));
            team.setTeamName(rs.getString("team_name"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return team;
    }

    public static void main(String[] args) {
        Team team = new TeamDaoImpl().getTeamById(7);
        System.out.println(team);

    }
}
