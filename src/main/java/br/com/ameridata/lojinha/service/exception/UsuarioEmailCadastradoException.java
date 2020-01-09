package br.com.ameridata.lojinha.service.exception;

public class UsuarioEmailCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 4296768329868337492L;

	public UsuarioEmailCadastradoException(String mensagem) {
		super(mensagem);
	}

}
