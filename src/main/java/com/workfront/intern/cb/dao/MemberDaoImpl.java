package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Member;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl extends GenericDao implements MemberDao {
    private static final Logger LOG = Logger.getLogger(MemberDaoImpl.class);

    //Gets member by memberId
    @Override
    public Member getMemberById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;
        String sql = "SELECT * FROM participant p INNER JOIN member m ON p.participant_id=m.member_id " +
                "WHERE m.member_id=?;";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                member = extractMemberFromResultSet(rs);
            }
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return member;
    }

    //Get all members
    @Override
    public List<Member> getMemberList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member;
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM participant p INNER JOIN member m ON p.participant_id=m.member_id";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                member = extractMemberFromResultSet(rs);
                memberList.add(member);
            }
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return memberList;
    }

    // add member to db
    @Override
    public boolean addMember(Member member) {
        boolean inserted = false;
        Connection conn = null;

        String sql_participant = "INSERT INTO participant(is_team, avatar, participant_info) VALUES (?,?,?)";
        String sql_member = "INSERT INTO member(member_id, name, surname, position, email) VALUES (?,?,?,?,?)";

        try {
            // acquire polled connection
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();

            // start transaction
            conn.setAutoCommit(false);

            // prepare base participant insert query
            PreparedStatement ps = conn.prepareStatement(sql_participant, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, member.isTeam());
            ps.setString(2, member.getAvatar());
            ps.setString(3, member.getParticipantInfo());

            // insert base participant info
            ps.executeUpdate();

            // acquire assigned ID
            ResultSet rs = ps.getGeneratedKeys();
            int memberId = 0;
            if (rs.next()) {
                memberId = rs.getInt(1);
            }
            rs.close();
            ps.close();

            // prepare member insert query
            ps = conn.prepareStatement(sql_member);
            ps.setInt(1, memberId);
            ps.setString(2, member.getName());
            ps.setString(3, member.getSurName());
            ps.setString(4, member.getPosition());
            ps.setString(5, member.getEmail());

            // insert member data
            ps.executeUpdate();
            rs.close();
            ps.close();

            // commit transaction
            conn.commit();
            inserted = true;
        } catch (PropertyVetoException | SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e);
            }
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn);
        }
        return inserted;
    }

    //Updating specific data of member
    @Override
    public boolean updateMember(Member member) {
        boolean updated = false;
        Connection conn = null;
        String sql_participant = "UPDATE participant SET avatar=?, participant_info=? WHERE participant_id=?";
        String sql_member = "UPDATE member SET name=?, surname=?, position=?, email=? WHERE member_id=?";

        try {
            // acquire polled connection
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();

            // start transaction
            conn.setAutoCommit(false);

            PreparedStatement ps_participant = conn.prepareStatement(sql_participant);
            ps_participant.setString(1, member.getAvatar());
            ps_participant.setString(2, member.getParticipantInfo());
            ps_participant.setInt(3, member.getId());

            // update base participant info
            ps_participant.executeUpdate();

            PreparedStatement ps_member = conn.prepareStatement(sql_member);
            ps_member.setString(1, member.getName());
            ps_member.setString(2, member.getSurName());
            ps_member.setString(3, member.getPosition());
            ps_member.setString(4, member.getEmail());
            ps_member.setInt(5, member.getId());

            // update member data
            ps_member.executeUpdate();

            ps_member.close();
            ps_participant.close();

            // commit transaction
            conn.commit();
            updated = true;
        } catch (PropertyVetoException | SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated;
    }

    @Override
    public boolean deleteMemberById(int id) {
        boolean deleted = false;
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM member WHERE member_id=?";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            deleted = true;
        } catch (PropertyVetoException | SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeResources(conn, ps);
        }
        return deleted;
    }

    private static Member extractMemberFromResultSet(ResultSet rs) {
        Member member = new Member();
        try {
            member.setId(rs.getInt("participant_id"));
            member.setAvatar(rs.getString("avatar"));
            member.setParticipantInfo(rs.getString("participant_info"));
            member.setId(rs.getInt("member_id"));
            member.setName(rs.getString("name"));
            member.setSurName(rs.getString("surname"));
            member.setPosition(rs.getString("position"));
            member.setEmail(rs.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }
}