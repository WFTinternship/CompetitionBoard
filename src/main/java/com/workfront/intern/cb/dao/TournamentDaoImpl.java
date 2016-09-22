package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.TournamentFormat;
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
public class TournamentDaoImpl extends GenericDao implements TournamentDao {
    private static final Logger LOG = Logger.getLogger(TournamentDaoImpl.class);

    public TournamentDaoImpl(@Autowired DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Adds tournament to db
     */
    @Override
    public Tournament addTournament(Tournament tournament) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO " +
                "tournament(tournament_name, start_date, end_date, location, tournament_description, tournament_format_id, manager_id) " +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

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
            ps.executeUpdate();

            int tournamentId = acquireGeneratedKey(ps);
            tournament.setTournamentId(tournamentId);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return tournament;
    }

    @Override
    public int addTournamentFormat(TournamentFormat tournamentFormat) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        String sql = "INSERT INTO " +
                "tournament_format(format_id, format_type) " +
                "VALUES (?,?)";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);

            ps.setInt(1, tournamentFormat.getFormatId());
            ps.setString(2, tournamentFormat.getFormatName());

            // Execute statement
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return rows;
    }

    /**
     * Gets tournament by tournament id
     */
    @Override
    public Tournament getTournamentById(int id) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tournament tournament = null;

        String sql = "SELECT * FROM tournament WHERE tournament_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute statement
            rs = ps.executeQuery();
            tournament = mapObject(rs);
            if (tournament == null) {
                throw new ObjectNotFoundException(String.format("Tournament with id[%d] not found", id));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return tournament;
    }

    /**
     * Gets tournament by tournament name
     */
    @Override
    public List<Tournament> getTournamentListByName(String inputStr) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tournament> tournamentListByName = new ArrayList<>();

        String sql = "SELECT * FROM tournament WHERE tournament_name LIKE ? ";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.format("%s", inputStr) + "%");

            // Execute statement
            rs = ps.executeQuery();
            tournamentListByName = mapList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return tournamentListByName;
    }

    /**
     * Gets all tournament
     */
    @Override
    public List<Tournament> getTournamentList() throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tournament> tournamentList = new ArrayList<>();

        String sql = "SELECT * FROM tournament";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);

            // Execute statement
            rs = ps.executeQuery();
            tournamentList = mapList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return tournamentList;
    }

    @Override
    public List<TournamentFormat> getTournamentFormats() throws FailedOperationException {
        List<TournamentFormat> formatList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "SELECT * FROM tournament_format";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);

            // Execute statement
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int formatId = rs.getInt("format_id");
                String formatName = rs.getString("format_type");

                TournamentFormat format = TournamentFormat.fromIdAnName(formatId, formatName);
                if (format != null) {
                    formatList.add(format);
                }
            }
            rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return formatList;
    }

    /**
     * Gets all tournament by manager id
     */
    @Override
    public List<Tournament> getTournamentListByManager(int managerId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tournament> tournamentListByManager = new ArrayList<>();

        String sql = "SELECT * FROM tournament WHERE manager_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, managerId);

            // Execute statement
            rs = ps.executeQuery();
            tournamentListByManager = mapList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return tournamentListByManager;
    }

    /**
     * Updates specific data tournament
     */
    @Override
    public void updateTournament(int id, Tournament tournament) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE tournament SET tournament_name=?, start_date=?, end_date=?, location=?, " +
                "tournament_description=?, tournament_format_id=?, manager_id=? WHERE tournament_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, tournament.getTournamentName());
            ps.setTimestamp(2, tournament.getStartDate());
            ps.setTimestamp(3, tournament.getEndDate());
            ps.setString(4, tournament.getLocation());
            ps.setString(5, tournament.getTournamentDescription());
            ps.setInt(6, tournament.getTournamentFormatId());
            ps.setInt(7, tournament.getManagerId());
            ps.setInt(8, id);

            // Execute statement
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
    }

    /**
     * Deletes tournament by id
     */
    @Override
    public void deleteTournamentById(int id) throws ObjectNotFoundException, FailedOperationException {
        String sql = "DELETE FROM tournament WHERE tournament_id=?";
        deleteEntry(sql, id);
    }

    /**
     * Removes all tournaments
     */
    @Override
    public void deleteAll() throws FailedOperationException {
        String sql = "DELETE FROM tournament";
        deleteAllEntries(sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Tournament mapObject(ResultSet rs) {
        List<Tournament> entities = mapList(rs);
        return entities.size() == 0 ? null : entities.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected List<Tournament> mapList(ResultSet rs) {
        List<Tournament> resultList = new ArrayList<>();
        try {
            while (rs.next()) {
                Tournament tournament = new Tournament();

                tournament.setTournamentId(rs.getInt("tournament_id"));
                tournament.setTournamentName(rs.getString("tournament_name"));
                tournament.setStartDate(rs.getTimestamp("start_date"));
                tournament.setEndDate(rs.getTimestamp("end_date"));
                tournament.setLocation(rs.getString("location"));
                tournament.setTournamentDescription(rs.getString("tournament_description"));
                tournament.setTournamentFormatId(rs.getInt("tournament_format_id"));
                tournament.setManagerId(rs.getInt("manager_id"));

                resultList.add(tournament);
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