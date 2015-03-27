package com.datacoper.locacaoequipamentos.common.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-27T16:20:16.886-0300")
@StaticMetamodel(Endereco.class)
public class Endereco_ {
	public static volatile SingularAttribute<Endereco, EnderecoPK> id;
	public static volatile SingularAttribute<Endereco, String> dsComplemento;
	public static volatile SingularAttribute<Endereco, String> nmBairro;
	public static volatile SingularAttribute<Endereco, String> nmLogradouro;
	public static volatile SingularAttribute<Endereco, String> nrCep;
	public static volatile SingularAttribute<Endereco, String> nrEndereco;
	public static volatile SingularAttribute<Endereco, Cidade> cidade;
	public static volatile SingularAttribute<Endereco, Pessoa> pessoa;
}
