package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import com.datacoper.locacaoequipamentos.common.model.Pais;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.PaisDAO;

public class PaisDAOJdbc extends AbstractDAOJdbc<Pais, Integer> implements PaisDAO {

	public PaisDAOJdbc(Class<Pais> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}


}
