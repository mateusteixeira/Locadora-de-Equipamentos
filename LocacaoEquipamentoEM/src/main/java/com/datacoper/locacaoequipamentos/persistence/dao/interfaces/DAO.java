package com.datacoper.locacaoequipamentos.persistence.dao.interfaces;

import java.util.List;

public interface DAO<T> {
	public List<T> pesquisar(String filtro, String valorFiltro);
}
