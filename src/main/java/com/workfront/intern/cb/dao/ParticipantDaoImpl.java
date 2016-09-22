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
     * Gets specific participant - member or team, by id:
     */
    @Override
    public List<? extends Participant> getParticipantListByGroupWithRelativeObjects(Class<? extends Participant> cls, int groupId)
            throws FailedOperationException, ObjectNotFoundException {
        if (cls.equals(Member.class)) {
            return getMembersByGroupWithRelativeObjects(groupId);
        } else if (cls.equals(Team.class)) {
            return getTeamsByGroupWithRelativeObjects(groupId);
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Gets specific participant list - member or team, by name:
     */
    @Override
    public List<? extends Participant> getParticipantListByName(Class<? extends Participant> cls, String participantName)
            throws FailedOperationException, ObjectNotFoundException {
        if (cls.equals(Member.class)) {
            return getMemberListByMemberName(participantName);
        } else if (cls.equals(Team.class)) {
            return getTeamListByTeamName(participantName);
        } else {
            throw new RuntimeException("Unknown participant type");
        }
    }

    /**
     * Gets specific participant list by tournament id - memberList or teamList
     */
    @Override
    public List<? extends Participant> getParticipantListByTournamentId(Class<? extends Participant> cls, int tournamentId)
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
     * Gets specific participant list by group id - memberList or teamList
     */
    @Override
    public List<? extends Participant> getParticipantListByGroupId(Class<? extends Participant> cls, int groupId)
            throws FailedOperationException {
        if (cls.equals(Member.class)) {
            return getMembersByGroupId(groupId);
        } else if (cls.equals(Team.class)) {
            return getTeamsByGroupId(groupId);
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

        String sql_participant = "INSERT INTO participant(is_team, avatar, participant_info, tournament_id) VALUES (?,?,?,?)";
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
            ps.setInt(4, member.getTournamentId());

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
            member = mapMember(rs);
            if (member == null) {
                throw new ObjectNotFoundException(String.format("Team with id[%d] not found", id));
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
     * Gets member list by By groupId id
     */
    private List<Member> getMembersByGroupWithRelativeObjects(int groupId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Member> memberList = new ArrayList<>();

        String sql = "SELECT * FROM participant p " +
                                    "INNER JOIN member m ON p.participant_id=m.member_id " +
                                    "INNER JOIN group_participant gp ON p.participant_id=gp.participant_id " +
                                    "INNER JOIN `group` g ON gp.group_id=g.group_id" +
                                    " WHERE g.group_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupId);

            // update member data
            rs = ps.executeQuery();

            memberList = mapMemberList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return memberList;
    }

    /**
     * Gets member list by member name
     */
    private List<Member> getMemberListByMemberName(String memberName) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Member> memberList = new ArrayList<>();

        String sql = "SELECT * FROM participant p INNER JOIN member m ON p.participant_id=m.member_id" +
                " INNER JOIN `group` g ON g.tournament_id=p.tournament_id WHERE m.name=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setString(1, memberName);

            // update member data
            rs = ps.executeQuery();

            memberList = mapMemberList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return memberList;
    }

    /**
     * Gets member list by tournament id
     */
    private List<Member> getMemberListByTournamentId(int tournamentId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Member> memberList = new ArrayList<>();

        String sql = "SELECT * FROM participant p INNER JOIN member m ON p.participant_id=m.member_id WHERE p.tournament_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, tournamentId);

            // update member data
            rs = ps.executeQuery();

            rs = ps.executeQuery();
            memberList = mapMemberList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return memberList;
    }

    /**
     * Gets member list by group id
     */
    private List<Member> getMembersByGroupId(int groupId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Member> memberList = new ArrayList<>();

        String sql = "SELECT * FROM group_participant gp WHERE gp.group_id=?;";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupId);

            // Execute statement
            rs = ps.executeQuery();
            memberList = mapMemberList(rs);
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
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM participant p INNER JOIN member m ON p.participant_id=m.member_id";

        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);

            // update member data
            rs = ps.executeQuery();

            rs = ps.executeQuery();
            memberList = mapMemberList(rs);
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

        String sql_participant = "UPDATE participant SET avatar=?, participant_info=?, tournament_id=? WHERE participant_id=?";
        String sql_member = "UPDATE member SET name=?, surname=?, position=?, email=? WHERE member_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // start transaction
            conn.setAutoCommit(false);

            PreparedStatement ps_participant = conn.prepareStatement(sql_participant);
            ps_participant.setString(1, member.getAvatar());
            ps_participant.setString(2, member.getParticipantInfo());
            ps_participant.setInt(3, member.getTournamentId());
            ps_participant.setInt(4, id);

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

        String sql_participant = "INSERT INTO participant (is_team, avatar, participant_info, tournament_id) VALUES (?,?,?,?)";
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
            ps.setInt(4, team.getTournamentId());

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
            team = mapTeam(rs);
            if (team == null) {
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
     * Gets team list by by groupId id, full
     */
    private List<Team> getTeamsByGroupWithRelativeObjects(int groupId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Team> teamList = new ArrayList<>();

        String sql = "SELECT * FROM participant p " +
                                    "INNER JOIN team t ON p.participant_id=t.team_id" +
                                    "INNER JOIN group_participant gp ON p.participant_id=gp.participant_id " +
                                    "INNER JOIN `group` g ON gp.group_id=g.group_id" +
                                    " WHERE g.group_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupId);

            // update member data
            rs = ps.executeQuery();
            teamList = mapTeamList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }

        return teamList;
    }


    /**
     * Gets team list by tournament id
     */
    private List<Team> getTeamListByTournamentId(int tournamentId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Team> teamList = new ArrayList<>();
        String sql = "SELECT * FROM participant p INNER JOIN team t ON p.participant_id=t.team_id WHERE p.tournament_id=?";

        try {
            // Acquire connection
            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, tournamentId);

            // update member data
            rs = ps.executeQuery();
            teamList = mapTeamList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return teamList;
    }

    /**
     * Gets team list by group id
     */
    private List<Member> getTeamsByGroupId(int groupId) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Member> memberList = new ArrayList<>();

        String sql = "SELECT * FROM group_participant gp WHERE gp.group_id=?;";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setInt(1, groupId);

            // Execute statement
            rs = ps.executeQuery();
            memberList = mapMemberList(rs);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new FailedOperationException(e.getMessage(), e);
        } finally {
            closeResources(conn, ps, rs);
        }
        return memberList;
    }

    /**
     * Gets team list by team name
     */
    private List<Team> getTeamListByTeamName(String teamName) throws FailedOperationException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Team> teamList = new ArrayList<>();
        String sql = "SELECT * FROM participant p " +
                "INNER JOIN team t ON p.participant_id=t.team_id WHERE t.team_name=?";

        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);
            ps.setString(1, teamName);

            // update member data
            rs = ps.executeQuery();
            teamList = mapTeamList(rs);
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
        List<Team> teamList = new ArrayList<>();

        String sql = "SELECT * FROM participant p INNER JOIN team t ON p.participant_id=t.team_id";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // Initialize statement
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            teamList = mapTeamList(rs);
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

        String sql_participant = "UPDATE participant SET avatar=?, participant_info=?, tournament_id=? WHERE participant_id=?";
        String sql_team = "UPDATE team SET team_name=? WHERE team_id=?";
        try {
            // Acquire connection
            conn = dataSource.getConnection();

            // start transaction
            conn.setAutoCommit(false);

            PreparedStatement ps_participant = conn.prepareStatement(sql_participant);
            ps_participant.setString(1, team.getAvatar());
            ps_participant.setString(2, team.getParticipantInfo());
            ps_participant.setInt(3, team.getTournamentId());
            ps_participant.setInt(4, team.getId());

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


    @SuppressWarnings("unchecked")
    @Override
    protected Participant mapObject(ResultSet rs) {
        throw new RuntimeException("Unsupported for Generic participant type. " +
                "Please defined the type of participant.");
    }

    @Override
    protected <T> List<T> mapList(ResultSet rs) {
        throw new RuntimeException("Unsupported for Generic participant type. " +
                "Please defined the type of participant.");
    }

    private Member mapMember(ResultSet rs) {
        List<Member> entities = mapMemberList(rs);
        return entities.size() == 0 ? null : entities.get(0);
    }

    /**
     * Extracting specific data of Member from ResultSet
     */
    @SuppressWarnings("unchecked")
    private List<Member> mapMemberList(ResultSet rs) {
        List<Member> resultList = new ArrayList<>();
        try {
            while (rs.next()) {
                Member member = new Member();

                member.setId(rs.getInt("participant_id"));
                member.setAvatar(rs.getString("avatar"));
                member.setParticipantInfo(rs.getString("participant_info"));
                member.setTournamentId(rs.getInt("tournament_id"));
                member.setName(rs.getString("name"));
                member.setSurName(rs.getString("surname"));
                member.setPosition(rs.getString("position"));
                member.setEmail(rs.getString("email"));

                resultList.add(member);
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
        return resultList;
    }

    private Team mapTeam(ResultSet rs) {
        List<Team> entities = mapTeamList(rs);
        return entities.size() == 0 ? null : entities.get(0);
    }

    /**
     * Extracting specific data of Team from ResultSet
     */
    @SuppressWarnings("unchecked")
    private List<Team> mapTeamList(ResultSet rs) {
        List<Team> resultList = new ArrayList<>();
        try {
            while (rs.next()) {
                Team team = new Team();

                team.setId(rs.getInt("participant_id"));
                team.setAvatar(rs.getString("avatar"));
                team.setParticipantInfo(rs.getString("participant_info"));
                team.setTeamName(rs.getString("team_name"));
                team.setTournamentId(rs.getInt("tournament_id"));

                resultList.add(team);
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
        return resultList;
    }
}