/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.common.model;

import com.datacoper.locacaoequipamentos.common.annotation.ColumnTableSearch;
import com.datacoper.locacaoequipamentos.common.model.enums.EstadoCivil;
import com.datacoper.locacaoequipamentos.common.model.enums.Sexo;

/**
 *
 * @author Java
 */

public class Pessoa {
	@ColumnTableSearch(header="Nome", width=200)
	private String nome;
	private String rg;
	@ColumnTableSearch(header="CPF", width=120)
	private String cpf;
	private EstadoCivil estadoCivil;
	private Sexo sexo;
	private String email;
	@ColumnTableSearch(header="Telefone", width=80)
	private String telefone;
	private String dataNascimento;
	private String dataCadastro;
	private Endereco endereco;

	public Pessoa() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
