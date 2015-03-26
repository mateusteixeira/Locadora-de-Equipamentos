package com.datacoper.locacaoequipamentos.persistence.parameters;

import java.util.ArrayList;
import java.util.List;

import com.datacoper.locacaoequipamentos.persistence.dao.jdbc.ClienteDAOJdbc;
import com.thoughtworks.xstream.XStream;

public abstract class ParametersLoader {

	private static Parameters parameters;

	private static void init() {

		if (parameters == null) {
			XStream xStream = new XStream();
			xStream.alias("lista", ArrayList.class);
			xStream.alias("parametersDAO", ParametersDAO.class);
			xStream.alias("parameters", Parameters.class);
			parameters = (Parameters) xStream.fromXML(ParametersLoader.class.getResourceAsStream("/META-INF/parameters.xml"));
		}

	}

	public static List<ParametersDAO> getListaDAO() {
		init();
		return parameters.getDaos();
	}

	public static String getPersistenceType() {
		init();
		return parameters.getPersistenceType();
	}
	
	public static void main(String[] args) {
		init();
	}

	public static ParametersDAO getParamderByClass(Class<?> classe) {
		for (ParametersDAO p : getListaDAO()) {
			if (p.getClassDao() == classe || p.getClasse() == classe) {
				return p;
			}
		}
		return null;
	}
}
