package com.workfront.intern.cb.common;

public class Team extends Participant {
    private String teamName;
    private int participantId;

    private int teamMemberId;

    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public Team setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public int getTeamMemberId() {
        return teamMemberId;
    }

    public Team setTeamMemberId(int teamMemberId) {
        this.teamMemberId = teamMemberId;
        return this;
    }

    public int getParticipantId() {
        return participantId;
    }

    public Team setParticipantId(int participantId) {
        this.participantId = participantId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("teamId: ").append(id).append("\n");
        sb.append("teamName: ").append(teamName).append("\n");
        sb.append("teamMemberId: ").append(teamMemberId).append("\n");
        sb.append("participantId: ").append(participantId).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
