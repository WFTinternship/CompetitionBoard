package com.workfront.intern.cb.common;

public class Group {
    private int groupId;
    private int participantsCount;
    private int round;
    private int nextRoundParticipnats;
    private int tournamentId;

    public Group() {
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

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("groupId: ").append(groupId).append("\n");
        sb.append("participantsId: ").append(participantsCount).append("\n");
        sb.append("tournamentId: ").append(getTournamentId()).append("\n");
        sb.append("round: ").append(round).append("\n");
        sb.append("nextRoundParticipants: ").append(nextRoundParticipnats).append("\n");
        sb.append("tournamentId: ").append(getTournamentId()).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
