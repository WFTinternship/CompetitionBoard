package com.workfront.intern.cb.dao;

import java.sql.Connection;

public interface IGenericDao {
	Connection getTransactionalConnection();
	void commitTransaction(Connection transaction);
	void rollbackTransaction(Connection transaction);
}