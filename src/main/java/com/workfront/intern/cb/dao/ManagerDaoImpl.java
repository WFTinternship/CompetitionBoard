package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.util.StringHelper;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl extends GenericDao implements ManagerDao {
    private static final Logger LOG = Logger.getLogger(ManagerDaoImpl.class);

    //Returning manager by id
    @Override
    public Manager getManagerById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Manager manager = null;

        String sql = "SELECT * FROM manager WHERE manager_id=?";
        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute statement
            rs = ps.executeQuery();
            if (rs.next()) {
                manager = extractManagerFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return manager;
    }

    //Returning manager by login
    @Override
    public Manager getManagerByLogin(String login) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Manager manager = null;
        String sql = "SELECT * FROM manager WHERE login=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                manager = extractManagerFromResultSet(rs);
            } else {
                return null;
            }
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return manager;
    }

    //Return all managers in db
    @Override
    public List<Manager> getManagerList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Manager> managerList = new ArrayList<>();
        String sql = "SELECT * FROM manager";
        Manager manager;

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                manager = extractManagerFromResultSet(rs);
                managerList.add(manager);
            }
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return managerList;
    }

    //Added new manager in db
    @Override
    public boolean addManager(Manager manager) {
        Connection conn = null;
        String sql = "INSERT INTO manager(login, password) VALUES (?, ?)";

        PreparedStatement ps = null;
        int rows = 0;
        try {
            // acquire polled connection
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();

            // prepare base participant insert query
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, manager.getLogin());
            ps.setString(2, StringHelper.passToEncrypt(manager.getPassword()));

            // insert base participant info
            rows = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                manager.setId(rs.getInt(1));
            }
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return rows == 1;
    }

    //Deleting manager by id
    @Override
    public boolean deleteManagerById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        String sql = "DELETE FROM manager WHERE manager_id=?";

        try {
            conn = DBManager.getPooledConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return rows == 1;
    }

    @Override
    public boolean deleteAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        String sql = "DELETE FROM manager";

        try {
            conn = DBManager.getPooledConnection();
            ps = conn.prepareStatement(sql);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return rows > 0;
    }

    //Extracting specific data of Manager from ResultSet
    private Manager extractManagerFromResultSet(ResultSet rs) throws SQLException {
        Manager manager = new Manager();

        manager.setId(rs.getInt("manager_id"));
        manager.setLogin(rs.getString("login"));
        manager.setPassword(rs.getString("password"));

        return manager;
    }

    public static void main(String[] args) {
        Manager manager = new ManagerDaoImpl().getManagerById(9999);
        System.out.println(manager.getId());

    }
}
