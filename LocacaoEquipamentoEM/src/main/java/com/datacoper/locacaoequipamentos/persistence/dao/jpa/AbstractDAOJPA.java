package com.datacoper.locacaoequipamentos.persistence.dao.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;

import com.datacoper.locacaoequipamentos.common.exception.BusinessException;
import com.datacoper.locacaoequipamentos.common.util.ReflectionUtils;
import com.datacoper.locacaoequipamentos.persistence.dao.DAO;

public abstract class AbstractDAOJPA<T extends Object, PK extends Object>  {

	private Class<T> entityClass;

	private EntityManager em;

	@SuppressWarnings("unchecked")
	public AbstractDAOJPA(EntityManager em) {
		this.em = em;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	public T save(T e) throws BusinessException {
		validateEntityJPA(e);
		if (getId(e) != null)
			return em.merge(e);
		else {
			em.persist(e);
			return e;
		}
	}

	public void remove(T e) throws BusinessException {
		validateEntityJPA(e);
		em.remove(e);
	}
	
	public T findByIdObj(T id) {
		return em.find(entityClass, id);
	}

	public T findById(PK id) {
		return em.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Query query = getPersistenceContext().createQuery("SELECT o FROM " + entityClass.getName() + " o");
		return (List<T>) query.getResultList();
	}

	protected EntityManager getPersistenceContext() {
		return this.em;
	}

	private void validateEntityJPA(T object) throws BusinessException {
		Class<?> classe = object.getClass();
		if (!classe.isAnnotationPresent(Entity.class)) {
			throw new BusinessException("Não é uma entidade persistente");
		}
	}

	private PK getId(T object) {
		try {
			return (PK) ReflectionUtils.getValueByFieldByAnnotation(Object.class, Id.class, object);
		} catch (IllegalArgumentException e) {
			new RuntimeException("Pau no getID");
		} catch (IllegalAccessException e) {
			new RuntimeException("Pau no getID");
		}
		return null;
	}
}
