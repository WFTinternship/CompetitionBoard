package com.workfront.intern.cb.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;

public class DBManager {
    private static final String LOGIN_DB = "root";
    private static final String PASS_DB = "root";
    private static final String DRIVER_DB = "com.mysql.jdbc.Driver";
    private static final String URL_DB = "jdbc:mysql://localhost:3306/";
    private static final String NAME_DB = "competition_board";
    private static final String CONNECTION_PROP = "?useUnicode=true&characterEncoding=utf-8";

    private static ComboPooledDataSource poolConn = new ComboPooledDataSource();

    /**
     * create and return connection with DB, non pools
     */
    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DRIVER_DB);
            dbConnection = DriverManager.getConnection(
                    URL_DB + NAME_DB + CONNECTION_PROP, LOGIN_DB, PASS_DB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    /**
     * create and return connection with DB, use connection pool method
     */
    static ComboPooledDataSource getDataSource() throws PropertyVetoException {
        poolConn.setDriverClass(DRIVER_DB);
        poolConn.setJdbcUrl(URL_DB + NAME_DB + CONNECTION_PROP);
        poolConn.setUser(LOGIN_DB);
        poolConn.setPassword(PASS_DB);
        poolConn.setInitialPoolSize(5);
        poolConn.setMinPoolSize(5);
        poolConn.setAcquireIncrement(5);
        poolConn.setMaxPoolSize(20);
        poolConn.setMaxStatements(100);

        return poolConn;
    }
}
