package com.workfront.intern.cb.services;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.dao.ManagerDao;

import java.util.List;

public class ManagerServ {

    ManagerDao managerDao;

    Manager getManager(int id) {
        return managerDao.getManagerById(id);
    }

    Manager getManagerByLogin(String login){
        return managerDao.getManagerByLogin(login);
    }

    List<Manager> getManagerList(){
        return managerDao.getManagerList();
    }
}
