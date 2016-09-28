package com.workfront.intern.cb.dao;

import java.sql.Connection;

public interface IGenericDao {

	public Connection getTransactionalConnection();
	public void commitTransaction(Connection transaction);
	public void rollbackTransaction(Connection transaction);

}
