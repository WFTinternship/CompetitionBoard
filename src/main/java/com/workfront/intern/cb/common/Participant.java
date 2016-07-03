package com.workfront.intern.cb.common;

public class Participant {
    private int participantId;
    private boolean isTeam;
    private String avatar;
    private String participantInfo;

    public Participant(int participantId, boolean isTeam, String avatar, String participantInfo) {
        this.participantId = participantId;
        this.isTeam = isTeam;
        this.avatar = avatar;
        this.participantInfo = participantInfo;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public boolean isTeam() {
        return isTeam;
    }

    public void setIsTeam(boolean isTeam) {
        this.isTeam = isTeam;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getParticipantInfo() {
        return participantInfo;
    }

    public void setParticipantInfo(String participantInfo) {
        this.participantInfo = participantInfo;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("participantId: ").append(participantId).append("\n");
        sb.append("isTeam: ").append(isTeam).append("\n");
        sb.append("avatar: ").append(avatar).append("\n");
        sb.append("participantInfo: ").append(participantInfo).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
