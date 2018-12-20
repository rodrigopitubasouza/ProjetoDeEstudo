package com.estudo.model;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.estudo.interceptor.InterceptorTransactional;

@Named
@RequestScoped
public class GenericDAO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1289446525582740316L;

	@Inject
	private EntityManager entityManager;
	
	private Class<T> type;
	
	public GenericDAO(Class<T> type) {
		this.type = type;
	}
	
	public T find(Integer id) {
		return (T) entityManager.find(type,id);
	}
	
	public List<T> findAll() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(type);
		Root<T> root = cq.from(type);
		cq.select(root);
		return this.entityManager.createQuery(cq).getResultList();
	}
	
	@Interceptors(InterceptorTransactional.class)
	public void insert(T entidade) {
		entityManager.persist(entidade);
	}

	@Interceptors(InterceptorTransactional.class)
	public void update(T entidade) {
		entityManager.merge(entidade);
	}
	
	@Interceptors(InterceptorTransactional.class)
	public void delete(T entidade) {
		entityManager.remove(entidade);
	}
}
