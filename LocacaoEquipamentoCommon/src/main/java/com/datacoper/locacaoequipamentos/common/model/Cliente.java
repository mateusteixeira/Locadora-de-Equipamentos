/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.common.model;

import com.datacoper.locacaoequipamentos.common.annotation.ColumnTableSearch;

/**
 *
 * @author Java
 */
public class Cliente extends Pessoa {
	@ColumnTableSearch(header="Código", width=30)
    private Integer idCliente;

    public Cliente() { //Construtor
    }

    public void setIdCliente(int id) {
        this.idCliente = id;
    }

    public Integer getIdCliente() {
        return this.idCliente;
    }
}
