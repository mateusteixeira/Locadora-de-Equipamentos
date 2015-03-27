package com.datacoper.locacaoequipamentos.persistence.dao;

import java.util.List;

public interface DAO<T, PK> {

	public T save(T object) throws Exception;

	public void remove(T object) throws Exception;

	public T findById(PK id) throws Exception;
	
	public T findByIdObj(T obj) throws Exception;

	public List<T> search(String filtro, String valorFiltro, Class<T> classe) throws Exception;

	public List<T> getAll() throws Exception;

	public Integer nextId() throws Exception;
}
