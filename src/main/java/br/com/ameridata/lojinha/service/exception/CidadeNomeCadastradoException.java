package br.com.ameridata.lojinha.service.exception;

public class CidadeNomeCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 696245153529245690L;

	public CidadeNomeCadastradoException(String mensagem) {
		super(mensagem);
	}

}