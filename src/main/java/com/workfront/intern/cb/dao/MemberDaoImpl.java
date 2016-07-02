package com.workfront.intern.cb.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.workfront.intern.cb.common.Member;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl extends GenericDao implements MemberDao {

    @Override
    public Member getMemberById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;

        String sql = "SELECT * FROM member WHERE member_id=?";

        try {
            ComboPooledDataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            member = new Member(id);
            if (rs.next()) {
                extractMemberFromResultSet(rs, member);
            }
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps, rs);
        }
        return member;
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> membersList = new ArrayList<>();
        String sql = "SELECT * FROM member";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member;

        try {
            ComboPooledDataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            member = new Member();
            if (rs.next()) {
                extractMemberFromResultSet(rs, member);
                membersList.add(member);
            }
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ps, rs);
        }

        return membersList;
    }

    @Override
    public void updateMember(Member member) {
        // TODO:
    }

    @Override
    public void deleteMember(Member member) {
        // TODO:
    }

    private Member extractMemberFromResultSet(ResultSet rs, Member member) {
        try {
            member.setMemberId(rs.getInt("member_id"));
            member.setName(rs.getString("name"));
            member.setSurName(rs.getString("surname"));
            member.setPosition(rs.getString("position"));
            member.setEmail(rs.getString("email"));
            member.setParticipantId(rs.getInt("participant_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public static void main(String[] args) {
        Member member = new MemberDaoImpl().getMemberById(2);
        System.out.println(member);
//        List<Member> memberList = new MemberDaoImpl().getAllMembers();
//        System.out.println(memberList);

    }
}
