/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.common.service.interfaces;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.exception.BusinessException;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;

/**
 *
 * @author Java
 */

public interface ClienteService {

	void gravar(Pessoa cliente) throws BusinessException;

	void excluir(Pessoa cliente) throws BusinessException;

}
