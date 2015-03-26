package com.datacoper.locacaoequipamentos.common.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the relacao_pessoa database table.
 * 
 */
@Entity
@Table(name = "relacao_pessoa")
@NamedQuery(name = "RelacaoPessoa.findAll", query = "SELECT r FROM RelacaoPessoa r")
public class RelacaoPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_relacao_pessoa")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "relacao_pessoa_id_relacao_pessoa_seq")
	private Integer idRelacaoPessoa;

	@Column(name = "ds_relacao_pessoa")
	private String dsRelacaoPessoa;

	// bi-directional many-to-one association to Pessoa
	@OneToMany(mappedBy = "relacaoPessoa")
	private List<Pessoa> pessoas;

	public RelacaoPessoa() {
	}

	public RelacaoPessoa(Integer idRelacaoPessoa, String dsRelacaoPessoa, List<Pessoa> pessoas) {
		super();
		this.idRelacaoPessoa = idRelacaoPessoa;
		this.dsRelacaoPessoa = dsRelacaoPessoa;
		this.pessoas = pessoas;
	}

	public RelacaoPessoa(Integer idRelacaoPessoa) {
		super();
		this.idRelacaoPessoa = idRelacaoPessoa;

	}

	public RelacaoPessoa(Integer idRelacaoPessoa, String dsRelacaoPessoa) {
		super();
		this.idRelacaoPessoa = idRelacaoPessoa;
		this.dsRelacaoPessoa = dsRelacaoPessoa;
	}

	public Integer getIdRelacaoPessoa() {
		return this.idRelacaoPessoa;
	}

	public void setIdRelacaoPessoa(Integer idRelacaoPessoa) {
		this.idRelacaoPessoa = idRelacaoPessoa;
	}

	public String getDsRelacaoPessoa() {
		return this.dsRelacaoPessoa;
	}

	public void setDsRelacaoPessoa(String dsRelacaoPessoa) {
		this.dsRelacaoPessoa = dsRelacaoPessoa;
	}

	public List<Pessoa> getPessoas() {
		return this.pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa addPessoa(Pessoa pessoa) {
		getPessoas().add(pessoa);
		pessoa.setRelacaoPessoa(this);

		return pessoa;
	}

	public Pessoa removePessoa(Pessoa pessoa) {
		getPessoas().remove(pessoa);
		pessoa.setRelacaoPessoa(null);

		return pessoa;
	}

}