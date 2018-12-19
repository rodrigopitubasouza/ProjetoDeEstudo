package com.estudo.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.estudo.entity.Aluno;
import com.estudo.model.AlunoDAO;
import com.estudo.service.exception.AlunoExistenteException;

@Named
@SessionScoped
public class AlunoService implements Serializable {

	private static final long serialVersionUID = -4754745878240744907L;

	@Inject
	AlunoDAO dao;
	
	public Aluno find(Integer id) {
		return dao.find(id);	
		
	}

	public void insert(Aluno aluno) throws AlunoExistenteException {
		Aluno alunoExistente = find(aluno.getId());
		if(alunoExistente == null) {
			aluno.setId(null); 
			dao.insert(aluno);
		}
		throw new AlunoExistenteException();
	}
	
	public void update(Aluno aluno) {
		dao.update(aluno);
	}
	
	public void remove(Integer id) {
		dao.delete(find(id));
	}

	public List<Aluno> findAll() {
		return dao.findAll();
	}
}
