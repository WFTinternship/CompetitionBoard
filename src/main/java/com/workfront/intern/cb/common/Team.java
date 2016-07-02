package com.workfront.intern.cb.common;

public class Team {
    private int teamId;
    private String teamName;
    private int teamMemberId;
    private int paricipantId;

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

    public int getParicipantId() {
        return paricipantId;
    }

    public void setParicipantId(int paricipantId) {
        this.paricipantId = paricipantId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamMemberId=" + teamMemberId +
                ", paricipantId=" + paricipantId +
                '}';
    }
}
