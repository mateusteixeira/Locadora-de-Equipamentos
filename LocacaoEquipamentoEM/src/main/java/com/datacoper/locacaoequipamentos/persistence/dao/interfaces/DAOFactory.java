/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.dao.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.List;

import javax.persistence.PersistenceException;

import com.datacoper.locacaoequipamentos.persistence.dao.jdbc.ConnectionController;
import com.datacoper.locacaoequipamentos.persistence.parameters.ParametersDAO;
import com.datacoper.locacaoequipamentos.persistence.parameters.ParametersLoader;
import com.datacoper.locacaoequipamentos.util.ConfiguracaoSistema;
import com.datacoper.locacaoequipamentos.util.ParametrosEnum;

/**
 *
 * @author Java
 */
public abstract class DAOFactory {

	private static DAOFactory daoFactory;
	private static Connection connection;

	public static <T> T getInstance(Class<T> classDAO) throws Exception {
		String persistenceType = ParametersLoader.getPersistenceType();
		Class<?> dao = null;
		List<ParametersDAO> daos = ParametersLoader.getListaDAO();
		try {
			for (ParametersDAO p : daos) {
				if (p.getClassDao() == classDAO) {
					if (persistenceType.equalsIgnoreCase("jdbc")) {
						dao = p.getClassJDBC();
						return instantiate(classDAO);
					} else if (persistenceType.equalsIgnoreCase("jpa")) {
						dao = p.getClassJPA();
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("Erro ao instanciar DAO\n" + e.getMessage());
		}
		return null;

	}
	
	public static <T>T instantiate (Class<T> classe) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return (T) classe.getConstructor(Connection.class).newInstance(getConnection());
	}

	public abstract <T> T createDAO(Class<T> daoClass);

	private static String getPersistenceType() {
		String persistenceType = ConfiguracaoSistema.getValorConfiguracao(ParametrosEnum.PERSISTENCETYPE);
		return persistenceType;
	}

	private static Connection getConnection() {
		if (connection == null) {
			connection = ConnectionController.getInstance().getConnection();
		}
		return connection;
	}

}
