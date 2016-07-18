package com.workfront.intern.cb.dao;

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

    //sxal method!
    boolean deleteEntity(String sql) {
        int rows = 0;
        boolean delete;
        delete = deleteEntity(sql);

        return delete;
    }


    /**
     * Deletes entry(es) according to specified SQL and ID param.
     */
    boolean deleteEntries(String sql, int id) {
        int rows = 0;
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
                rows = ps.executeUpdate();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            } finally {
                closeResources(conn, ps);
            }
        }
        return rows > 0;
    }

    /**
     * Deletes entries according to specified SQL statement.
     */
    boolean deleteEntries(String sql) {
        return deleteEntries(sql, 0);
    }
}
