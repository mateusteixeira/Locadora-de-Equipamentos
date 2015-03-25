package com.datacoper.locacaoequipamentos.common.model;

public class Estado {
	private Integer idEstado;
	private String nmEstado;
	private String sgEstado;
	private Pais cdPais;
	
	public Estado() {
		super();
	}

	public Estado(Integer idEstado, String nmEstado, String sgEstado, Pais cdPais) {
		super();
		this.idEstado = idEstado;
		this.nmEstado = nmEstado;
		this.sgEstado = sgEstado;
		this.cdPais = cdPais;
	}

	public Integer getIdEstado() {
		return idEstado;
	}
	
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	public String getNmEstado() {
		return nmEstado;
	}
	
	public void setNmEstado(String nmEstado) {
		this.nmEstado = nmEstado;
	}
	
	public String getSgEstado() {
		return sgEstado;
	}
	
	public void setSgEstado(String sgEstado) {
		this.sgEstado = sgEstado;
	}
	
	public Pais getCdPais() {
		return cdPais;
	}
	
	public void setCdPais(Pais cdPais) {
		this.cdPais = cdPais;
	}
}
