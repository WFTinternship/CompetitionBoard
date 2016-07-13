package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Manager;

import java.util.List;

interface ManagerDao {

    Manager getManagerById(int id);

    Manager getManagerByLogin(String login);

    List<Manager> getManagerList();

    boolean addManager(Manager manager);

    boolean deleteManagerById(int id);

    boolean deleteAllManager();
}
