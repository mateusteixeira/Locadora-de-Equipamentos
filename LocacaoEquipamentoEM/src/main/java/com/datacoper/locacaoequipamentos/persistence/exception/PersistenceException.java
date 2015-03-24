package com.datacoper.locacaoequipamentos.persistence.exception;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PersistenceException(Throwable cause) {
		super(cause);
	}

}
