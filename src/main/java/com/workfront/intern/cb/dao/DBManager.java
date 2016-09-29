package com.workfront.intern.cb.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.workfront.intern.cb.common.TournamentFormat;
import com.workfront.intern.cb.common.util.CollectionsHelper;
import com.workfront.intern.cb.common.util.FileHelper;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBManager {
    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static final String DB_LOGIN = "root";
    private static final String DB_PASS = "root";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "competition_board";
    private static final String DB_CONNECTION_OPTIONS = "useUnicode=true&characterEncoding=utf-8";

    private static ComboPooledDataSource poolDataSource;
    private static ComboPooledDataSource poolDataSourceForTestDB;

    static {
        init();
    }

    /**
     * Executes Databases initialization code.
     */
    private static void init() {
        try {
            boolean connectionAvailable = validateMySQLConnection();
            if (!connectionAvailable) {
                throw new RuntimeException(String.format("Database not available: %s", DB_URL + DB_NAME));
            }

            poolDataSource = (ComboPooledDataSource) createDataSource();
            checkInitDefaultData(DB_NAME);
            checkInitTestDatabase();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private static DataSource createDataSource() {
        return createDataSource(null);
    }

    /**
     *
     */
    private static DataSource createDataSource(String dbName) {
        ComboPooledDataSource ds;
        try {
            // Load DB properties
            ClassLoader classLoader = DBManager.class.getClassLoader();
            InputStream in = classLoader.getResourceAsStream("db.properties");
            Properties dbProps = new Properties();
            dbProps.load(in);
            in.close();

            // Initialize connection pool
            ds = new ComboPooledDataSource();
            if (!dbProps.isEmpty()) {
                String targetDBName = dbName == null ? dbProps.getProperty("db.name") : dbName;
                ds.setDriverClass(dbProps.getProperty("db.driver"));
                ds.setJdbcUrl(dbProps.getProperty("db.url") + targetDBName + "?" + dbProps.getProperty("db.connection.options"));
                ds.setUser(dbProps.getProperty("db.login"));
                ds.setPassword(dbProps.getProperty("db.pass"));
            } else {
                ds.setDriverClass(DB_DRIVER);
                ds.setJdbcUrl(DB_URL + dbName + "?" + DB_CONNECTION_OPTIONS);
                ds.setUser(DB_LOGIN);
                ds.setPassword(DB_PASS);
            }

            // Set pool options
            ds.setInitialPoolSize(50);
            ds.setMinPoolSize(10);
            ds.setAcquireIncrement(50);
            ds.setMaxPoolSize(200);
            ds.setMaxStatements(100);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return ds;
    }

    /**
     * Check/Initializes the Test database.
     */
    private static void checkInitTestDatabase() throws Exception {
        boolean testDatabaseExists = false;

        try {
            testDatabaseExists = validateMySQLConnection(DB_NAME + "_test");
        } catch (Exception ex) {
            LOG.warn(ex.getMessage(), ex);
        }

        if (!testDatabaseExists) {
            createPopulateTestDatabase();
        }
    }

    /**
     * Creates the Test database and populates with default application data.
     * @throws Exception
     */
    private static void createPopulateTestDatabase() throws Exception {
        Class.forName(DB_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASS);
        Statement s = con.createStatement();
        int result = s.executeUpdate("CREATE DATABASE " + DB_NAME + "_test");
        LOG.info(String.format("Create Test database execution returned (%s)", result));

        String testDatabaseName = DB_NAME + "_test";
        con = DriverManager.getConnection(DB_URL + testDatabaseName, DB_LOGIN, DB_PASS);

        loadAndExecuteScript(con, true);
        checkInitDefaultData(testDatabaseName);
    }

    /**
     * Loads/executed provided database scripts to initialize
     * the database and populate with static data.
     *
     */
    public static void loadAndExecuteScript(Connection connection, boolean showExecutionLog) throws Exception {
        final String testDatabaseFileName = "DB_Full_test.sql";

        ClassLoader classLoader = DBManager.class.getClassLoader();
        java.net.URL url = classLoader.getResource(testDatabaseFileName);
        if (url == null)
            throw new RuntimeException(String.format("DB file %s not found", testDatabaseFileName));
        File execPlanFile = new File(url.getFile());

        if (!execPlanFile.exists()) {
            throw new RuntimeException(String.format("Test Database script file not found: %s", testDatabaseFileName));
        } else {
            executeCreateScript(connection, testDatabaseFileName, true);
        }
    }

    @SuppressWarnings("ConstantConditions")
    private static void executeCreateScript(Connection connection, String scriptFileName, boolean showExecutionLog) throws SQLException, IOException {
        if (showExecutionLog) {
            LOG.info(String.format("Executing SQL script %s", scriptFileName));
        }

        ClassLoader classLoader = DBManager.class.getClassLoader();
        File file = new File(classLoader.getResource(scriptFileName).getFile());
//        String query = readFileContent(file);
//        PreparedStatement ps = connection.prepareStatement(query);
//        ps.executeUpdate();
//        ps.close();

        ScriptRunner runner = new ScriptRunner(connection, false, false);
        runner.runScript(new BufferedReader(new FileReader(file)));

        if (showExecutionLog) {
            LOG.info("...done.");
        }
    }

    /**
     *
     * @return
     * @throws Exception
     */
    private static boolean validateMySQLConnection() throws Exception {
        return validateMySQLConnection(DB_NAME);
    }

    private static boolean validateMySQLConnection(String databaseName) throws Exception {
        Class.forName(DB_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL + databaseName, DB_LOGIN, DB_PASS);

        Integer result = null;
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT 1 FROM DUAL");

            while (rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }

        return result != null && result == 1;
    }

    /**
     *
     */
    private static boolean validateTestDatabase(Connection con) {
        Integer result = null;
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT member_id FROM member");

            while (rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            LOG.debug(ex.getMessage(), ex);
        }
        return result != null && result > 0;
    }

    /**
     * Create and return DataSource with DB, use connection pool method
     */
    public static DataSource getDataSource() {
        return poolDataSource;
    }

    public static DataSource getTestDataSource() {
        if (poolDataSourceForTestDB == null) {
            poolDataSourceForTestDB = (ComboPooledDataSource) createDataSource(DB_NAME + "_test");
        }
        return poolDataSourceForTestDB;
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
    protected static Connection getDirectConnection(String dbName) {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(
                    DB_URL + dbName + DB_CONNECTION_OPTIONS, DB_LOGIN, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    // region <DEFAULT DATA>

    /**
     * Checks/Inserts application default data.
     */
    private static void checkInitDefaultData(String dbName) throws Exception {
        checkInitTournamentFormats(dbName);
    }

    /**
     * Adds default values for Tournament Formats.
     */
    private static void checkInitTournamentFormats(String dbName) throws Exception {
        DataSource dataSource = dbName.equals(DB_NAME) ? getDataSource() : createDataSource(dbName);
        TournamentDao tournamentDao = new TournamentDaoImpl(dataSource);
        List<TournamentFormat> tournamentFormats = tournamentDao.getTournamentFormats();
        if (CollectionsHelper.isBlank(tournamentFormats)) {
            for (TournamentFormat format : TournamentFormat.values()) {
                tournamentDao.addTournamentFormat(format);
            }
        }
    }

    // endregion

    // region <HELPERS>

    private static String readFileContent(File file) {
        try {
            return FileHelper.readFileContent(file);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private static List<String> readFileToList(File file) {
        try {
            List<String> list = new ArrayList<>();
            FileHelper.readFileToList(file, list);
            return list;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    // endregion

}