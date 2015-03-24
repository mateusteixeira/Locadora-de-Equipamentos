/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.common.model;

/**
 *
 * @author Java
 */
public class Maquina {
    private String marca;
    private String modelo;
    private double numeroSerie;
    private double custoAquisicao;
    private double custoDiaria;
    private String dataAquisicao;
    private double tempoManutencao;
    private int ID;
    private String dataCadastro;
    private boolean locado;
    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Maquina() {
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getCustoAquisicao() {
        return custoAquisicao;
    }

    public void setCustoAquisicao(double custoAquisicao) {
        this.custoAquisicao = custoAquisicao;
    }

    public double getCustoDiaria() {
        return custoDiaria;
    }

    public void setCustoDiaria(double custoDiaria) {
        this.custoDiaria = custoDiaria;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public double getTempoManutencao() {
        return tempoManutencao;
    }

    public void setTempoManutencao(double dataManutencao) {
        this.tempoManutencao = dataManutencao;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(double numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public boolean isLocado() {
        return locado;
    }

    public void setLocado(boolean locado) {
        this.locado = locado;
    }
    
    
    
    
}
