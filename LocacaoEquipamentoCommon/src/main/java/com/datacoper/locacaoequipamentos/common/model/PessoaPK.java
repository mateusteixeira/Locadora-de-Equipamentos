package com.datacoper.locacaoequipamentos.common.model;

public class PessoaPK {
	private Pessoa idPessoa;
	private Integer cdEndereco;
	
	public PessoaPK() {
		super();
	}

	public PessoaPK(Pessoa idPessoa, Integer cdEndereco) {
		super();
		this.idPessoa = idPessoa;
		this.cdEndereco = cdEndereco;
	}

	public Pessoa getIdPessoa() {
		return idPessoa;
	}
	
	public void setIdPessoa(Pessoa idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	public Integer getCdEndereco() {
		return cdEndereco;
	}
	
	public void setCdEndereco(Integer cdEndereco) {
		this.cdEndereco = cdEndereco;
	}
}
