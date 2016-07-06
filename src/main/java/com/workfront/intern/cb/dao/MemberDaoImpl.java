package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.List;

public class MemberDaoImpl extends GenericDao implements MemberDao {
    /**
     * method returns member by memberId
     */
    @Override
    public Member getMemberById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;

        String sql = "SELECT * FROM participant p" +
                " INNER JOIN member m ON p.participant_id = m.member_id WHERE p.participant_id=?";

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
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps, rs);
        }
        return member;
    }

    @Override
    public List<Member> getMemberList() {
        return null;
    }

    @Override
    public boolean addMember(Member member) {
        int row = 0;
        Connection conn = null;
        PreparedStatement ps_participant = null;
        PreparedStatement ps_member = null;
        ResultSet rs = null;
        Participant participant;

        String sql_participant = "INSERT INTO participant(is_team, avatar, participant_info) VALUES (?,?,?)";
        String sql_member = "INSERT INTO member(member_id, name, surname, position, email) VALUES (?,?,?,?,?)";

        try {
            DataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            ps_participant = conn.prepareStatement(sql_participant, Statement.RETURN_GENERATED_KEYS);

            participant = new Member();
            ps_participant.setBoolean(1, participant.isTeam());
            ps_participant.setString(2, participant.getAvatar());
            ps_participant.setString(3, participant.getParticipantInfo());
            row = ps_participant.executeUpdate();

            rs = ps_participant.getGeneratedKeys();
            int memberId = 0;
            if (rs.next()) {
                memberId = rs.getInt(1);
            }

            if (row == 1) {
                ps_member = conn.prepareStatement(sql_member);
                ps_member.setInt(1, memberId);
                ps_member.setString(2, member.getName());
                ps_member.setString(3, member.getSurName());
                ps_member.setString(4, member.getPosition());
                ps_member.setString(5, member.getEmail());
                ps_member.executeUpdate();
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps_participant, rs);
        }
        return row == 1;
    }


    @Override
    public boolean updateMember(int memberId, String name, String surName, String position, String email, int participantId) {
        return false;
    }

    @Override
    public boolean deleteMember(Member member) {
        return false;
    }

    private Member extractMemberFromResultSet(ResultSet rs) {
        Member member = new Member();
        try {
            member.setId(rs.getInt("participant_id"));
            member.setIsTeam(rs.getBoolean("is_team"));
            member.setAvatar(rs.getString("avatar"));
            member.setParticipantInfo(rs.getString("participant_info"));
            member.setName(rs.getString("name"));
            member.setSurName(rs.getString("surname"));
            member.setPosition(rs.getString("position"));
            member.setEmail(rs.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public static void main(String[] args) {
        /**
         * Testing
         * */
//        Member member = new MemberDaoImpl().getMemberById(2);
//        System.out.println(member);
        boolean add = new MemberDaoImpl().addMember(
                new Member().setName("Axjik").setSurName("Sirun").setPosition("intern").setEmail("gmail.com"));

//        List<Member> memberList = new MemberDaoImpl().getMemberList();
//        System.out.println(memberList);
    }
}