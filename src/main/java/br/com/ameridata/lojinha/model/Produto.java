package br.com.ameridata.lojinha.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Produto {

	@NotBlank(message = "SKU é obrigatório")
	private String sku;
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
//	@NotBlank
	@Size(min = 1, max = 50, message = "A descrição deve ter tamanho entre 1 e 50")
	private String descricao;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}