package com.workfront.intern.cb.common;

public class Manager {
    private int managerId;
    private String login;
    private String pass;

    public Manager(int managerId, String login, String pass) {
        this.managerId = managerId;
        this.login = login;
        this.pass = pass;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("managerId: ").append(managerId).append("\n");
        sb.append("login: ").append(login).append("\n");
        sb.append("pass: ").append(pass).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
