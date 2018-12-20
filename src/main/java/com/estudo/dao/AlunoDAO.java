package com.estudo.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.estudo.entity.Aluno;

@Named
@RequestScoped
public class AlunoDAO extends GenericDAO<Aluno>{

	public AlunoDAO() {
		super(Aluno.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -30860686489829483L;
	
}
