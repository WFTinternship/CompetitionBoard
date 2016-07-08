package com.workfront.intern.cb.common;

public class Manager {
    private int id;
    private String login;
    private String password;

    public Manager() {
    }

    public Manager(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public static Manager getManagerById(int id){
        Manager manager = new Manager();
        manager.setId(id);
        return manager;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("id: ").append(id).append("\n");
        sb.append("login: ").append(login).append("\n");
        sb.append("password: ").append(password).append("\n");
        sb.append("*******************************************************************************************");
        sb.append("\n");

        return sb.toString();
    }
}
