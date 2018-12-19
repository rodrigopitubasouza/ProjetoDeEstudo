package com.estudo.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlunoEntityManager {
	
	@Produces @ApplicationScoped
	public EntityManagerFactory criaFactory() {
		return Persistence.createEntityManagerFactory("alunos");
	}
	
	@Produces @RequestScoped
	public EntityManager criaEntityManager(EntityManagerFactory factory) {
		 return factory.createEntityManager();
	}
	
	public void finaliza(@Disposes EntityManager manager) {
	      manager.close();
	   }
}
