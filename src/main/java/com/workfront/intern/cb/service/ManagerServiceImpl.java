package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.dao.ManagerDao;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    ManagerDao managerDao;


    @Override
    public boolean addManager(Manager manager) {
        return false;
    }

    @Override
    public Manager getManagerById(int id) {
        return managerDao.getManagerById(id);
    }

    @Override
    public Manager getManagerByLogin(String login){
        return getManagerByLogin(login);
    }

    @Override
    public List<Manager> getManagerList() {
        return getManagerList();
    }

    @Override
    public boolean updateManager(int id, Manager manager) {
        return false;
    }

    @Override
    public boolean deleteManagerById(int id) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
