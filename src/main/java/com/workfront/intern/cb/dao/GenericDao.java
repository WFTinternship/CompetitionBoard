package com.workfront.intern.cb.dao;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;

abstract class GenericDao {
    void closeResources(Connection conn) {
        closeResources(conn, null);
    }

    void closeResources(Connection conn, Statement ps) {
        closeResources(conn, ps, null);
    }

    void closeResources(Connection conn, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    Boolean deleteEntity(String sql, int id) {
        boolean deleted = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            deleted = true;
        } catch (PropertyVetoException | SQLException e) {
//            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return deleted;
    }
}
