package com.datacoper.locacaoequipamentos.common.model;

import java.util.Date;
import java.util.List;

import com.datacoper.locacaoequipamentos.common.annotation.ColumnTableSearch;
import com.datacoper.locacaoequipamentos.common.model.enums.EstadoCivil;
import com.datacoper.locacaoequipamentos.common.model.enums.Sexo;


public class Pessoa {
	@ColumnTableSearch(header="Codigo", width=70)
	private Integer idPessoa;
	@ColumnTableSearch(header="Nome", width=200)
	private String nmPessoa;
	@ColumnTableSearch(header="CPF", width=100)
	private String nrCpf;
	private String nrRg;
	private Date dtNascimento;
	private Sexo sexo;
	private EstadoCivil idEstadoCivil;
	private RelacaoPessoa idRelacaoPessoa;
	@ColumnTableSearch(header="Telefone", width=80)
	private String nrTelefone;
	
	private List<Endereco> enderecos;
	
	public Pessoa() {
		super();
	}

	public Pessoa(Integer idPessoa, String nmPessoa, String nrCpf, String nrRg, Date dtNascimento, Sexo sexo, EstadoCivil idEstadoCivil,
			RelacaoPessoa idRelacaoPessoa, String nrTelefone, List<Endereco> enderecos) {
		super();
		this.idPessoa = idPessoa;
		this.nmPessoa = nmPessoa;
		this.nrCpf = nrCpf;
		this.nrRg = nrRg;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo;
		this.idEstadoCivil = idEstadoCivil;
		this.idRelacaoPessoa = idRelacaoPessoa;
		this.nrTelefone = nrTelefone;
		this.enderecos = enderecos;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}
	
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	public String getNmPessoa() {
		return nmPessoa;
	}
	
	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}
	
	public String getNrCpf() {
		return nrCpf;
	}
	
	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}
	
	public String getNrRg() {
		return nrRg;
	}
	
	public void setNrRg(String nrRg) {
		this.nrRg = nrRg;
	}
	
	public Date getDtNascimento() {
		return dtNascimento;
	}
	
	public void setDtNascimento(Date dt_nascimento) {
		this.dtNascimento = dt_nascimento;
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public EstadoCivil getIdEstadoCivil() {
		return idEstadoCivil;
	}
	
	public void setIdEstadoCivil(EstadoCivil idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}
	
	public RelacaoPessoa getIdRelacaoPessoa() {
		return idRelacaoPessoa;
	}
	
	public void setIdRelacaoPessoa(RelacaoPessoa idRelacaoPessoa) {
		this.idRelacaoPessoa = idRelacaoPessoa;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}
