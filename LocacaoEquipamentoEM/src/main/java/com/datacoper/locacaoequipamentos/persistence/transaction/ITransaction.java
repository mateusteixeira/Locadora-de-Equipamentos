package com.datacoper.locacaoequipamentos.persistence.transaction;

import com.datacoper.locacaoequipamentos.persistence.exception.PersistenceException;

public interface ITransaction {
	
	void beginTransaction() throws PersistenceException;
	
	void commit() throws PersistenceException;
	
	void rollback() throws PersistenceException;
	
	void closeTransaction() throws PersistenceException;
	
	

}
