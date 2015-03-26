/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.business.servicesimpl;

import com.datacoper.locacaoequipamentos.common.exception.BusinessException;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.common.service.interfaces.ClienteService;
import com.datacoper.locacaoequipamentos.persistence.dao.DAOFactory;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.ClienteDAO;
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
		// } else if (pessoa.getTelefone().contains(" ")) {
		// throw new
		// RuntimeException("Por favor, preencha o campo Telefone de forma correta");
		// }

		if (camposNaoPreenchidos.length() > 0) {
			throw new BusinessException("Os seguintes campos são de preenchimento obrigatório:\n" + camposNaoPreenchidos);
		}
	}

	// @Override
	// public List<Pessoa> pesquisar() throws BusinessException {
	// List<Pessoa> clientes;
	// try {
	// clientes = clienteDAO.pesquisarTodos();
	// } catch (Exception e) {
	// throw new BusinessException(e);
	// }
	// return clientes;
	// }

	// @Override
	// public List<Pessoa> pesquisar(String campoPesquisar, String pesquisa)
	// throws BusinessException {
	// try {
	// return clienteDAO.pesquisar(campoPesquisar, pesquisa);
	// } catch (Exception e) {
	// throw new BusinessException(e);
	// }
	// }

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

	// @Override
	// public boolean excluir(Pessoa pessoa) {
	//
	// List<Locacao> locacoes;
	//
	// try {
	// locacoes = encontrarTodasLocacoes();
	// } catch (Exception ex) {
	// Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE,
	// null, ex);
	// throw new
	// RuntimeException("Problema encontrar locações de pessoa para excluir",
	// ex);
	// }
	//
	// boolean lock = false;
	// for (Locacao l : locacoes) {
	// System.out.println("ID Pessoa = " + l.getIdCliente());
	// if (l.getIdCliente() == pessoa.getIdCliente()) {
	// lock = true;
	// }
	// }
	// if (lock) {
	// throw new RuntimeException("Pessoa com Locação!");
	// } else {
	//
	// if (clienteDAO.excluiCliente(pessoa)) {
	// return true;
	// } else {
	// return false;
	// }
	//
	// }
	// }
	//
	// @Override
	// public List<Pessoa> encontrarTodosClientes() {
	// throw new UnsupportedOperationException("Not supported yet."); //To
	// change body of generated methods, choose Tools | Templates.
	// }
	//
	// @Override
	// public boolean updateCliente(Pessoa pessoa) {
	// if (pessoa.getCpf().contains(" ")) {
	// throw new RuntimeException("Por favor preencha CPF corretamenta");
	// } else if (pessoa.getDataNascimento() == null) {
	// throw new
	// RuntimeException("Por favor, preencha o campo Data de Nascimento de forma correta");
	// } else if (pessoa.getTelefone().contains(" ")) {
	// throw new
	// RuntimeException("Por favor, preencha o campo Telefone de forma correta");
	// }
	//
	// if (clienteDAO.updateCliente(pessoa)) {
	// return true;
	// } else {
	// return false;
	// }
	//
	// }
	//
	// @Override
	// public List<Locacao> encontrarTodasLocacoes() {
	//
	// try {
	// return getLocacaoDAO().buscaLocacoesAll();
	// } catch (Exception ex) {
	// Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE,
	// null, ex);
	// throw new RuntimeException("Problema para encontrar todas as locações!",
	// ex);
	// }
	//
	// }
	//
	// @Override
	// public List<Pessoa> buscaClienteAll(int ordem, int ascDesc) {
	// List<Pessoa> lista;
	//
	// lista = clienteDAO.buscaAll(ordem, ascDesc);
	//
	// return lista;
	// }
	//
	// @Override
	// public List<Pessoa> buscaClienteEsp(int ordem, int ascDesc, int id,
	// String cont) {
	//
	// List<Pessoa> lista;
	//
	// lista = clienteDAO.buscaEsp(ordem, ascDesc, id, cont);
	//
	// return lista;
	// }

}
