package com.workfront.intern.cb.dao;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
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
     * Deletes specific entity by sql
     */
    boolean deleteEntity(String sql) {
        boolean deleted;
        deleted = deleteEntity(sql, 0);

        return deleted;
    }

    /**
     * Deletes specific entity by sql an id
     */
    boolean deleteEntity(String sql, int id) {
        int rows = 0;
        if (sql != null && id > 0) {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                // Acquire connection
                conn = DBManager.getPooledConnection();

                // Initialize statement
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                // Execute statement
                rows = ps.executeUpdate();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            } finally {
                closeResources(conn, ps);
            }
        }
        return rows > 0;
    }
}
