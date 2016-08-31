package com.workfront.intern.cb.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class DBManager {
    private static final String DB_LOGIN = "root";
    private static final String DB_PASS = "root";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "competition_board";
    private static final String DB_CONNECTION_OPTIONS = "?useUnicode=true&characterEncoding=utf-8";

    private static ComboPooledDataSource poolDataSource;

    static {
        init();
    }

    private static void init() {
        try {
            // Load DB properties
            ClassLoader classLoader = DBManager.class.getClassLoader();
            InputStream in = classLoader.getResourceAsStream("db.properties");
            Properties dbProps = new Properties();
            dbProps.load(in);
            in.close();

            // Initialize connection pool
            poolDataSource = new ComboPooledDataSource();
            if (!dbProps.isEmpty()) {
                poolDataSource.setDriverClass(dbProps.getProperty("db.driver"));
                poolDataSource.setJdbcUrl(dbProps.getProperty("db.url") + dbProps.getProperty("db.name") + "?" + dbProps.getProperty("db.connection.options"));
                poolDataSource.setUser(dbProps.getProperty("db.login"));
                poolDataSource.setPassword(dbProps.getProperty("db.pass"));
            } else {
                poolDataSource.setDriverClass(DB_DRIVER);
                poolDataSource.setJdbcUrl(DB_URL + DB_NAME + "?" + DB_CONNECTION_OPTIONS);
                poolDataSource.setUser(DB_LOGIN);
                poolDataSource.setPassword(DB_PASS);
            }

            // Set pool options
            poolDataSource.setInitialPoolSize(50);
            poolDataSource.setMinPoolSize(10);
            poolDataSource.setAcquireIncrement(50);
            poolDataSource.setMaxPoolSize(100);
            poolDataSource.setMaxStatements(100);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Create and return DataSource with DB, use connection pool method
     */
    public static DataSource getDataSource() {
        return poolDataSource;
    }

    /**
     * Create and return polled connection with DB, use connection pool method
     */
    public static Connection getPooledConnection() {
        Connection dbConnection;
        try {
            dbConnection = poolDataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return dbConnection;
    }

    /**
     * Create and return connection with DB, non pools
     */
    public static Connection getDirectConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(
                    DB_URL + DB_NAME + DB_CONNECTION_OPTIONS, DB_LOGIN, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}