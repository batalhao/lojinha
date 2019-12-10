package br.com.ameridata.lojinha.model;

public enum TipoEntidade {

	F("Física"), J("Juríca");

	private String descricao;

	TipoEntidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}