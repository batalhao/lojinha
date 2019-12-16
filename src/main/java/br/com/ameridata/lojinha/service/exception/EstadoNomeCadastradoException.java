package br.com.ameridata.lojinha.service.exception;

public class EstadoNomeCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 2930319431775136781L;

	public EstadoNomeCadastradoException(String mensagem) {
		super(mensagem);
	}

}