package com.datacoper.locacaoequipamentos.common.service.interfaces;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.exception.BusinessException;

public interface PesquisaService {

	public abstract <T> List<T> pesquisar(String object, String valorFiltro) throws BusinessException;
	
	public abstract <T> T pesquisarById(T object) throws BusinessException;

}