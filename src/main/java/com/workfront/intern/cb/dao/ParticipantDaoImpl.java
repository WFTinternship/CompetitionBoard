package com.workfront.intern.cb.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDaoImpl extends GenericDao implements ParticipantDao {
    /**
     * method returns participant by participantId
     */
    @Override
    public Participant getParticipantById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Participant participant = null;
        String sql = "SELECT * FROM participant INNER JOIN member ON participant_id=member_id \n" +
                "WHERE participant_id=?;";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                participant.setId(rs.getInt("participant_id"));
                participant.setIsTeam(rs.getBoolean("is_team"));
                participant.setAvatar(rs.getString("avatar"));
                participant.setParticipantInfo(rs.getString("participant_info"));


            }
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps, rs);
        }
        return participant;
    }

    /**
     * method returns all participant
     */
    @Override
    public List<Participant> getParticipantList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Participant participant = null;
        List<Participant> participantList = new ArrayList<>();
        String sql = "SELECT * FROM participant";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
//                participant = new Participant();
                extractParticipantFromResultSet(rs, participant);
                participantList.add(participant);
            }
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps, rs);
        }
        return participantList;
    }

    /**
     * adding participant to db.
     * return one (1) if the row is added
     */
    @Override
    public boolean addParticipant(Participant participant) {
        Connection conn = null;
        PreparedStatement ps = null;
        int row = 0;
        String sql = "INSERT INTO participant(is_team, avatar, participant_info) VALUES(?, ?, ?)";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, participant.isTeam());
            ps.setString(2, participant.getAvatar());
            ps.setString(3, participant.getParticipantInfo());
            row = ps.executeUpdate();
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps);
        }
        return row == 1;
    }

    /**
     * update participant parameters in db
     */
    @Override
    public boolean updateParticipant(int participantId, boolean isTeam, String avatar, String participantInfo) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        String sql = "UPDATE participant SET is_team=?, avatar=?, participant_info=? WHERE participant_id=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, isTeam);
            ps.setString(2, avatar);
            ps.setString(3, participantInfo);
            ps.setInt(4, participantId);
            rows = ps.executeUpdate();

        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps);
        }
        return rows == 1;
    }

    /**
     * method deleting participant by id in db.
     * return one (1) if the row is deleted
     */
    @Override
    public boolean deleteParticipantById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM participant WHERE participant_id=?";
        int rows = 0;

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rows = ps.executeUpdate();
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps);
        }
        return rows == 1;
    }

    /**
     * method getting specific data from db table and set to the participant
     *
     * @param rs
     * @param participant
     * @return
     */
    private Participant extractParticipantFromResultSet(ResultSet rs, Participant participant) {
//        try {
//            participant.setId(rs.getInt("participant_id"));
//            participant.setIsTeam(rs.getBoolean("is_team"));
//            participant.setAvatar(rs.getString("avatar"));
//            participant.setParticipantInfo(rs.getString("participant_info"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return participant;
    }

    public static void main(String[] args) {
        /**
         * Test
         * */
        Participant participant = new ParticipantDaoImpl().getParticipantById(10);
        System.out.println(participant);

//        List<Participant> list = new ParticipantDaoImpl().getParticipantList();
//        System.out.println(list);

//        boolean added1 = new ParticipantDaoImpl().addParticipant(0, "", "");
//       boolean added2 = new ParticipantDaoImpl().addParticipant(new Participant(false, "aa", "bb"));
//        System.out.println(added1 + " " + added2);

//        boolean update = new ParticipantDaoImpl().updateParticipant(20, true, " ", " ");
//        System.out.println(update);
    }
}
