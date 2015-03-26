package com.datacoper.locacaoequipamentos.persistence.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.datacoper.locacaoequipamentos.persistence.connections.ConnectionController;
import com.datacoper.locacaoequipamentos.persistence.connections.JPAUtil;
import com.datacoper.locacaoequipamentos.persistence.parameters.ParametersDAO;
import com.datacoper.locacaoequipamentos.persistence.parameters.ParametersLoader;
import com.datacoper.locacaoequipamentos.persistence.util.ConfiguracaoSistema;
import com.datacoper.locacaoequipamentos.persistence.util.ParametrosEnum;

/**
 *
 * @author Java
 */
public abstract class DAOFactory {
	private static Object connection;
	private static final String PERSISTENCE_TYPE = ParametersLoader.getPersistenceType();

	public static <T> T getInstance(Class<T> classDAO) throws Exception {
		try {
			connection = getConnection();
			return (T) getClassDaoForInstantiate(classDAO).getConstructor(connection.getClass()).newInstance(connection);
		} catch (Exception e) {
			throw new Exception("Erro ao instanciar DAO\n" + e.getMessage());
		}

	}

	public abstract <T> T createDAO(Class<T> daoClass);

	private static String getPersistenceType() {
		String persistenceType = ConfiguracaoSistema.getValorConfiguracao(ParametrosEnum.PERSISTENCETYPE);
		return persistenceType;
	}

	private static Class<?> getClassDaoForInstantiate(Class<?> classDAO) {
		ParametersDAO dao = ParametersLoader.getParamderByClass(classDAO);

		if (PERSISTENCE_TYPE.equalsIgnoreCase("jdbc")) {
			return dao.getClassJDBC();
		} else if (PERSISTENCE_TYPE.equalsIgnoreCase("jpa")) {
			return dao.getClassJPA();
		}
		return null;
	}

	private static Object getConnection() {
		if (PERSISTENCE_TYPE.equalsIgnoreCase("jdbc")) {
			return ConnectionController.getInstance().getConnection();
		} else if (PERSISTENCE_TYPE.equalsIgnoreCase("jpa")) {
			return JPAUtil.createEntityManager();
		}
		return null;
	}

}
