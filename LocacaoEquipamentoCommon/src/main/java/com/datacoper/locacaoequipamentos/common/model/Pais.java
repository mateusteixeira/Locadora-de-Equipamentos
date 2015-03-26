package com.datacoper.locacaoequipamentos.common.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the pais database table.
 * 
 */
@Entity
@Table(name = "pais")
@NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_pais")

	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pais_id_seq")
	private Integer idPais;

	@Column(name = "nm_pais")
	private String nmPais;

	@Column(name = "sg_pais")
	private String sgPais;

	public Pais() {
	}

	public Integer getIdPais() {
		return this.idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public String getNmPais() {
		return this.nmPais;
	}

	public void setNmPais(String nmPais) {
		this.nmPais = nmPais;
	}

	public String getSgPais() {
		return this.sgPais;
	}

	public void setSgPais(String sgPais) {
		this.sgPais = sgPais;
	}

}