package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Manager;

import java.util.List;

public interface ManagerDao {

    // CREATE
    boolean addManager(Manager manager);

    // READ
    Manager getManagerById(int id);
    Manager getManagerByLogin(String login);
    List<Manager> getManagerList();

    // UPDATE
    boolean updateManager(int id, Manager manager);

    // DELETE
    boolean deleteManagerById(int id);

    boolean deleteAll();
}
