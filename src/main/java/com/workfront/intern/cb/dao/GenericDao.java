package com.workfront.intern.cb.dao;

import java.sql.*;

/**
 * Created by Inmelet on 7/1/2016.
 */
public abstract class GenericDao {

    protected void closeConnection(Connection conn, Statement ps, ResultSet rs) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
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
