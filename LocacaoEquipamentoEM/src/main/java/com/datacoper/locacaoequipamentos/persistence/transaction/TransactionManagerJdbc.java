package com.datacoper.locacaoequipamentos.persistence.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import com.datacoper.locacaoequipamentos.persistence.dao.jdbc.ConnectionController;
import com.datacoper.locacaoequipamentos.persistence.exception.PersistenceException;

public class TransactionManagerJdbc implements ITransaction {

	private Connection connection;
	
	
	public TransactionManagerJdbc() {
		setConnection(ConnectionController.getInstance().getConnection());
	}
	Connection getConnection() {
		return connection;
	}

	void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void commit() throws PersistenceException  {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public void rollback() throws PersistenceException {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public void beginTransaction() throws PersistenceException {
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}
	@Override
	public void closeTransaction() throws PersistenceException {
		try {
			getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}
	
	

}
