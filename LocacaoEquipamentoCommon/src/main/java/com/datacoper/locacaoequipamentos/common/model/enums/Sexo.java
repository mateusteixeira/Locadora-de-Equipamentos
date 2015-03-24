package com.datacoper.locacaoequipamentos.common.model.enums;

public enum Sexo {
	
	MASCULINO('M', "Masculino"), FEMININO('F', "Feminino");
	private char idSexo;
	private String descricao;

	Sexo(char idSexo, String descricao) {
		this.idSexo = idSexo;
		this.descricao = descricao;
	}

	public char getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(char idSexo) {
		this.idSexo = idSexo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}