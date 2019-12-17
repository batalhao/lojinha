package br.com.ameridata.lojinha.model;

public enum TipoPessoa implements Comparable<TipoPessoa> {

	F("Física"), J("Jurídica");

	private String descricao;

	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}