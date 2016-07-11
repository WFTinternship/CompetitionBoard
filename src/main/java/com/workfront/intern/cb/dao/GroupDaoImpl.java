package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GroupDaoImpl extends GenericDao implements GroupDao {
    @Override
    public List<Group> getGroupListByManager(int id) {
        return null;
    }

    @Override
    public List<Group> getGroupInTournamentList(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM `group` WHERE tournament_id=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

            }

        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }


        return null;
    }
}
