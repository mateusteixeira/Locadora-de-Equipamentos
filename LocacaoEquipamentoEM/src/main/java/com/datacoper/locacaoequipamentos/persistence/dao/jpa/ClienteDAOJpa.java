package com.datacoper.locacaoequipamentos.persistence.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.ClienteDAO;

public class ClienteDAOJpa extends AbstractDAOJPA<Pessoa, Integer> implements ClienteDAO {

	public ClienteDAOJpa(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Pessoa> search(String filtro, String valorFiltro) throws Exception {
		
		return null;
	}

	@Override
	public Integer nextId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
