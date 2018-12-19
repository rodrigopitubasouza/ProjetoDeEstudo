package com.estudo.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.estudo.entity.Aluno;

@Named
@ViewScoped
public class AlunoDAO implements Serializable{

	private static final long serialVersionUID = -8378713730286932610L;
	
	@Inject
	private EntityManager alunoManager;
	
	public Aluno find(Integer id) {
		return alunoManager.find(Aluno.class, id);
	}
	
	public List<Aluno> findAll() {
		Query query = alunoManager.createQuery("Select matricula,nome from Aluno");
		return (List<Aluno>) query.getResultList() ;
	}
	
	@Transactional
	public void insert(Aluno aluno) {
		alunoManager.persist(aluno);
	}

	@Transactional
	public void update(Aluno aluno) {
		alunoManager.merge(aluno);
	}
	
	@Transactional
	public void delete(Aluno aluno) {
		alunoManager.remove(aluno);
	}
	

}
