package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Member;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.*;

public class MemberDaoImpl extends GenericDao implements MemberDao {
    private static final Logger LOG = Logger.getLogger(MemberDaoImpl.class);

    /**
     * Gets member by memberId
     */
    @Override
    public Member getMemberById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;

        String sql = "SELECT * FROM participant p " +
                "INNER JOIN member m ON p.participant_id = m.member_id " +
                "WHERE p.participant_id=?";

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

    @Override
    public List<Member> getMemberList() {
        return null;
    }

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
            LOG.error(e.getMessage(), e);
        } finally {
            closeResources(conn);
        }
        return inserted;
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
        List<Member> memberList = extractMemberListFromResultSet(rs);
        return memberList.size() == 0 ? null : memberList.get(0);
    }

    private List<Member> extractMemberListFromResultSet(ResultSet rs) {
        Map<Integer, Member> items = new HashMap<>();
        try {
            while (rs.next()){
                int id = rs.getInt("participant_id");

                if (!items.containsKey(id)) {
                    Member member = new Member();

                    member.setId(id);
                    member.setAvatar(rs.getString("avatar"));
                    member.setParticipantInfo(rs.getString("participant_info"));
                    member.setName(rs.getString("name"));
                    member.setSurName(rs.getString("surname"));
                    member.setPosition(rs.getString("position"));
                    member.setEmail(rs.getString("email"));

                    items.put(id, member);
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
        return new ArrayList<>(items.values());
    }

    public static void main(String[] args) {
        /**
         * Testing
         * */
//        Member member = new MemberDaoImpl().getMemberById(2);
//        System.out.println(member);

        Member member = new Member().setName("Axjik").setSurName("Sirun").setPosition("intern").setEmail("gmail.com");
        member.setAvatar("avatar_" + System.currentTimeMillis());
        member.setParticipantInfo("info_" + System.currentTimeMillis());

        boolean add = new MemberDaoImpl().addMember(member);

//        List<Member> memberList = new MemberDaoImpl().getMemberList();
//        System.out.println(memberList);
    }
}