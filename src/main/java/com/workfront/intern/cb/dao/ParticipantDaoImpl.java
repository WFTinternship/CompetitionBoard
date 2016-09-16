package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Member;
import com.workfront.intern.cb.common.Participant;
import com.workfront.intern.cb.common.Team;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParticipantDaoImpl extends GenericDao implements ParticipantDao {
    private static final Logger LOG = Logger.getLogger(ParticipantDaoImpl.class);

    public ParticipantDaoImpl(@Autowired DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Adds specific participant: member or team
     */
    @Override
    public Participant addParticipant(Participant participant) throws FailedOperationException {
        if (participant instanceof Member) {
            return addMember((Member) participant);
        } else if (participant instanceof Team) {
            return addTeam((Team) participant);
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Gets specific participant - member or team, by id:
     */
    @Override
    public Participant getOne(Class<? extends Participant> cls, int id) throws FailedOperationException, ObjectNotFoundException {
        if (cls.equals(Member.class)) {
            return getMemberById(id);
        } else if (cls.equals(Team.class)) {
            return getTeamById(id);
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }


    /**
     * Gets specific participant list by tournament id - memberList or teamList
     */
    @Override
    public List<? extends Participant> getParticipantsByTournamentId(Class<? extends Participant> cls, int tournamentId)
                                                                                        throws FailedOperationException {
        if (cls.equals(Member.class)) {
            return getMemberListByTournamentId(tournamentId);
        } else if (cls.equals(Team.class)) {
            return getTeamListByTournamentId(tournamentId);
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Gets specific participant list - memberList or teamList
     */
    @Override
    public List<? extends Participant> getAll(Class<? extends Participant> cls) throws FailedOperationException {
        if (cls.equals(Member.class)) {
            return getMemberList();
        } else if (cls.equals(Team.class)) {
            return getTeamList();
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Updates specific participant - member or team
     */
    @Override
    public void update(int id, Participant participant) throws ObjectNotFoundException, FailedOperationException {
        if (participant instanceof Member) {
            updateMember(id, (Member) participant);
        } else if (participant instanceof Team) {
            updateTeam(id, (Team) participant);
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Deletes specific participant - member or team, by id:
     */
    @Override
    public void delete(int id) throws ObjectNotFoundException, FailedOperationException {
        String sql = "DELETE FROM participant WHERE participant_id=?";
        deleteEntry(sql, id);
    }

    /**
     * Deletes all specific participant - member or team, by id:
     */
    @Override
    public void deleteAll(Class<? extends Participant> cls) throws FailedOperationException {
        if (cls.equals(Member.class)) {
            deleteAllMembers();
        } else if (cls.equals(Team.class)) {
            deleteAllTeams();
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    // region <MEMBER>

    /**
     * Adds member to db
     */
    private Member addMember(Member member) throws FailedOperationException {
        Connection conn = null;

        String sql_participant = "INSERT INTO participant(is_team, avatar, participant_info) VALUES (?,?,?)";
        String sql_member = "INSERT INTO member(member_id, name, surname, position, email) VALUES (?,?,?,?,?)";
        try {
            // Acquire connection
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

            // acquire/apply assigned ID
            int memberId = acquireGeneratedKey(ps);
            member.setId(memberId);
            ps.close();

            // prepare member insert query
            ps = conn.prepareStatement(sql_member, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, memberId);
            ps.setString(2, member.getName());
            ps.setString(3, member.getSurName());
            ps.setString(4, member.getPosition());
            ps.setString(5, member.getEmail());

            // insert member data
            ps.executeUpdate();
            ps.close();

            // commit transaction
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return member;
    }

    /**
     * Gets member by member id
     */
    private Member getMemberById(int id) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;

        String sql = "SELECT * FROM participant p INNER JOIN member m ON p.participant_id=m.member_id " +
                "WHERE m.member_id=?;";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // update member data
            rs = ps.executeQuery();
            if (rs.next()) {
                member = extractMemberFromResultSet(rs);
            } else {
                throw new ObjectNotFoundException(String.format("Member with id[%d] not found", id));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return member;
    }

    /**
     * Gets member list by tournament id
     */
    private List<Member> getMemberListByTournamentId(int tournamentId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member;
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM participant p WHERE p.tournament_id=?";

        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, tournamentId);

            // update member data
            rs = ps.executeQuery();

            while (rs.next()) {
                member = extractMemberFromResultSet(rs);
                memberList.add(member);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return memberList;
    }

    /**
     * Gets member list
     */
    private List<Member> getMemberList() throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member;
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM participant p INNER JOIN member m ON p.participant_id=m.member_id";

        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);

            // update member data
            rs = ps.executeQuery();

            while (rs.next()) {
                member = extractMemberFromResultSet(rs);
                memberList.add(member);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return memberList;
    }

    /**
     * Updating specific data of member
     */
    private Member updateMember(int id, Member member) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;

        String sql_participant = "UPDATE participant SET avatar=?, participant_info=? WHERE participant_id=?";
        String sql_member = "UPDATE member SET name=?, surname=?, position=?, email=? WHERE member_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // start transaction
            conn.setAutoCommit(false);

            PreparedStatement ps_participant = conn.prepareStatement(sql_participant);
            ps_participant.setString(1, member.getAvatar());
            ps_participant.setString(2, member.getParticipantInfo());
            ps_participant.setInt(3, id);

            // update base participant info
            ps_participant.executeUpdate();

            PreparedStatement ps_member = conn.prepareStatement(sql_member);
            ps_member.setString(1, member.getName());
            ps_member.setString(2, member.getSurName());
            ps_member.setString(3, member.getPosition());
            ps_member.setString(4, member.getEmail());
            ps_member.setInt(5, member.getId());

            // update member data
            int rows = ps_member.executeUpdate();
            if (rows == 0) {
                throw new ObjectNotFoundException(String.format("Participant instance with id=%d not found", id));
            }
            ps_participant.close();
            ps_member.close();

            // commit transaction
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return member;
    }

    /**
     * Deletes all members
     */
    private void deleteAllMembers() throws FailedOperationException {
        int isTeam = 0;
        String sql = "DELETE FROM participant WHERE is_team=" + isTeam;
        deleteAllEntries(sql);
    }

    // endregion

    // region <TEAM>

    /**
     * Adding new team in db
     */
    private Team addTeam(Team team) throws FailedOperationException {
        Connection conn = null;

        String sql_participant = "INSERT INTO participant (is_team, avatar, participant_info) VALUES (?,?,?)";
        String sql_team = "INSERT INTO team (team_id, team_name) VALUES (?,?)";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // start transaction
            conn.setAutoCommit(false);

            // prepare base participant insert query
            PreparedStatement ps = conn.prepareStatement(sql_participant, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, team.isTeam());
            ps.setString(2, team.getAvatar());
            ps.setString(3, team.getParticipantInfo());

            // insert base participant info
            ps.executeUpdate();

            // acquire/apply assigned ID
            int teamId = acquireGeneratedKey(ps);
            team.setId(teamId);
            ps.close();

            // prepare team insert query
            ps = conn.prepareStatement(sql_team);
            ps.setInt(1, teamId);
            ps.setString(2, team.getTeamName());

            // insert team data
            ps.executeUpdate();
            ps.close();

            // commit transaction
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return team;
    }

    /**
     * Gets team by id
     */
    private Team getTeamById(int id) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Team team = null;

        String sql = "SELECT * FROM participant p INNER JOIN team t ON p.participant_id=t.team_id WHERE team_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                team = extractTeamFromResultSet(rs);
            } else {
                throw new ObjectNotFoundException(String.format("Team with id[%d] not found", id));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return team;
    }

    /**
     * Gets member list by tournament id
     */
    private List<Team> getTeamListByTournamentId(int tournamentId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Team team;
        List<Team> teamList = new ArrayList<>();
        String sql = "SELECT * FROM participant p WHERE p.tournament_id=?";

        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, tournamentId);

            // update member data
            rs = ps.executeQuery();

            while (rs.next()) {
                team = extractTeamFromResultSet(rs);
                teamList.add(team);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return teamList;
    }

    /**
     * Gets team list
     */
    private List<Team> getTeamList() throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Team team;
        List<Team> teamList = new ArrayList<>();

        String sql = "SELECT * FROM participant p INNER JOIN team t ON p.participant_id=t.team_id";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                team = extractTeamFromResultSet(rs);
                teamList.add(team);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return teamList;
    }

    /**
     * Updates specific data of team
     */
    private Team updateTeam(int id, Team team) throws ObjectNotFoundException, FailedOperationException {
        Connection conn = null;

        String sql_participant = "UPDATE participant SET avatar=?, participant_info=? WHERE participant_id=?";
        String sql_team = "UPDATE team SET team_name=? WHERE team_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // start transaction
            conn.setAutoCommit(false);

            PreparedStatement ps_participant = conn.prepareStatement(sql_participant);
            ps_participant.setString(1, team.getAvatar());
            ps_participant.setString(2, team.getParticipantInfo());
            ps_participant.setInt(3, team.getId());

            // update base participant info
            ps_participant.executeUpdate();

            PreparedStatement ps_team = conn.prepareStatement(sql_team);
            ps_team.setString(1, team.getTeamName());
            ps_team.setInt(2, team.getId());

            // update member data
            int rows = ps_team.executeUpdate();
            if (rows == 0) {
                throw new ObjectNotFoundException(String.format("Participant instance with id=%d not found", id));
            }
            ps_participant.close();
            ps_team.close();

            // commit transaction
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return team;
    }

    /**
     * Deletes all teams
     */
    private void deleteAllTeams() throws FailedOperationException {
        int isTeam = 1;
        String sql = "DELETE FROM participant WHERE is_team=" + isTeam;
        deleteAllEntries(sql);
    }

    // endregion

    /**
     * Extracting specific data of Member from ResultSet
     */
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
            member.setTournamentId(rs.getInt("tournament_id"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return member;
    }

    /**
     * Extracting specific data of Team from ResultSet
     */
    private static Team extractTeamFromResultSet(ResultSet rs) throws FailedOperationException {
        Team team = new Team();
        try {
            team.setId(rs.getInt("participant_id"));
            team.setAvatar(rs.getString("avatar"));
            team.setParticipantInfo(rs.getString("participant_info"));
            team.setTeamName(rs.getString("team_name"));
            team.setTournamentId(rs.getInt("tournament_id"));

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        }
        return team;
    }
}