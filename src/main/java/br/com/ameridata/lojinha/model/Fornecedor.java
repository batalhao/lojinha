package br.com.ameridata.lojinha.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedores")
public class Fornecedor extends Pessoa {

	private static final long serialVersionUID = 403540477421817310L;

	@OneToMany(mappedBy = "fornecedor")
	private List<Produto> produtos;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
