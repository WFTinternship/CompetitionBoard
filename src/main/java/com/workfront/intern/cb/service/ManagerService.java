package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Manager;

import java.util.List;

public interface ManagerService {

    // CREATE
    Manager addManager(Manager manager);

    // READ
    Manager getManagerById(int id);
    Manager getManagerByLogin(String login);
    List<Manager> getManagerList();

    // UPDATE
    void updateManager(int id, Manager manager);

    // DELETE
    void deleteManagerById(int id);
    void deleteAll();
}