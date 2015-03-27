package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import java.sql.Connection;
import java.util.List;

import com.datacoper.locacaoequipamentos.common.model.Cidade;
import com.datacoper.locacaoequipamentos.common.model.Estado;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.CidadeDAO;
import com.datacoper.locacaoequipamentos.persistence.exception.PersistenceException;

public class CidadeDAOJdbc extends AbstractDAOJdbc<Cidade, Integer> implements CidadeDAO {


	public CidadeDAOJdbc(Class<Cidade> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}



	@Override
	public List<Cidade> findCidadesByEstado(Estado estado) throws PersistenceException {
		String sql = "SELECT * from " + getTableName(Cidade.class) + " WHERE cd_estado = " + estado.getIdEstado();
		return getResult(sql, Cidade.class);
	}

}
