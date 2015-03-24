package com.datacoper.locacaoequipamentos.common.model.enums;

public enum EstadoCivil {

	SOLTEIRO('1',"Solteiro(a)"),
	CASADO('2',"Casado(a)"),
	DIVORCIADO('3',"Divorciado(a)"),
	VIUVO('4',"Viúvo(a)"),
	SEPARADO('5',"Separado(a)");
	
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
