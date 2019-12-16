package br.com.ameridata.lojinha.service.exception;

public class ProdutoNomeCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 622031984101321346L;

	public ProdutoNomeCadastradoException(String mensagem) {
		super(mensagem);
	}

}
