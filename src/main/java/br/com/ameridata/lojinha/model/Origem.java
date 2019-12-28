package br.com.ameridata.lojinha.model;

public enum Origem {

	NACIONAL("Nacional"), INTERNACIONAL("Internacional");

	private String descricao;

	Origem(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getDescricaoAbreviada() {
		return this.descricao.substring(0, 3) + ".";
	}

}
