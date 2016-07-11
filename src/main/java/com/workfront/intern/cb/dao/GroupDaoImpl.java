package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Group;
import com.workfront.intern.cb.common.Tournament;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl extends GenericDao implements GroupDao {
    private static final Logger LOG = Logger.getLogger(GroupDaoImpl.class);

    @Override
    public int getParticipantsCount(Tournament tournament) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM `group` WHERE tournament_id";

        return 0;
    }

    @Override
    public List<Group> getGroupInTournamentList(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM `group` WHERE tournament_id=?";
        List<Group> groupList = new ArrayList<>();
        Group group = null;

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                group = extractGroupFromResultSet(rs);
                groupList.add(group);
            }
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return groupList;
    }

    //Extracting specific data of Group from ResultSet
    private static Group extractGroupFromResultSet(ResultSet rs) {
        Group group = new Group();
        try {
            group.setGroupId(rs.getInt("group_id"));
            group.setParticipantsCount(rs.getInt("participants_count"));
            group.setRound(rs.getInt("round"));
            group.setNextRoundParticipnats(rs.getInt("next_round_participants"));
            group.setTournament(new Tournament().getTournamentById(rs.getInt("tournament_id")));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return group;
    }


    public static void main(String[] args) {
        List<Group> groupList = new ArrayList<>();
        groupList = new GroupDaoImpl().getGroupInTournamentList(1);
        System.out.println(groupList);
    }
}
