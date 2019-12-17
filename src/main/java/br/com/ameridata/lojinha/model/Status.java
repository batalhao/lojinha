package br.com.ameridata.lojinha.model;

public enum Status {

	ATIVO("Ativo"), INATIVO("Inativo");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}