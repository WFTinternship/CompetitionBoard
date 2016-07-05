package com.workfront.intern.cb.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl extends GenericDao implements MemberDao {
    /**
     * method returns member by memberId
     *
     * @param id
     * @return
     */
    @Override
    public Member getMemberById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;

        String sql = "SELECT * FROM participant" +
                " INNER JOIN member ON participant_id=member_id WHERE participant_id=?";

        try {
            ComboPooledDataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            member = new Member();
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

    /**
     * method returns member list
     *
     * @return
     */
    @Override
    public List<Member> getMemberList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member;
        List<Member> membersList = new ArrayList<>();
        String sql = "SELECT * FROM participant INNER JOIN member ON participant_id=member_id";

        try {
            ComboPooledDataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                member = new Member();
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

    /**
     * adding member to db
     *
     * @param member
     * @return
     */
    @Override
    public boolean addMember(Member member) {
        int row = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql_participant = "INSERT INTO participant(participant_id, is_team, avatar, participant_info) values (?,?,?,?)";
        String sql_member = "INSERT INTO member(member_id, name, surname, position, email) VALUES (?,?,?,?,?)";

        try {
            ComboPooledDataSource dataSource = DBManager.getDataSource();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql_participant, ps.RETURN_GENERATED_KEYS);

            rs = ps.getGeneratedKeys();
            int id = 1;
            while(rs.next()){
                id = rs.getInt(1);
            }
            PreparedStatement ps1;
            ps1 = conn.prepareStatement(sql_member);
            member = new Member();
            ps1.setInt(1, id);
            ps1.setString(2, member.getName() );
            ps1.setString(3, member.getSurName() );
            ps1.setString(4, member.getPosition());
            ps1.setString(5, member.getEmail() );
            row =  ps1.executeUpdate();
            conn.commit();

        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        }


        return row == 1;
    }

    /**
     * update member parameters
     *
     * @param memberId
     * @param name
     * @param surName
     * @param position
     * @param email
     * @param participantId
     * @return
     */
    @Override
    public boolean updateMember(int memberId, String name, String surName, String position,
                                String email, int participantId) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        String sql = "UPDATE member SET name=?, surname=?, position=?, email=?, participant_id=? WHERE member_id=?";

       return rows == 1;
    }

    @Override
    public boolean deleteMember(Member member) {
        return true;
    }

    private Member extractMemberFromResultSet(ResultSet rs, Member member) {
//        Member member = new Member();
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
//        Member member = new MemberDaoImpl().getMemberById(10);
//        System.out.println(member);

//        List<Member> memberList = new MemberDaoImpl().getMemberList();
//        System.out.println(memberList);



    }
}
