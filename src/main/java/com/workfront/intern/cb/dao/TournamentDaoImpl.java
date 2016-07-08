package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Tournament;
import com.workfront.intern.cb.common.TournamentFormat;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TournamentDaoImpl extends GenericDao implements TournamentDao {
    private static final Logger LOG = Logger.getLogger(TournamentDaoImpl.class);

    //Gets tournament by memberId
    @Override
    public Tournament getTournamentById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tournament tournament = null;
        String sql = "SELECT * FROM tournament WHERE tournament_id=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                tournament = extractTournamentFromResultSet(rs);
            }
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return tournament;
    }

    //Get all tournament
    @Override
    public List<Tournament> getTournamentList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tournament> tournamentList = new ArrayList<>();
        Tournament tournament;
        String sql = "SELECT * FROM tournament";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tournament = extractTournamentFromResultSet(rs);
                tournamentList.add(tournament);
            }
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return tournamentList;
    }

    //Adding tournament to db
    @Override
    public boolean addTournament(Tournament tournament) {
        boolean inserted = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO " +
                "tournament(tournament_name, start_date, end_date, location, tournament_description, tournament_format_id, manager_id)\n" +
                "VALUES (?,?,?,?,?,?,?)";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, tournament.getTournamentName());
//            ps.setDate(2, tournament.getStartDate());
//            ps.setDate(3, tournament.getEndDate());
            ps.setString(4, tournament.getLocation());
            ps.setString(5, tournament.getTournamentDescription());
            ps.setInt(6, tournament.getTournamentFormat().getFormatId());
            ps.setInt(7, tournament.getManager().getId());
            ps.executeUpdate();
            inserted = true;
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return inserted;
    }
    //todo
    //Updating specific data of tournament
    @Override
    public boolean updateTournament(Tournament tournament) {
        boolean updated = false;
        Connection conn = null;
        String sql = "UPDATE tournament SET tournament_name=?, start_date=?, end_date=?, location=?, " +
                "tournament_description=?, tournament_format_id=?, manager_id=? WHERE tournament_id=?";


        return updated;
    }

    //Deleting tournament by id
    @Override
    public boolean deleteTournamentById(int id) {
        boolean deleted;
        String sql = "DELETE FROM tournament WHERE tournament_id=?";
        deleted = deleteEntity(sql, id);

        return deleted;
    }

    //Extracting specific data of Tournament from ResultSet
    private static Tournament extractTournamentFromResultSet(ResultSet rs) {
        Tournament tournament = new Tournament();
        try {
            tournament.setTournamentId(rs.getInt("tournament_id"));
            tournament.setTournamentName(rs.getString("tournament_name"));
            tournament.setStartDate(rs.getDate("end_date"));
            tournament.setStartDate(rs.getDate("end_date"));
            tournament.setLocation(rs.getString("location"));
            tournament.setTournamentDescription(rs.getString("tournament_description"));
            tournament.setTournamentFormat(TournamentFormat.getTournamentFormatById(rs.getInt("tournament_format_id")));
            tournament.setManager(Manager.getManagerById(rs.getInt("manager_id")));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return tournament;
    }

    public static void main(String[] args) {
//        Tournament tournament = new Tournament();
//        Manager manager = new Manager();
//        manager.setId(1);//
//        tournament.setTournamentName("Evro-2016");
//        tournament.setTournamentName("2020-08-07 12:00:00");
//        tournament.setTournamentName("2020-08-07 12:00:10");
//        tournament.setLocation("Yerevan");
//        tournament.setTournamentDescription("bla bla bla");
//        tournament.setTournamentFormat(TournamentFormat.OLYMPIC);
//        tournament.setManager(manager);
//        boolean inserted = new TournamentDaoImpl().addTournament(tournament);

//        Tournament tournament = new TournamentDaoImpl().getTournamentById(13);
//        System.out.println(tournament);

//        boolean deleted = new TournamentDaoImpl().deleteTournamentById(7);
        List<Tournament> tournamentList = new TournamentDaoImpl().getTournamentList();
        System.out.println(tournamentList);
    }
}
