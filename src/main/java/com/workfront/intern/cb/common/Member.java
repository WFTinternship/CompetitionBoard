package com.workfront.intern.cb.common;

public class Member {
    private int memberId;
    private String name;
    private String surName;
    private String position;
    private String email;
    private int participantId;

    public Member() {
    }

    public Member(int memberId) {
        this.memberId = memberId;
    }

    public Member(String name, String surName, String position, String email) {

        this.name = name;
        this.surName = surName;
        this.position = position;
        this.email = email;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        sb.append("memberId: ").append(memberId).append("\n");
        sb.append("name: ").append(name).append("\n");
        sb.append("surname: ").append(surName).append("\n");
        sb.append("position: ").append(position).append("\n");
        sb.append("email: ").append(email).append("\n");
        sb.append("participantId: ").append(participantId).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

       return sb.toString();
    }
}