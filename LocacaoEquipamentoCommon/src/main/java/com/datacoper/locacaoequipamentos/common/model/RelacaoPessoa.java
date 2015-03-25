package com.datacoper.locacaoequipamentos.common.model;

public class RelacaoPessoa {
	private Integer idRelacaoPessoa;
	private String dsRelacaoPessoa;
	
	public RelacaoPessoa() {
		super();
	}
	
	public RelacaoPessoa(Integer idRelacaoPessoa, String dsRelacaoPessoa) {
		super();
		this.idRelacaoPessoa = idRelacaoPessoa;
		this.dsRelacaoPessoa = dsRelacaoPessoa;
	}
	public Integer getIdRelacaoPessoa() {
		return idRelacaoPessoa;
	}
	public void setIdRelacaoPessoa(Integer idRelacaoPessoa) {
		this.idRelacaoPessoa = idRelacaoPessoa;
	}
	public String getDsRelacaoPessoa() {
		return dsRelacaoPessoa;
	}
	public void setDsRelacaoPessoa(String dsRelacaoPessoa) {
		this.dsRelacaoPessoa = dsRelacaoPessoa;
	}
}
