package com.estudo.service.exception;

public class InexistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7779854841589886133L;

	public InexistenteException(String mensagem) {
		super(mensagem);
	}
}
