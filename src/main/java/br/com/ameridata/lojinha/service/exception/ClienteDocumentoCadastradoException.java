package br.com.ameridata.lojinha.service.exception;

public class ClienteDocumentoCadastradoException extends RuntimeException {

	private static final long serialVersionUID = -4522260163603022274L;

	public ClienteDocumentoCadastradoException(String mensagem) {
		super(mensagem);
	}

}