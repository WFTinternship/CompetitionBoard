package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.apache.log4j.Logger;

import java.sql.*;

abstract class GenericDao {
    private static final Logger LOG = Logger.getLogger(GenericDao.class);

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
    void deleteEntries(String sql, int id) throws ObjectNotFoundException {
        if (sql != null) {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                // Acquire connection
                conn = DBManager.getPooledConnection();

                // Initialize statement
                ps = conn.prepareStatement(sql);
                if (id > 0) {
                    ps.setInt(1, id);
                }

                // Execute statement
                ps.executeUpdate();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            } finally {
                closeResources(conn, ps);
            }
        }
    }

    /**
     * Deletes entries according to specified SQL statement.
     */
    void deleteEntries(String sql) throws ObjectNotFoundException {
        deleteEntries(sql, 0);
    }

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
}
