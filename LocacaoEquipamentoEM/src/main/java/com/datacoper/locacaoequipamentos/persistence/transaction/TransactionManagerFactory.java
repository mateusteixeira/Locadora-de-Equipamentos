package com.datacoper.locacaoequipamentos.persistence.transaction;

import com.datacoper.locacaoequipamentos.persistence.util.ConfiguracaoSistema;
import com.datacoper.locacaoequipamentos.persistence.util.ParametrosEnum;

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
