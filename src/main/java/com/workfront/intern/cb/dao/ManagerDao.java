package com.workfront.intern.cb.dao;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.custom.exception.FailedOperationException;
import com.workfront.intern.cb.common.custom.exception.ObjectNotFoundException;

import java.util.List;

public interface ManagerDao {

    // CREATE
    Manager addManager(Manager manager) throws FailedOperationException;

    // READ
    Manager getManagerById(int id) throws ObjectNotFoundException, FailedOperationException;
    Manager getManagerByLogin(String login) throws ObjectNotFoundException, FailedOperationException;
    List<Manager> getManagerList() throws ObjectNotFoundException, FailedOperationException;

    // UPDATE
    void updateManager(int id, Manager manager) throws ObjectNotFoundException, FailedOperationException;

    // DELETE
    void deleteManagerById(int id) throws ObjectNotFoundException, FailedOperationException;
    void deleteAll() throws FailedOperationException;
}
