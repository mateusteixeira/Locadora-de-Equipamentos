package com.datacoper.locacaoequipamentos.common.model.enums;

public enum Comparador {
	IGUAL("="),
	LIKE("LIKE"), 
	DIFERENTE_DE("<>");
	
	private String sql;

	private Comparador(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
