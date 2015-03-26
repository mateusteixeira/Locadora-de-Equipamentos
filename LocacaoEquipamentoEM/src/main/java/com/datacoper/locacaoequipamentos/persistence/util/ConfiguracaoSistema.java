/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.util;

import java.io.IOException;
import java.util.Properties;

import com.datacoper.locacaoequipamentos.persistence.dao.DAOFactory;

/**
 *
 * @author Java
 */
public class ConfiguracaoSistema {
    
    private static final Properties configuracaoSistema = new Properties();
    
    private ConfiguracaoSistema() {
        
    }
    
    static {
        try {
              configuracaoSistema.load(DAOFactory.class.getResourceAsStream("/META-INF/configuracaosistema.properties"));
        } catch (IOException ex) {
           throw new RuntimeException("Erro ao obter o arquivo de configuração do sistema", ex);
        }
    }
    
    public static String getValorConfiguracao(ParametrosEnum parametro) {
        String valorPropriedade = configuracaoSistema.getProperty(parametro.getChavePropriedade());
        return valorPropriedade;
    }
    
}
