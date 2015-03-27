/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import javax.persistence.PersistenceException;

import com.datacoper.locacaoequipamentos.common.model.Endereco;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.common.model.Pessoa_;
import com.datacoper.locacaoequipamentos.persistence.dao.DAOFactory;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.ClienteDAO;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.EnderecoDAO;

/**
 *
 * @author Java
 */
public class ClienteDAOJdbc extends AbstractDAOJdbc<Pessoa, Integer> implements ClienteDAO {

	public ClienteDAOJdbc(Class<Pessoa> entityClass) {
		super(entityClass);
	}

	public Pessoa insert(Pessoa pessoa) {
		super.insert(pessoa);
		try {
			EnderecoDAO enderecoDao = (EnderecoDAO) DAOFactory.getInstance(EnderecoDAO.class);
			enderecoDao.save(pessoa.getEnderecos().get(0));
		} catch (Exception e) {
			throw new PersistenceException(e);
		}

		return null;
	}

	public Pessoa update(Pessoa pessoa) {

		try {
			super.update(pessoa);
			EnderecoDAO enderecoDao = (EnderecoDAO) DAOFactory.getInstance(EnderecoDAO.class);
			enderecoDao.save(pessoa.getEnderecos().get(0));
		} catch (Exception e) {
			throw new PersistenceException(e);
		}

		return null;
	}
	
	
	@Override
	public Pessoa findById(Integer id) throws Exception {
		String sql = "SELECT * FROM " + getTableName(entityClass) + getWhereId(Pessoa.class, id);
		
		Pessoa p = getSingleResult(sql, entityClass);
		p.setEnderecos(((EnderecoDAO)DAOFactory.getInstance(EnderecoDAO.class)).getEnderecosByPessoa(p));
		return p;
	}
}