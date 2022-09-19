package io.github.mizaeldouglas.logapi.domain.exception;

public class NegocioExecption extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NegocioExecption(String message) {
		super(message);
	}

}
