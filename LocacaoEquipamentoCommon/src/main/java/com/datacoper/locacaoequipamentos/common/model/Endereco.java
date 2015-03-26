package com.datacoper.locacaoequipamentos.common.model;



import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EnderecoPK id;

	@Column(name="ds_complemento")
	private String dsComplemento;

	@Column(name="nm_bairro")
	private String nmBairro;

	@Column(name="nm_logradouro")
	private String nmLogradouro;

	@Column(name="nr_cep")
	private String nrCep;

	@Column(name="nr_endereco")
	private String nrEndereco;

	//bi-directional many-to-one association to Cidade
	@ManyToOne
	@JoinColumn(name="cd_cidade")
	private Cidade cidade;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;

	public Endereco() {
	}

	public EnderecoPK getId() {
		return this.id;
	}

	public void setId(EnderecoPK id) {
		this.id = id;
	}

	public String getDsComplemento() {
		return this.dsComplemento;
	}

	public void setDsComplemento(String dsComplemento) {
		this.dsComplemento = dsComplemento;
	}

	public String getNmBairro() {
		return this.nmBairro;
	}

	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}

	public String getNmLogradouro() {
		return this.nmLogradouro;
	}

	public void setNmLogradouro(String nmLogradouro) {
		this.nmLogradouro = nmLogradouro;
	}

	public String getNrCep() {
		return this.nrCep;
	}

	public void setNrCep(String nrCep) {
		this.nrCep = nrCep;
	}

	public String getNrEndereco() {
		return this.nrEndereco;
	}

	public void setNrEndereco(String nrEndereco) {
		this.nrEndereco = nrEndereco;
	}

	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}