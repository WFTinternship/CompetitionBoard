package com.workfront.intern.cb.common;

public class Team extends Participant {
    private String teamName;

    public Team() {
        isTeam = true;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("participant_id: ").append(id).append("\n");
        sb.append("avatar: ").append(avatar).append("\n");
        sb.append("participant_info: ").append(participantInfo).append("\n");
        sb.append("teamName: ").append(teamName).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
