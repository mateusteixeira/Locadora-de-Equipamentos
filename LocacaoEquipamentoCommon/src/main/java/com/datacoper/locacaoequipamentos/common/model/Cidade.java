package com.datacoper.locacaoequipamentos.common.model;

import javax.persistence.Entity;
import javax.persistence.Table;

public class Cidade {
	private Integer idCidade;
	private String nmCidade;
	private Estado cdEstado;
	
	public Cidade() {
		super();
	}

	public Cidade(Integer idCidade, String nmCidade, Estado cdEstado) {
		super();
		this.idCidade = idCidade;
		this.nmCidade = nmCidade;
		this.cdEstado = cdEstado;
	}
	
	public Integer getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	public String getNmCidade() {
		return nmCidade;
	}
	public void setNmCidade(String nmCidade) {
		this.nmCidade = nmCidade;
	}
	public Estado getCdEstado() {
		return cdEstado;
	}
	public void setCdEstado(Estado cdEstado) {
		this.cdEstado = cdEstado;
	}
	
}
