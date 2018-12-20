package com.estudo.producer;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerProducer {
	
	@Produces
	@SessionScoped
	public EntityManagerFactory criaFactory() {
		return Persistence.createEntityManagerFactory("javaEEDB");
	}

	@Produces
	@SessionScoped
	public EntityManager criaEntityManager(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}

	public void finaliza(@Disposes EntityManager manager) {
		manager.close();
	}
	
}
