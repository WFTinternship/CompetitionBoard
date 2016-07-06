package com.workfront.intern.cb.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.workfront.intern.cb.common.Manager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl extends GenericDao implements ManagerDao {
    /**
     * returning manager by id
     */
    @Override
    public Manager getManagerById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Manager manager = null;
        String sql = "SELECT * FROM manager WHERE manager_id=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                manager = new Manager();
                extractManagerFromResultSet(rs, manager);
            }
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps, rs);
        }
        return manager;
    }

    /**
     * returning manager by login

     */
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
                manager = new Manager();
                extractManagerFromResultSet(rs, manager);
            }
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps, rs);
        }
        return manager;
    }

    /**
     * return all managers in db
     */
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
                manager = new Manager();
                extractManagerFromResultSet(rs, manager);
                managerList.add(manager);
            }
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps, rs);
        }
        return managerList;
    }

    /**
     * added new manager in db
     */
    @Override
    public boolean addManager(String login, String pass) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        String sql = "INSERT INTO manager(login, password) VALUES (?, ?)";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, passToEncrypt(pass));
            rows = ps.executeUpdate();

        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps);
        }
        return rows == 1;
    }

    /**
     * delete manager by id
     */
    @Override
    public boolean deleteManagerById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        String sql = "DELETE FROM manager WHERE manager_id=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rows = ps.executeUpdate();

        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection(conn, ps);
        }


        return rows == 1;
    }

    /**
     * delete manager by login and password
     */
    @Override
    public boolean deleteManagerByLogin(String login, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;

        String sql = "DELETE FROM manager WHERE login=? and password=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            rows = ps.executeUpdate();
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps);
        }
        return rows == 1;
    }

    /**
     *
     * */
    private Manager extractManagerFromResultSet(ResultSet rs, Manager manager) {
        try {
            manager.setId(rs.getInt("manager_id"));
            manager.setLogin(rs.getString("login"));
            manager.setPassword(rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    /**
     * method encrypt password, uses SHA-256.
     */
    private static String passToEncrypt(String password) {
        StringBuffer sb = null;
        String passToEncrypt = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();

            //convert the byte to hex format
            sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (sb != null) {
            passToEncrypt = sb.toString();
        }
        return passToEncrypt;
    }

    public static void main(String[] args) {
        boolean addToDb = new ManagerDaoImpl().addManager("artbabayan", "art123");
        System.out.println(addToDb);

//        Manager manager = new ManagerDaoImpl().getManagerById(1);
//        System.out.println(manager);

//        Manager manager = new ManagerDaoImpl().getManagerByLogin("arturbabayan");
//        System.out.println(manager);

//        List<Manager> managerList = new ManagerDaoImpl().getManagerList();
//        System.out.println(managerList);

//        boolean deleteByLoginAndPassword = new ManagerDaoImpl().deleteManagerByLogin("artbabayan", "");
//        System.out.println(deleteByLoginAndPassword);
//
//        boolean deleteById = new ManagerDaoImpl().deleteManagerById(16);
//        System.out.println(deleteById);

    }
}
