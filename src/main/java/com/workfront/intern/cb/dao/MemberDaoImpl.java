package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;
import javafx.scene.input.DataFormat;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl extends GenericDao implements MemberDao {
    private static final Logger LOG = Logger.getLogger(MemberDaoImpl.class);

    private DataSource dataSource;

    public MemberDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
            // Acquire connection
            conn = DBManager.getPooledConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // update member data
            rs = ps.executeQuery();
            if (rs.next()) {
                member = extractMemberFromResultSet(rs);
            }
        } catch (SQLException e) {
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
            // Acquire connection
            conn = DBManager.getPooledConnection();

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                member = extractMemberFromResultSet(rs);
                memberList.add(member);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return memberList;
    }

    //Adding member to db
    @Override
    public boolean addMember(Member member) {
        boolean inserted = false;
        Connection conn = null;

        String sql_participant = "INSERT INTO participant(is_team, avatar, participant_info) VALUES (?,?,?)";
        String sql_member = "INSERT INTO member(member_id, name, surname, position, email) VALUES (?,?,?,?,?)";
        try {
            // Acquire connection
            conn = DBManager.getPooledConnection();

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
            ps.close();
            rs.close();

            // prepare member insert query
            ps = conn.prepareStatement(sql_member, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, memberId);
            ps.setString(2, member.getName());
            ps.setString(3, member.getSurName());
            ps.setString(4, member.getPosition());
            ps.setString(5, member.getEmail());

            // insert member data
//            ps.executeUpdate();
//            rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                member.setMemberId(rs.getInt(1));
//            }
            ps.close();
            rs.close();

            // commit transaction
            conn.commit();
            inserted = true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e1);
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
            // Acquire connection
            conn = DBManager.getPooledConnection();

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

            ps_participant.close();
            ps_member.close();

            // commit transaction
            conn.commit();
            updated = true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
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

    //Deleting member by id
    @Override
    public boolean deleteMember(int id) {
        boolean deleted;
        String sql = "DELETE FROM member WHERE member_id=?";
        deleted = deleteEntries(sql, id);

        return deleted;
    }

    @Override
    public boolean deleteAll() {
        boolean deleted;
        String sql = "DELETE FROM member";
        deleted = deleteEntries(sql);

        return deleted;
    }

    //Extracting specific data of Member from ResultSet
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
            LOG.error(e.getMessage(), e);
        }
        return member;
    }
}