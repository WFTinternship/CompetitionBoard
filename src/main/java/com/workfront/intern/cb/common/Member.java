package com.workfront.intern.cb.common;

public class Member extends Participant {
    private String name;
    private String surName;
    private String position;
    private String email;

    public Member() {
        isTeam = false;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurName() {
        return surName;
    }

    public Member setSurName(String surName) {
        this.surName = surName;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public Member setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("id: ").append(id).append("\n");
        sb.append("name: ").append(name).append("\n");
        sb.append("surname: ").append(surName).append("\n");
        sb.append("position: ").append(position).append("\n");
        sb.append("email: ").append(email).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}