package com.datacoper.locacaoequipamentos.persistence.dao;

import java.util.List;

public interface DAO<T, PK> {

	public T save(T object) throws Exception;

	public void remove(T object) throws Exception;

	public T findById(PK id) throws Exception;

	public List<T> search(String filtro, String valorFiltro) throws Exception;

	public List<T> getAll() throws Exception;

	public Integer nextId() throws Exception;
}
