package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.TournamentFormat;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

abstract class GenericDao {
    private static final Logger LOG = Logger.getLogger(GenericDao.class);

    protected DataSource dataSource;

    /**
     * Closed DB resources, when Statement and ResultSet of null
     */
    void closeResources(Connection conn) {
        closeResources(conn, null);
    }

    /**
     * Closed DB resources, when ResultSet of null
     */
    void closeResources(Connection conn, Statement ps) {
        closeResources(conn, ps, null);
    }

    /**
     * Closed DB resources
     */
    void closeResources(Connection conn, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Deletes entry(es) according to specified SQL and ID param.
     */
    void deleteEntry(String sql, int id) throws ObjectNotFoundException, FailedOperationException {
        if (sql != null) {
            Connection conn = null;
            PreparedStatement ps = null;

            if (id <= 0) {
                throw new FailedOperationException(String.format("Invalid ID provided: %d", id));
            }

            try {
                // Acquire connection
                conn = dataSource.getConnection();

                // Initialize statement
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                // Execute statement
                int result = ps.executeUpdate();
                if (result == 0) {
                    throw new ObjectNotFoundException(String.format("Entity with ID=%s not found", id));
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                throw new FailedOperationException(e.getMessage(), e);
            } finally {
                closeResources(conn, ps);
            }
        }
    }

    /**
     * Deletes all entries according to specified SQL.
     */
    void deleteAllEntries(String sql) throws FailedOperationException {
        if (sql != null) {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                // Acquire connection
                conn = dataSource.getConnection();

                // Initialize statement
                ps = conn.prepareStatement(sql);

                // Execute statement
                ps.executeUpdate();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                throw new FailedOperationException(e.getMessage(), e);
            } finally {
                closeResources(conn, ps);
            }
        }
    }

    /**
     * Generated key from PreparedStatement
     */
    int acquireGeneratedKey(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.getGeneratedKeys();
        Integer id = null;
        if (rs.next()) {
            id = (rs.getInt(1));
        }
        rs.close();
        if (id == null)
            throw new RuntimeException("Generated ID was NULL");
        return id;
    }

    String parseTournamentFormatIdToString(int inputId) {



        return null;
    }
}
