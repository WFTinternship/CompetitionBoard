package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.util.StringHelper;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;


public class ParticipantDaoImpl extends GenericDao implements ParticipantDao {
    private static final Logger LOG = Logger.getLogger(ManagerDaoImpl.class);

    private DataSource dataSource;

    public ParticipantDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Adds new participant in db
     */
    @Override
    public boolean addParticipant(Participant participant) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rows = 0;

        String sql = "INSERT INTO participant(avatar, participant_info) VALUES (?, ?)";
        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // prepare base participant insert query
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, participant.getAvatar());
            ps.setString(2, participant.getParticipantInfo());

            // insert base participant info
            rows = ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                participant.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return rows == 1;
    }


    @Override
    public Participant getParticipantById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Participant participant = new Member();

        String sql = "SELECT * FROM participant WHERE participant_id=?";
        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute statement
            rs = ps.executeQuery();
            if (rs.next()) {
                participant = extractParticipantFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return participant;
    }

    private Participant extractParticipantFromResultSet(ResultSet rs) throws SQLException {
        Participant participant = new Member();
        participant.setId(rs.getInt("participant_id"));
        participant.setAvatar(rs.getString("avatar"));
        participant.setParticipantInfo(rs.getString("participant_info"));

        return participant;
    }

    @Override
    public boolean updateParticipant(int id, Participant participant) {
        return false;
    }

    @Override
    public boolean deleteParticipantbyId(int id) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        boolean deleted;

        String sql = "DELETE FROM participant";

        deleted = deleteEntries(sql);

        return deleted;
    }
}
