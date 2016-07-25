package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface ManagerDao {

    // CREATE
    void addManager(Manager manager);

    // READ
    Manager getManagerById(int id) throws ObjectNotFoundException, FailedOperationException;
    Manager getManagerByLogin(String login) ObjectNotFoundException, FailedOperationException;

    List<Manager> getManagerList();

    // UPDATE
    boolean updateManager(int id, Manager manager);

    // DELETE
    boolean deleteManagerById(int id);
    boolean deleteAll();
}
