package com.datacoper.locacaoequipamentos.persistence.dao.interfaces;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.model.Endereco;
import com.datacoper.locacaoequipamentos.common.model.EnderecoPK;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.persistence.dao.DAO;

public interface EnderecoDAO extends DAO<Endereco, EnderecoPK> {
	public List<Endereco> getEnderecosByPessoa(Pessoa p);
}
