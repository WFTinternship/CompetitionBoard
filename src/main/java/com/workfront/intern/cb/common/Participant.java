package com.workfront.intern.cb.common;

public abstract class Participant {
    protected int id;
    protected boolean isTeam;
    protected String avatar;
    protected String participantInfo;
    protected int tournamentId;

    public int getId() {
        return id;
    }

    public Participant setId(int id) {
        this.id = id;
        return this;
    }

    public boolean isTeam() {
        return isTeam;
    }

    public String getAvatar() {
        return avatar;
    }

    public Participant setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getParticipantInfo() {
        return participantInfo;
    }

    public Participant setParticipantInfo(String participantInfo) {
        this.participantInfo = participantInfo;
        return this;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    /**
     * method return participant type: MEMBER or TEAM
     */
    public static String getParticipantType(boolean isTeam) {
        if (isTeam) {
            return String.valueOf("TEAM");
        }
        return String.valueOf("MEMBER");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("id: ").append(id).append("\n");
        sb.append("isTeam(participant_type): ").append(getParticipantType(isTeam)).append("\n");
        sb.append("avatar: ").append(avatar).append("\n");
        sb.append("participantInfo: ").append(participantInfo).append("\n");
        sb.append("tournamentId: ").append(tournamentId).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}