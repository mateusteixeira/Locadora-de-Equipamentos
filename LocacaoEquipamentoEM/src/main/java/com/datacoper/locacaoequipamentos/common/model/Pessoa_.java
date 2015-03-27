package com.datacoper.locacaoequipamentos.common.model;

import com.datacoper.locacaoequipamentos.common.model.enums.EstadoCivil;
import com.datacoper.locacaoequipamentos.common.model.enums.Sexo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-27T16:20:16.900-0300")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ {
	public static volatile SingularAttribute<Pessoa, Integer> idPessoa;
	public static volatile SingularAttribute<Pessoa, Date> dtNascimento;
	public static volatile SingularAttribute<Pessoa, Date> dtCadastro;
	public static volatile SingularAttribute<Pessoa, EstadoCivil> idEstadoCivil;
	public static volatile SingularAttribute<Pessoa, String> nmPessoa;
	public static volatile SingularAttribute<Pessoa, String> nrCpf;
	public static volatile SingularAttribute<Pessoa, String> nrRg;
	public static volatile SingularAttribute<Pessoa, String> nrTelefone;
	public static volatile SingularAttribute<Pessoa, Sexo> sexo;
	public static volatile SingularAttribute<Pessoa, String> dsEmail;
	public static volatile ListAttribute<Pessoa, Endereco> enderecos;
	public static volatile SingularAttribute<Pessoa, RelacaoPessoa> relacaoPessoa;
}
