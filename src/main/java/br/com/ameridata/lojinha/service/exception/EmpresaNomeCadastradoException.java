package br.com.ameridata.lojinha.service.exception;

public class EmpresaNomeCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 7484991713007896294L;

	public EmpresaNomeCadastradoException(String mensagem) {
		super(mensagem);
	}

}