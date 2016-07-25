package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Manager;

import java.util.List;

public interface ManagerService {
    boolean addManager(Manager manager);

    Manager getManagerById(int id);

    Manager getManagerByLogin(String login);

    List<Manager> getManagerList();

    boolean updateManager(int id, Manager manager);

    boolean deleteManagerById(int id);

    boolean deleteAll();
}
