package com.datacoper.locacaoequipamentos.persistence.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import com.datacoper.locacaoequipamentos.persistence.dao.jdbc.JdbcDAOFactory;
import com.datacoper.locacaoequipamentos.util.ConfiguracaoSistema;
import com.datacoper.locacaoequipamentos.util.ParametrosEnum;

public abstract class TransactionManagerFactory {


	protected TransactionManagerFactory() {
	}

	public static ITransaction getTransaction( ) {
		String persistenceType = getPersistenceType();
		if (persistenceType.equals("JDBC")) {
			TransactionManagerJdbc transactionManagerJdbc = new TransactionManagerJdbc();
			return transactionManagerJdbc;
		} else if (persistenceType.equals("JPA")) {
		}
		return null;
	}

	private static String getPersistenceType() {
		String persistenceType = ConfiguracaoSistema
				.getValorConfiguracao(ParametrosEnum.PERSISTENCETYPE);
		return persistenceType;
	}



}
