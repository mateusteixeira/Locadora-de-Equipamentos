package com.datacoper.locacaoequipamentos.common.service;

import java.util.List;

public interface Service {
	public abstract <T> List<T> pesquisar(String object, String valorFiltro);
}