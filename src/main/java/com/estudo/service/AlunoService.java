package com.estudo.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.estudo.dao.AlunoDAO;
import com.estudo.entity.Aluno;
import com.estudo.service.exception.InexistenteException;


@Named
@SessionScoped
public class AlunoService implements Serializable {

	private static final long serialVersionUID = -4754745878240744907L;

	@Inject
	AlunoDAO dao;
	
	public Aluno find(Integer id) throws InexistenteException {
		Aluno alunoExistente = dao.find(id);
		if(alunoExistente != null)
			return alunoExistente;	
		else
			throw new InexistenteException("Aluno inexistente");
	}

	public void insert(Aluno aluno)  {
		aluno.setId(null); 
		dao.insert(aluno);
	}
	
	public void update(Aluno aluno) throws InexistenteException {
		find(aluno.getId());
		dao.update(aluno);	
	}
	
	public void remove(Integer id) throws InexistenteException {
		dao.delete(find(id));
	}

	public List<Aluno> findAll() {
		return dao.findAll();
	}
}
