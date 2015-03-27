package com.datacoper.locacaoequipamentos.persistence.dao.interfaces;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.model.Cidade;
import com.datacoper.locacaoequipamentos.common.model.Estado;
import com.datacoper.locacaoequipamentos.persistence.dao.DAO;
import com.datacoper.locacaoequipamentos.persistence.exception.PersistenceException;

public interface CidadeDAO extends DAO<Cidade, Integer> {

	public List<Cidade> findCidadesByEstado(Estado estado) throws PersistenceException;

}
