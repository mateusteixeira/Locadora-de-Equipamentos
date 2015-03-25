package com.datacoper.locacaoequipamentos.common.model.enums;

public enum EstadoCivil {

	S('1',"Solteiro(a)"),
	C('2',"Casado(a)"),
	D('3',"Divorciado(a)"),
	V('4',"Viúvo(a)"),
	T('5',"Separado(a)");
	
	private char idEstadoCivil;
	private String descricao;
	
	EstadoCivil(char id,String descricao){
		this.descricao = descricao;
		idEstadoCivil = id;
	}
	
	public int getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(char idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
