package com.datacoper.locacaoequipamentos.common.model;

public class Pais {
	private Integer id_pais;
	private String  nm_pais;
	private String  sg_pais;

	public Pais() {
		super();
	}

	public Pais(Integer id_pais, String nm_pais, String sg_pais) {
		super();
		this.id_pais = id_pais;
		this.nm_pais = nm_pais;
		this.sg_pais = sg_pais;
	}

	public Integer getId_pais() {
		return id_pais;
	}
	
	public void setId_pais(Integer id_pais) {
		this.id_pais = id_pais;
	}
	
	public String getNm_pais() {
		return nm_pais;
	}
	
	public void setNm_pais(String nm_pais) {
		this.nm_pais = nm_pais;
	}
	
	public String getSg_pais() {
		return sg_pais;
	}
	
	public void setSg_pais(String sg_pais) {
		this.sg_pais = sg_pais;
	}
}
