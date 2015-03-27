package com.datacoper.locacaoequipamentos.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the cidade database table.
 * 
 */
@Entity
@NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_cidade")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cidade_id_seq")
	private Integer idCidade;

	@ManyToOne
	private Estado cdEstado;

	@Column(name = "nm_cidade")
	private String nmCidade;

	// bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy = "cidade")
	private List<Endereco> enderecos;

	public Cidade() {
	}

	public Integer getIdCidade() {
		return this.idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

	public Estado getCdEstado() {
		return this.cdEstado;
	}

	public void setCdEstado(Estado cdEstado) {
		this.cdEstado = cdEstado;
	}

	public String getNmCidade() {
		return this.nmCidade;
	}

	public void setNmCidade(String nmCidade) {
		this.nmCidade = nmCidade;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setCidade(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setCidade(null);

		return endereco;
	}

	@Override
	public String toString() {

		return nmCidade;
	}

}