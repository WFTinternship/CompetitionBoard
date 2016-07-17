package com.workfront.intern.cb.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String DB_LOGIN = "root";
    private static final String DB_PASS = "root";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "competition_board";
    private static final String DB_CONNECTION_PROPERTIES = "?useUnicode=true&characterEncoding=utf-8";

    private static ComboPooledDataSource poolConn;

    static {
        init();
    }

    private static void init() {
        try {
            poolConn = new ComboPooledDataSource();
            poolConn.setDriverClass(DB_DRIVER);
            poolConn.setJdbcUrl(DB_URL + DB_NAME + DB_CONNECTION_PROPERTIES);
            poolConn.setUser(DB_LOGIN);
            poolConn.setPassword(DB_PASS);
            poolConn.setInitialPoolSize(50);
            poolConn.setMinPoolSize(10);
            poolConn.setAcquireIncrement(50);
            poolConn.setMaxPoolSize(100);
            poolConn.setMaxStatements(100);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Create and return DataSource with DB, use connection pool method
     */
    public static DataSource getDataSource() {
        return poolConn;
    }

    /**
     * Create and return polled connection with DB, use connection pool method
     */
    public static Connection getPooledConnection() {
        Connection dbConnection;
        try {
            dbConnection = getDataSource().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return dbConnection;
    }

    /**
     * Create and return connection with DB, non pools
     */
    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(
                    DB_URL + DB_NAME + DB_CONNECTION_PROPERTIES, DB_LOGIN, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}