/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.common.service;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.exception.BusinessException;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;

/**
 *
 * @author Java
 */

public interface ClienteService extends Service {

	void gravar(Pessoa cliente) throws BusinessException;

	// boolean updateCliente(Cliente cliente);

	void excluir(Pessoa cliente) throws BusinessException;

	//
	List<Pessoa> encontrarTodosClientes() throws BusinessException;

	// List<Cliente> buscaClienteAll(int ordem, int ascDesc);
	//
	// List<Cliente> buscaClienteEsp(int ordem, int ascDesc, int id, String
	// cont);
	//
	// /////VER SE PRECISO CRIAR OUTRA INTERFACE PARA LOCACAO
	// List<Locacao> encontrarTodasLocacoes();
}
