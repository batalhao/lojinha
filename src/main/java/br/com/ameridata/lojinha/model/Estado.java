package br.com.ameridata.lojinha.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Estado {

	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	@Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres")
	private String uf;

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) { 
		this.nome = nome;
	}

}
