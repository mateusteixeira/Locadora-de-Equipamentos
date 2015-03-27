package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import com.datacoper.locacaoequipamentos.common.model.Estado;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.EstadoDAO;

public class EstadoDAOJdbc extends AbstractDAOJdbc<Estado, Integer> implements EstadoDAO {

	public EstadoDAOJdbc(Class<Estado> entityClass) {
		super(entityClass);
	}

}
