package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import java.util.Date;

import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.common.model.RelacaoPessoa;
import com.datacoper.locacaoequipamentos.common.model.enums.EstadoCivil;
import com.datacoper.locacaoequipamentos.common.model.enums.Sexo;

public class test {

	public static void main(String[] args) {
		Pessoa pessoa = new Pessoa(null,new Date(), EstadoCivil.C,"cesar","01010201010","09230913923","093290332",Sexo.FEMININO,null,new RelacaoPessoa(1));
		try {
			new ClienteDAOJdbc(Pessoa.class).insert(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
