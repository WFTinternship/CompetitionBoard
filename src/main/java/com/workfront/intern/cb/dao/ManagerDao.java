package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Manager;

import java.util.List;

public interface ManagerDao {
    Manager getManagerById(int id);
    Manager getManagerByLogin(String login);
    List<Manager> getManagerList();
    boolean addManager(String login, String password);
    boolean deleteManagerById(int id);
    boolean deleteManagerByLogin(String login, String password);

}
