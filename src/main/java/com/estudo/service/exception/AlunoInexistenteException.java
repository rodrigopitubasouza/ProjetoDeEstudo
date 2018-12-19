package com.estudo.service.exception;

public class AlunoInexistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7779854841589886133L;

	public AlunoInexistenteException() {
		super("Aluno inexistente");
	}
}
