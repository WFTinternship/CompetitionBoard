package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.common.util.StringHelper;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl extends GenericDao implements ManagerDao {
    private static final Logger LOG = Logger.getLogger(ManagerDaoImpl.class);

    private DataSource dataSource;

    public ManagerDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Returns manager by id
     */
    @Override
    public Manager getManagerById(int id) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Manager manager = null;

        String sql = "SELECT * FROM manager WHERE manager_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute statement
            rs = ps.executeQuery();
            if (rs.next()) {
                manager = extractManagerFromResultSet(rs);
            } else {
                throw new ObjectNotFoundException(String.format("Manager with id[%d] not found", id));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return manager;
    }

    //TODO
    /**
     * Returns manager by login
     */
    @Override
    public Manager getManagerByLogin(String login) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Manager manager = null;

        String sql = "SELECT * FROM manager WHERE login=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, login);

            // Execute statement
            rs = ps.executeQuery();
            if (rs.next()) {
                manager = extractManagerFromResultSet(rs);
            } else {
                throw new ObjectNotFoundException(String.format("Manager with login[%s] not found", login));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return manager;
    }

    /**
     * Returns all managers in db
     */
    @Override
    public List<Manager> getManagerList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Manager> managerList = new ArrayList<>();
        Manager manager;

        String sql = "SELECT * FROM manager";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                manager = extractManagerFromResultSet(rs);
                managerList.add(manager);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return managerList;
    }

    /**
     * Updates existing manager in db
     */
    @Override
    public boolean updateManager(int id, Manager manager) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        String sql = "UPDATE manager SET password=? WHERE manager_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, manager.getPassword());
            ps.setInt(2, id);

            // Execute statement
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps);
        }
        return rows == 1;
    }

    /**
     * Adds new manager in db
     */
    @Override
    public void addManager(Manager manager) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO manager(login, password) VALUES (?, ?)";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // prepare base participant insert query
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, manager.getLogin());
            ps.setString(2, StringHelper.passToEncrypt(manager.getPassword()));

            // insert base participant info
            ps.executeUpdate();

            // return generated key
            int id = acquireGeneratedKey(ps);
            manager.setId(id);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
    }

    /**
     * Deletes manager by id
     */
    @Override
    public boolean deleteManagerById(int id) {
        boolean deleted;

        String sql = "DELETE FROM manager WHERE manager_id=?";

        deleted = deleteEntries(sql, id);

        return deleted;
    }

    /**
     * Removes all managers
     */
    @Override
    public boolean deleteAll() {
        boolean deleted;

        String sql = "DELETE FROM manager";

        deleted = deleteEntries(sql);

        return deleted;
    }

    /**
     * Extracting specific data of Manager from ResultSet
     */
    private Manager extractManagerFromResultSet(ResultSet rs) throws SQLException {
        Manager manager = new Manager();
        manager.setId(rs.getInt("manager_id"));
        manager.setLogin(rs.getString("login"));
        manager.setPassword(rs.getString("password"));

        return manager;
    }
}
