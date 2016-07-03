package com.workfront.intern.cb.common;

public class Team {
    private int teamId;
    private String teamName;
    private int teamMemberId;
    private int participantId;

    public Team(int teamId) {
        this.teamId = teamId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(int teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("teamId: ").append(teamId).append("\n");
        sb.append("teamName: ").append(teamName).append("\n");
        sb.append("teamMemberId: ").append(teamMemberId).append("\n");
        sb.append("participantId: ").append(participantId).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
