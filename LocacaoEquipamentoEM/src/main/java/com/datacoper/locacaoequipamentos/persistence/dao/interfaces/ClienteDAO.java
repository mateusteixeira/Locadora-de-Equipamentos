/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.dao.interfaces;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.model.Cliente;

/**
 *
 * @author Java
 */
public interface ClienteDAO extends DAO<Cliente> {

    public void insert(Cliente cliente);
    
    public Integer nextId();
    
    public List<Cliente> encontrarClienteAll();
    
    public void excluir(Cliente excluir);

	public void update(Cliente cliente);

//    public List<Cliente> buscaAll(int ordem, int ascDesc);
//
//    public List<Cliente> buscaEsp(int ordem, int ascDesc, int ID, String cont);
//
//    public void updateCliente(Cliente c);
//
//    public void excluiCliente(Cliente c);
}
