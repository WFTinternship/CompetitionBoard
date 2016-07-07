package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Team;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
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
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return team;
    }

    @Override
    public List<Team> getTeamList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Team team = null;
        List<Team> teamList = new ArrayList<>();
        String sql = "SELECT * FROM participant p INNER JOIN team t ON p.participant_id=t.team_id";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                team = extractTeamFromResultSet(rs);
                teamList.add(team);
            }
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return teamList;
    }

    @Override
    public boolean addTeam(Team team) {
        boolean inserted = false;
        Connection conn = null;

        String sql_participant = "INSERT INTO participant (is_team, avatar, participant_info) VALUES (?,?,?)";
        String sql_team = "INSERT INTO team (team_id, team_name) VALUES (?,?)";

        try {
            // acquire polled connection
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();

            // start transaction
            conn.setAutoCommit(false);

            // prepare base participant insert query
            PreparedStatement ps = conn.prepareStatement(sql_participant, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, team.isTeam());
            ps.setString(2, team.getAvatar());
            ps.setString(3, team.getParticipantInfo());

            // insert base participant info
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int teamId = 0;
            if (rs.next()) {
                teamId = rs.getInt(1);
            }
            ps.close();
            rs.close();

            // prepare team insert query
            ps = conn.prepareStatement(sql_team);
            ps.setInt(1, teamId);
            ps.setString(2, team.getTeamName());

            // insert team data
            ps.executeUpdate();
            rs.close();
            ps.close();

            // commit transaction
            conn.commit();
            inserted = true;
        } catch (PropertyVetoException | SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return inserted;
    }

    @Override
    public boolean updateMember(Team team) {
        boolean updated = false;
        Connection conn = null;
        String sql_participant = "UPDATE participant SET avatar=?, participant_info=? WHERE participant_id=?";
        String sql_team = "UPDATE team SET team_name=? WHERE team_id=?";

        // acquire polled connection
        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();

            // start transaction
            conn.setAutoCommit(false);

            PreparedStatement ps_participant = conn.prepareStatement(sql_participant);
            ps_participant.setString(1, team.getAvatar());
            ps_participant.setString(2, team.getParticipantInfo());
            ps_participant.setInt(3, team.getId());

            // update base participant info
            ps_participant.executeUpdate();

            PreparedStatement ps_team = conn.prepareStatement(sql_team);
            ps_team.setString(1, team.getTeamName());
            ps_team.setInt(2, team.getId());
            // update member data
            ps_team.executeUpdate();

            ps_participant.close();
            ps_team.close();

            // commit transaction
            conn.commit();
            updated = true;
        } catch (PropertyVetoException | SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated;
    }

    //Deleting team by id
    @Override
    public boolean deleteMember(int id) {
        boolean deleted;
        String sql = "DELETE FROM team WHERE team_id=?";
        deleted = deleteEntity(sql, id);

        return deleted;
    }

    // Extracting specific data of Team from ResultSet
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
//        Team team = new TeamDaoImpl().getTeamById(7);
//        System.out.println(team);

//        List<Team> teamList = new TeamDaoImpl().getTeamList();
//        System.out.println(teamList);

//        Team team = new Team();
//        team.setTeamName("Bavariya");
//        team.setAvatar("avatar_bavariya");
//        team.setParticipantInfo("bla bla bla");
//        boolean added = new TeamDaoImpl().addTeam(team);

//        boolean deleted = new TeamDaoImpl().deleteMember(27);

        Team team = new Team();
        team.setId(25);
        team.setAvatar("avatar_" + System.currentTimeMillis());
        team.setParticipantInfo("info_" + System.currentTimeMillis());
        team.setTeamName("Manchestr");

        boolean updateMember = new TeamDaoImpl().updateMember(team);
    }
}
