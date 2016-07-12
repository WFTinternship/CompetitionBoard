package com.workfront.intern.cb.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;

public class DBManager {
    private static final String DB_LOGIN = "root";
    private static final String DB_PASS = "attask";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "competition_board";
    private static final String DB_CONNECTION_PROPERTIES = "?useUnicode=true&characterEncoding=utf-8";

    private static ComboPooledDataSource poolConn = new ComboPooledDataSource();

    /**
     * create and return connection with DB, non pools
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

    /**
     * create and return polled connection with DB, use connection pool method
     * @return
     */
    public static Connection getPooledConnection() {
        Connection dbConnection = null;
        try {
            dbConnection = getDataSource().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    /**
     * create and return DataSource with DB, use connection pool method
     */
    static DataSource getDataSource() throws PropertyVetoException {
        poolConn.setDriverClass(DB_DRIVER);
        poolConn.setJdbcUrl(DB_URL + DB_NAME + DB_CONNECTION_PROPERTIES);
        poolConn.setUser(DB_LOGIN);
        poolConn.setPassword(DB_PASS);
        poolConn.setInitialPoolSize(5);
        poolConn.setMinPoolSize(5);
        poolConn.setAcquireIncrement(5);
        poolConn.setMaxPoolSize(20);
        poolConn.setMaxStatements(100);

        return poolConn;
    }
}
