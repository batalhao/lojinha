package br.com.ameridata.lojinha.service.exception;

public class ClienteNomeCadastradoException extends RuntimeException {

	private static final long serialVersionUID = -851452122054504263L;

	public ClienteNomeCadastradoException(String mensagem) {
		super(mensagem);
	}

}