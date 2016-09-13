package com.workfront.intern.cb.common;

public class Group {
    private int groupId;
    private String groupName;
    private int participantsCount;
    private int round;
    private int nextRoundParticipants;
    private int tournamentId;

    public Group() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public int getNextRoundParticipants() {
        return nextRoundParticipants;
    }

    public void setNextRoundParticipants(int nextRoundParticipants) {
        this.nextRoundParticipants = nextRoundParticipants;
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
        sb.append("groupName: ").append(groupName).append("\n");
        sb.append("participantsId: ").append(participantsCount).append("\n");
        sb.append("tournamentId: ").append(getTournamentId()).append("\n");
        sb.append("round: ").append(round).append("\n");
        sb.append("nextRoundParticipants: ").append(nextRoundParticipants).append("\n");
        sb.append("tournamentId: ").append(getTournamentId()).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}