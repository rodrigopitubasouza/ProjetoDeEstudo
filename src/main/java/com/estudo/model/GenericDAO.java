package com.estudo.model;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
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

	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<T> type;
	
	public GenericDAO(Class<T> type) {
		super();
		this.type = type;
	}

	@Produces
	@SessionScoped
	public EntityManagerFactory criaFactory() {
		return Persistence.createEntityManagerFactory("javaEEDB");
	}

	@Produces
	@RequestScoped
	public EntityManager criaEntityManager(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}

	public void finaliza(@Disposes EntityManager manager) {
		manager.close();
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
