/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.common.service;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.datacoper.locacaoequipamentos.common.util.MyServiceLoader;

/**
 *
 * @author Java
 */
public abstract class ServiceLocator {
    
	public static <T> T loadService(Class<T> interfaceServico) {
		return loadService(interfaceServico, null);
	}
	
    
    public static <T> T loadService(Class<T> interfaceServico, Object paramtersConstruct) {
        T service = null;
        MyServiceLoader serviceLoader =  MyServiceLoader.load(interfaceServico, paramtersConstruct);
        Iterator<T> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) 
            service = iterator.next();
        
        if (service == null)
            throw new RuntimeException("Não foi encontrado implementação para o serviço: " + interfaceServico.getCanonicalName());
        
        return service;
    }
}
