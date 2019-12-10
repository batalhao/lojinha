package br.com.ameridata.lojinha.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedores")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Fornecedor extends EntidadeExterna {

	private static final long serialVersionUID = 403540477421817310L;

	@OneToMany(mappedBy = "fornecedor")
	private List<Produto> produtos;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
