package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.model.Endereco;
import com.datacoper.locacaoequipamentos.common.model.EnderecoPK;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.EnderecoDAO;

public class EnderecoDAOJdbc extends AbstractDAOJdbc<Endereco, EnderecoPK> implements EnderecoDAO {

	public EnderecoDAOJdbc(Class<Endereco> entityClass) {
		super(entityClass);
	}

	public Endereco save(Endereco object) throws Exception {
		if (((EnderecoPK) super.getId(object)) == null) {
			return super.insert(object);
		} else {
			return super.update(object);
		}
	}
	
	public List<Endereco> getEnderecosByPessoa(Pessoa p) {
		String sql = "SELECT * FROM " + getTableName(Endereco.class) + " WHERE ID_PESSOA = " + p.getIdPessoa();
		return getResult(sql, Endereco.class);
	}
}
