package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Tournament;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentDaoImpl extends GenericDao implements TournamentDao {
    private static final Logger LOG = Logger.getLogger(TournamentDaoImpl.class);

    /**
     * Gets tournament by tournament id
     */
    @Override
    public Tournament getTournamentById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tournament tournament = null;
        String sql = "SELECT * FROM tournament WHERE tournament_id=?";

        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute statement
            rs = ps.executeQuery();
            if (rs.next()) {
                tournament = extractTournamentFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return tournament;
    }

    /**
     * Gets all tournament
     */
    @Override
    public List<Tournament> getTournamentList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tournament> tournamentList = new ArrayList<>();
        Tournament tournament;
        String sql = "SELECT * FROM tournament";

        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);

            // Execute statement
            rs = ps.executeQuery();
            while (rs.next()) {
                tournament = extractTournamentFromResultSet(rs);
                tournamentList.add(tournament);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return tournamentList;
    }



    /**
     * Gets all tournament by manager id
     */
    @Override
    public List<Tournament> getTournamentListByManager(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tournament tournament;
        List<Tournament> tournamentListByManager = new ArrayList<>();
        String sql = "SELECT * FROM tournament WHERE manager_id=?";

        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute statement
            rs = ps.executeQuery();
            while (rs.next()) {
                tournament = extractTournamentFromResultSet(rs);
                tournamentListByManager.add(tournament);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return tournamentListByManager;
    }

    /**
     * Adds tournament to db
     */
    @Override
    public boolean addTournament(Tournament tournament) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int row = 0;

        String sql = "INSERT INTO " +
                "tournament(tournament_name, start_date, end_date, location, tournament_description, tournament_format_id, manager_id) " +
                "VALUES (?,?,?,?,?,?,?)";

        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, tournament.getTournamentName());
            ps.setTimestamp(2, tournament.getStartDate());
            ps.setTimestamp(3, tournament.getEndDate());
            ps.setString(4, tournament.getLocation());
            ps.setString(5, tournament.getTournamentDescription());
            ps.setInt(6, tournament.getTournamentFormatId());
            ps.setInt(7, tournament.getManagerId());

            // Execute statement
            row = ps.executeUpdate();

            // insert base participant info
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                tournament.setTournamentId(rs.getInt(1));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return row == 1;
    }

    /**
     * Updates specific data tournament
     */
    @Override
    public boolean updateTournament(int id, Tournament tournament) {
        boolean updated = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE tournament SET tournament_name=?, start_date=?, end_date=?, location=?, " +
                "tournament_description=?, tournament_format_id=?, manager_id=? WHERE tournament_id=?";

        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, tournament.getTournamentName());
            ps.setTimestamp(2, tournament.getStartDate());
            ps.setTimestamp(3, tournament.getEndDate());
            ps.setString(4, tournament.getLocation());
            ps.setString(5, tournament.getTournamentDescription());
            ps.setInt(6, tournament.getTournamentFormatId());
            ps.setInt(7, tournament.getManagerId());
            ps.setInt(8, tournament.getTournamentId());

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
     * Deletes tournament by id
     */
    @Override
    public boolean deleteTournamentById(int id) {
        boolean deleted;

        String sql = "DELETE FROM tournament WHERE tournament_id=?";

        deleted = deleteEntity(sql, id);
        return deleted;
    }

    /**
     * Removes all tournaments
     */
    @Override
    public boolean deleteAll() {
        boolean deletedAll;

        String sql = "DELETE FROM tournament";
        deletedAll = deleteEntity(sql);

        return deletedAll;
    }

    //Extracting specific data of Tournament from ResultSet
    private static Tournament extractTournamentFromResultSet(ResultSet rs) throws SQLException {
        Tournament tournament = new Tournament();
        tournament.setTournamentId(rs.getInt("tournament_id"));
        tournament.setTournamentName(rs.getString("tournament_name"));
        tournament.setStartDate(rs.getTimestamp("start_date"));
        tournament.setEndDate(rs.getTimestamp("end_date"));
        tournament.setLocation(rs.getString("location"));
        tournament.setTournamentDescription(rs.getString("tournament_description"));
        tournament.setTournamentFormatId(rs.getInt("tournament_format_id"));
        tournament.setManagerId(rs.getInt("manager_id"));

        return tournament;
    }

}



