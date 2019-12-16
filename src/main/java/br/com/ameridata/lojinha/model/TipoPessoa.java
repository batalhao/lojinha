package br.com.ameridata.lojinha.model;

public enum TipoPessoa {

	F("Física"), J("Juríca");

	private String descricao;

	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}