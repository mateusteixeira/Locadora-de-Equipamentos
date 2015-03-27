/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.business.servicesimpl;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.exception.BusinessException;
import com.datacoper.locacaoequipamentos.common.model.Cidade;
import com.datacoper.locacaoequipamentos.common.model.Estado;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.common.service.interfaces.ClienteService;
import com.datacoper.locacaoequipamentos.persistence.dao.DAOFactory;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.CidadeDAO;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.ClienteDAO;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.EstadoDAO;
import com.datacoper.locacaoequipamentos.persistence.exception.PersistenceException;
import com.datacoper.locacaoequipamentos.persistence.transaction.ITransaction;
import com.datacoper.locacaoequipamentos.persistence.transaction.TransactionManagerFactory;

/**
 *
 * @author Java
 */
public class ClienteServiceImpl implements ClienteService {

	private ClienteDAO clienteDAO;

	public ClienteServiceImpl() {
		try {
			clienteDAO = DAOFactory.getInstance(ClienteDAO.class, Pessoa.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void gravar(Pessoa pessoa) throws BusinessException {
		validarDadosObrigatorios(pessoa);

		ITransaction transaction = TransactionManagerFactory.getTransaction();

		try {
			transaction.beginTransaction();
			clienteDAO.save(pessoa);
			transaction.commit();

		} catch (Exception e) {

			try {
				transaction.rollback();
			} catch (PersistenceException e1) {
				throw new BusinessException(e1);
			}

			throw new BusinessException(e);

		} finally {
			try {
				transaction.closeTransaction();
			} catch (PersistenceException e) {
				e.printStackTrace();
			}
		}

	}

	private void atualizarIDCliente(Pessoa pessoa) throws BusinessException {
		try {
			pessoa.setIdPessoa(clienteDAO.nextId());
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}

	private void validarDadosObrigatorios(Pessoa pessoa) throws BusinessException {

		StringBuilder camposNaoPreenchidos = new StringBuilder();
		if (pessoa.getNrCpf().matches("(\\d\\d\\.){3}-\\d{2}")) {
			camposNaoPreenchidos.append("CPF inválido");
		}
		if (pessoa.getDtNascimento() == null) {
			camposNaoPreenchidos.append("Data Nascimento não preenchido");
		}

		if (camposNaoPreenchidos.length() > 0) {
			throw new BusinessException("Os seguintes campos são de preenchimento obrigatório:\n" + camposNaoPreenchidos);
		}
	}


	@Override
	public void excluir(Pessoa pessoa) throws BusinessException {

		ITransaction transaction = TransactionManagerFactory.getTransaction();

		try {

			transaction.beginTransaction();
			clienteDAO.remove(pessoa);
			transaction.commit();

		} catch (Exception e) {

			try {
				transaction.rollback();
			} catch (PersistenceException e1) {
				throw new BusinessException();
			}

			throw new BusinessException(e);

		} finally {
			try {
				transaction.closeTransaction();
			} catch (PersistenceException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Estado> getEstados() throws BusinessException {
		try {
			EstadoDAO estadoDAO = (EstadoDAO) DAOFactory.getInstance(Estado.class);
			return estadoDAO.getAll();
		} catch (Exception e) {
			throw new BusinessException("Erro ao obter Estados", e);
		}
	}

	@Override
	public List<Cidade> getCidades(Estado estado) throws BusinessException {
		try {
			CidadeDAO cidadeDAO = (CidadeDAO) DAOFactory.getInstance(Cidade.class);
			return cidadeDAO.findCidadesByEstado(estado);
		} catch (Exception e) {
			throw new BusinessException("Erro ao obter Cidade", e);
		}
	}

}
