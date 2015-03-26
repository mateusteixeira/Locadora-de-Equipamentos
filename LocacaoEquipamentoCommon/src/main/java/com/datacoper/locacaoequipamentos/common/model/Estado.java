package com.datacoper.locacaoequipamentos.common.model;



import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_estado")

	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "estado_id_seq")
	private Integer idEstado;

	@Column(name="cd_pais")
	private Integer cdPais;

	@Column(name="nm_estado")
	private String nmEstado;

	@Column(name="sg_estado")
	private String sgEstado;

	public Estado() {
	}

	public Integer getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getCdPais() {
		return this.cdPais;
	}

	public void setCdPais(Integer cdPais) {
		this.cdPais = cdPais;
	}

	public String getNmEstado() {
		return this.nmEstado;
	}

	public void setNmEstado(String nmEstado) {
		this.nmEstado = nmEstado;
	}

	public String getSgEstado() {
		return this.sgEstado;
	}

	public void setSgEstado(String sgEstado) {
		this.sgEstado = sgEstado;
	}

}