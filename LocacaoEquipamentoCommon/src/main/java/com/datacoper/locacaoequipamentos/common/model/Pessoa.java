package com.datacoper.locacaoequipamentos.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.datacoper.locacaoequipamentos.common.annotation.ColumnTableSearch;
import com.datacoper.locacaoequipamentos.common.model.enums.Comparador;
import com.datacoper.locacaoequipamentos.common.model.enums.EstadoCivil;
import com.datacoper.locacaoequipamentos.common.model.enums.Sexo;

/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_pessoa")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pessoa_id_pessoa_seq")
	@ColumnTableSearch(header = "Código", index = 1)
	private Integer idPessoa;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento")
	private Date dtNascimento;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_cadastro")
	private Date dtCadastro;

	@Column(name = "id_estado_civil")
	@Enumerated(EnumType.ORDINAL)
	private EstadoCivil idEstadoCivil;

	@Column(name = "nm_pessoa")
	@ColumnTableSearch(header = "Nome", index = 2, comparador=Comparador.LIKE)
	private String nmPessoa;

	@Column(name = "nr_cpf")
	@ColumnTableSearch(header = "CPF", index = 3, comparador=Comparador.LIKE)
	private String nrCpf;

	@Column(name = "nr_rg")
	private String nrRg;

	@Column(name = "nr_telefone")
	@ColumnTableSearch(header = "Telefone", index = 4)
	private String nrTelefone;
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;
	
	@Column(name = "ds_email")
	private String dsEmail;

	// bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy = "pessoa")
	private List<Endereco> enderecos;

	// bi-directional many-to-one association to RelacaoPessoa
	@ManyToOne
	@JoinColumn(name = "id_relacao_pessoa")
	private RelacaoPessoa relacaoPessoa;

	public Pessoa() {
	}

	public Pessoa(Integer idPessoa, String nome) {
		this.idPessoa = idPessoa;
		this.nmPessoa = nome;
	}

	public Pessoa(Integer idPessoa, Date dtNascimento, EstadoCivil idEstadoCivil, String nmPessoa, String nrCpf, String nrRg, String nrTelefone, Sexo sexo,
			List<Endereco> enderecos, RelacaoPessoa relacaoPessoa) {
		this.idPessoa = idPessoa;
		this.dtNascimento = dtNascimento;
		this.idEstadoCivil = idEstadoCivil;
		this.nmPessoa = nmPessoa;
		this.nrCpf = nrCpf;
		this.nrRg = nrRg;
		this.nrTelefone = nrTelefone;
		this.sexo = sexo;
		this.enderecos = enderecos;
		this.relacaoPessoa = relacaoPessoa;
	}

	public Integer getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Date getDtNascimento() {
		return this.dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public EstadoCivil getIdEstadoCivil() {
		return this.idEstadoCivil;
	}

	public void setIdEstadoCivil(EstadoCivil idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public String getNmPessoa() {
		return this.nmPessoa;
	}

	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}

	public String getNrCpf() {
		return this.nrCpf;
	}

	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getNrRg() {
		return this.nrRg;
	}

	public void setNrRg(String nrRg) {
		this.nrRg = nrRg;
	}

	public String getNrTelefone() {
		return this.nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public Sexo getSexo() {
		return this.sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setPessoa(this);

		return endereco;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setPessoa(null);

		return endereco;
	}

	public RelacaoPessoa getRelacaoPessoa() {
		return this.relacaoPessoa;
	}

	public void setRelacaoPessoa(RelacaoPessoa relacaoPessoa) {
		this.relacaoPessoa = relacaoPessoa;
	}

	@Override
	public String toString() {
		return nmPessoa;
	}
}