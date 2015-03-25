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
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.ClienteDAO;
import com.datacoper.locacaoequipamentos.vohandler.ClienteEnderecoHandler;
import com.dc.locacaoequipamentocommon.vo.ClienteEnderecoVO;

/**
 *
 * @author Java
 */
public class ClienteDAOJdbc extends GenericDAOJdbc implements ClienteDAO {

	static final String NOME_TABELA = "pessoa";
	
	static final String ID_PESSOA = "id_pessoa";
	static final String NM_PESSOA = "nm_pessoa";
	static final String NR_CPF = "nr_cpf";
	static final String NR_RG = "nr_rg";
	static final String DT_NASCIMENTO = "dt_nascimento";
	static final String SEXO = "sexo";
	static final String ID_ESTADO_CIVIL = "id_estado_civil";
	static final String ID_RELACAO_PESSOA = "id_relacao_pessoa";
	static final String NR_TELEFONE = "NR_TELEFONE";	

	public ClienteDAOJdbc(Connection connection) {
		super(connection);
	}
	
	@Override
	public Integer nextId() {
		return getNextSequenceValue("idcliente_pk_seq");
	}

	@Override
	public void insert(Pessoa pessoa) {
		String insertSQL = "INSERT INTO " + NOME_TABELA + " (" + ID_PESSOA + ", " + NM_PESSOA + ", " + NR_CPF + ", " + NR_RG + ", " + DT_NASCIMENTO + ", " + ID_ESTADO_CIVIL + ") VALUES (?,?,?,?,?,?,?,?)";

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(insertSQL);
			ps.setInt(1, pessoa.getIdPessoa());
			ps.setString(2, pessoa.getNmPessoa());
			ps.setString(3, pessoa.getNrCpf());
			ps.setString(4, pessoa.getNrRg());
			ps.setDate(5, new Date(pessoa.getDtNascimento().getTime()));
			ps.setString(6, String.valueOf(pessoa.getSexo().getIdSexo()));
			ps.setInt(7, pessoa.getIdEstadoCivil().getIdEstadoCivil());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (ps != null)
				closeResource(ps);
		}
	}

	@Override
	public List<Pessoa> pesquisarTodos() {
		String sqlSearch = "SELECT * FROM pessoa";
		List<ClienteEnderecoVO> clientes = getResult(sqlSearch, ClienteEnderecoVO.class);
		return null;//ClienteEnderecoHandler.getMultipleResult(clientes);
	}

	@Override
	public List<Pessoa> pesquisar(String campoPesquisar, String pesquisa) {
		String tag = null;
		String operador = null;
		switch (campoPesquisar) {
		case "nome": {
			tag = NM_PESSOA;
			operador = "LIKE";
			break;
		}
		case "codigo": {
			tag = ID_PESSOA;
			operador = "=";
			break;
		}
		case "cpf": {
			tag = NR_CPF;
			operador = "LIKE";
			break;
		}
		case "telefone": {
			tag = NR_TELEFONE;
			operador = "LIKE";
			break;
		}
		default: {
			tag = NM_PESSOA;
			operador = "LIKE";
		}
		}
		
		String sqlSearch = "SELECT " + NM_PESSOA + ", " + ID_PESSOA + ", " + NR_CPF + ", " + NR_TELEFONE + " FROM " + NOME_TABELA + " WHERE UPPER(" + tag + ") " + operador + " UPPER('%" + pesquisa + "%')";
		System.out.println(sqlSearch);
		List<Pessoa> pessoa = getResult(sqlSearch, Pessoa.class);
		return pessoa;
	}

	@Override
	public void delete(Integer pk) {
		String sqlString = "DELETE FROM " + NOME_TABELA + " WHERE " + ID_PESSOA + " = " + pk;
		
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sqlString);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (ps != null)
				closeResource(ps);
		}

	}

	@Override
	public void update(Pessoa pessoa) {

		String updateSQL = "UPDATE " + NOME_TABELA + " SET " + NM_PESSOA + " = ?, " + NR_CPF + " = ?, " + NR_RG + " = ?, " + DT_NASCIMENTO + " = ?, " + SEXO + " = ?, " + ID_ESTADO_CIVIL + " = ? WHERE " + ID_PESSOA + " = ?";

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(updateSQL);
			ps.setString(1, pessoa.getNmPessoa());
			ps.setString(2, pessoa.getNrCpf());
			ps.setString(3, pessoa.getNrRg());
			ps.setDate(4, new Date(pessoa.getDtNascimento().getTime()));
			ps.setString(5, String.valueOf(pessoa.getSexo().getIdSexo()));
			ps.setInt(6, pessoa.getIdEstadoCivil().getIdEstadoCivil());
			ps.setInt(7, pessoa.getIdPessoa());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (ps != null)
				closeResource(ps);
		}

	}
}