/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.common.vo.ClienteEnderecoVO;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.ClienteDAO;

/**
 *
 * @author Java
 */
public class ClienteDAOJdbc extends AbstractDAOJdbc<Pessoa, Integer> implements ClienteDAO {

	@Override
	public List<Pessoa> search(String filtro, String valorFiltro) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}