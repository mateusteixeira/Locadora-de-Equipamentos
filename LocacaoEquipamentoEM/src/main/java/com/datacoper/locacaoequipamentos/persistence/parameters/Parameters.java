package com.datacoper.locacaoequipamentos.persistence.parameters;

import java.util.List;

public class Parameters {

	private List<ParametersDAO> daos;
	private String persistenceType;

	public List<ParametersDAO> getDaos() {
		return daos;
	}

	public String getPersistenceType() {
		return persistenceType;
	}

}
