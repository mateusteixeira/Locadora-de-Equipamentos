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
public class Manutencao {
    private int idManutencao;
    private int idMaquina;
    private int tempoManutencao;
    private String ultimaManutencao;
    private boolean emManutencao;

    public Manutencao() {
    }

    public int getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(int idManutencao) {
        this.idManutencao = idManutencao;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getTempoManutencao() {
        return tempoManutencao;
    }

    public void setTempoManutencao(int tempoManutencao) {
        this.tempoManutencao = tempoManutencao;
    }

    public String getUltimaManutencao() {
        return ultimaManutencao;
    }

    public void setUltimaManutencao(String ultimaManutencao) {
        this.ultimaManutencao = ultimaManutencao;
    }

    public boolean isEmManutencao() {
        return emManutencao;
    }

    public void setEmManutencao(boolean emManutencao) {
        this.emManutencao = emManutencao;
    }
    
}
