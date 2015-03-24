package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

public class SequenceValue {

	private Integer value;
	
	public SequenceValue() {

	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
}
