package com.workfront.intern.cb.dao;

import java.sql.*;

public abstract class GenericDao {

    protected void closeResources(Connection conn) {
        closeResources(conn, null);
    }

    protected void closeResources(Connection conn, Statement ps) {
        closeResources(conn, ps, null);
    }

    protected void closeResources(Connection conn, Statement ps, ResultSet rs) {
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
}
