package com.datacoper.locacaoequipamentos.common.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-27T16:20:16.802-0300")
@StaticMetamodel(Cidade.class)
public class Cidade_ {
	public static volatile SingularAttribute<Cidade, Integer> idCidade;
	public static volatile SingularAttribute<Cidade, Estado> cdEstado;
	public static volatile SingularAttribute<Cidade, String> nmCidade;
	public static volatile ListAttribute<Cidade, Endereco> enderecos;
}
