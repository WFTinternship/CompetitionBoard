package com.workfront.intern.cb.common;

public class Group {
    private int groupId;
    private int participantsCount;
    private int round;
    private int nextRoundParticipnats;
    private Tournament tournament;

    public Group() {
    }

    public Group(int groupId, int participantsCount, Tournament tournament, int round, int nextRoundParticipnats) {
        this.groupId = groupId;
        this.participantsCount = participantsCount;
        this.tournament = tournament;
        this.round = round;
        this.nextRoundParticipnats = nextRoundParticipnats;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public void setParticipantsCount(int participantsCount) {
        this.participantsCount = participantsCount;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getNextRoundParticipnats() {
        return nextRoundParticipnats;
    }

    public void setNextRoundParticipnats(int nextRoundParticipnats) {
        this.nextRoundParticipnats = nextRoundParticipnats;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("groupId: ").append(groupId).append("\n");
        sb.append("participantsId: ").append(participantsCount).append("\n");
        sb.append("tournamentId: ").append(tournament.getTournamentId()).append("\n");
        sb.append("round: ").append(round).append("\n");
        sb.append("nextRoundParticipants: ").append(nextRoundParticipnats).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
