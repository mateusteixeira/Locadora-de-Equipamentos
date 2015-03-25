package com.datacoper.locacaoequipamentos.common.model;

public class Endereco {
	private PessoaPK cdPessoa;
	private String nrCep;
	private Cidade idCidade;
	private String nmBairro;
	private String nmLogradouro; 
	private String nrEndereco;
	private String dsComplemento;
	
	public Endereco() {
		super();
	}

	public Endereco(PessoaPK cdPessoa, String nrCep, Cidade idCidade, String nmBairro, String nmLogradouro, String nrEndereco, String dsComplemento) {
		super();
		this.cdPessoa = cdPessoa;
		this.nrCep = nrCep;
		this.idCidade = idCidade;
		this.nmBairro = nmBairro;
		this.nmLogradouro = nmLogradouro;
		this.nrEndereco = nrEndereco;
		this.dsComplemento = dsComplemento;
	}

	public PessoaPK getCdPessoa() {
		return cdPessoa;
	}
	
	public void setCdPessoa(PessoaPK cdPessoa) {
		this.cdPessoa = cdPessoa;
	}
	
	public String getNrCep() {
		return nrCep;
	}
	
	public void setNrCep(String nrCep) {
		this.nrCep = nrCep;
	}
	
	public Cidade getIdCidade() {
		return idCidade;
	}
	
	public void setIdCidade(Cidade idCidade) {
		this.idCidade = idCidade;
	}
	
	public String getNmBairro() {
		return nmBairro;
	}
	
	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}
	
	public String getNmLogradouro() {
		return nmLogradouro;
	}
	
	public void setNmLogradouro(String nmLogradouro) {
		this.nmLogradouro = nmLogradouro;
	}
	
	public String getNrEndereco() {
		return nrEndereco;
	}
	
	public void setNrEndereco(String nrEndereco) {
		this.nrEndereco = nrEndereco;
	}
	
	public String getDsComplemento() {
		return dsComplemento;
	}
	
	public void setDsComplemento(String dsComplemento) {
		this.dsComplemento = dsComplemento;
	}
}
