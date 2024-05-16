package com.agsp.exception;

public class NaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 8748443033877573215L;
	
	public NaoEncontradoException(String msg) {
		super(msg);
	}

}
