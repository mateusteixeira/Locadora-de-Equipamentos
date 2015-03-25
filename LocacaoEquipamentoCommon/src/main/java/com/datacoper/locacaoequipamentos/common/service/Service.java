package com.datacoper.locacaoequipamentos.common.service;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.exception.BusinessException;

public interface Service {
	public abstract <T> List<T> pesquisar(String object, String valorFiltro) throws BusinessException;
}