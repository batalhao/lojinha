package br.com.ameridata.lojinha.service.exception;

public class SenhaObrigatoriaUsuarioException extends RuntimeException {

	private static final long serialVersionUID = -773951554954785259L;

	public SenhaObrigatoriaUsuarioException(String message) {
		super(message);
	}

}
