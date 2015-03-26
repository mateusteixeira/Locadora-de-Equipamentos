/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.util;

/**
 *
 * @author Java
 */
public enum ParametrosEnum {

    PERSISTENCETYPE("persistenceType");
    
    private ParametrosEnum(String chavePropriedade) {
        this.chavePropriedade = chavePropriedade;
    }
    
    private String chavePropriedade;

    public String getChavePropriedade() {
        return chavePropriedade;
    }
    
}
