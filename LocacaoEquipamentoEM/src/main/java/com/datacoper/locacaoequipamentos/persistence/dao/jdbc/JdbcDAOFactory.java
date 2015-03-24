/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.ClienteDAO;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.DAOFactory;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.LocacaoDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Java
 */
public class JdbcDAOFactory extends DAOFactory {

	private static Map<Class, Class> mapa = new HashMap<>();
	private Connection connection;
	
	static {
		mapa.put(ClienteDAO.class, ClienteDAOJdbc.class);
		mapa.put(LocacaoDAO.class, LocacaoDAOJdbc.class);
	}

	@Override
	public <T> T createDAO(Class<T> interfaceDaoClass) {
		Class<T> daoClass = mapa.get(interfaceDaoClass);
		connection = ConnectionController.getInstance()
				.getConnection();
		try {
			
			return daoClass.getConstructor(Connection.class).newInstance(
					connection);
		} catch (Exception ex) {
			System.out.println("Erro = " + ex.getMessage());
			throw new RuntimeException(ex);
		}

	}

	public Connection getConnection() {
		return connection;
	}

	

}
