package com.workfront.intern.cb.service;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;
import com.workfront.intern.cb.dao.DBManager;
import com.workfront.intern.cb.dao.ManagerDao;
import com.workfront.intern.cb.dao.ManagerDaoImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    private static final Logger LOG = Logger.getLogger(ManagerServiceImpl.class);

    private ManagerDao managerDao = new ManagerDaoImpl(DBManager.getDataSource());

    /**
     * Adds new manager in db
     */
    @Override
    public void addManager(Manager manager) {
        try {
            managerDao.addManager(manager);
        } catch (FailedOperationException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns manager by id
     */
    @Override
    public Manager getManagerById(int id) {
        try {
            return managerDao.getManagerById(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Manager instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns manager by login
     */
    @Override
    public Manager getManagerByLogin(String login) {
        try {
            return managerDao.getManagerByLogin(login);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Manager instance with login=%s not found", login));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Returns all managers in db
     */
    @Override
    public List<Manager> getManagerList() {
        try {
            return managerDao.getManagerList();
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException("Could not find a Manager list");
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Updates existing manager in db
     */
    @Override
    public void updateManager(int id, Manager manager) {
        try {
            managerDao.updateManager(id, manager);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Manager instance with id=%s not updated", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Deletes manager by id
     */
    @Override
    public void deleteManagerById(int id) {
        try {
            managerDao.deleteManagerById(id);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(String.format("Manager instance with id=%s not found", id));
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Removes all managers
     */
    @Override
    public void deleteAll() {
        try {
            managerDao.deleteAll();
        } catch (FailedOperationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
