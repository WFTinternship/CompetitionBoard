package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.DBManager;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.ManagerDaoImpl;

import javax.sql.DataSource;
import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    DataSource dataSource;

    public ManagerServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private ManagerDao managerDao = new ManagerDaoImpl(dataSource);

    @Override
    public void addManager(Manager manager) {}

    @Override
    public Manager getManagerById(int id) {
        try {
            return managerDao.getManagerById(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Great apologies! We could not find a Manager with id=%s", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Manager getManagerByLogin(String login) {
        return null;
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

    public static void main(String[] args) {
        DataSource dataSource = DBManager.getDataSource();
        Manager managerNew = new ManagerServiceImpl(dataSource).getManagerById(10);
        System.out.println(managerNew);
    }
}
