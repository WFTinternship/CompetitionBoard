package com.workfront.intern.cb.dao;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;

abstract class GenericDao {
    private static final Logger LOG = Logger.getLogger(GenericDao.class);

    // Closed entity resources, when Statement and ResultSet of null
    void closeResources(Connection conn) {
        closeResources(conn, null);
    }

    // Closed entity resources, when ResultSet of null
    void closeResources(Connection conn, Statement ps) {
        closeResources(conn, ps, null);
    }

    // Closed entity resources
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

    // Deleted specific entity by sql
    boolean deleteEntity(String sql) {
        boolean deleted;
        deleted = deleteEntity(sql, 0);

        return deleted;
    }

    // Deleted specific entity by sql and id
    boolean deleteEntity(String sql, int id) {
        boolean deleted = false;
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
                ps.executeUpdate();
                deleted = true;
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            } finally {
                closeResources(conn, ps);
            }
        }
        return deleted;
    }
}
