package com.datacoper.locacaoequipamentos.persistence.dao.interfaces;

import java.util.List;

public interface DAO<T, PK> {
	public abstract void insert(T object) throws Exception;
	public abstract void update(T object) throws Exception;
	
	public abstract void delete(PK object) throws Exception;
	
	public List<T> pesquisar(String filtro, String valorFiltro) throws Exception;
	public List<T> pesquisarTodos() throws Exception;
	
	
	public Integer nextId() throws Exception;
}
