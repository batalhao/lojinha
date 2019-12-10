package br.com.ameridata.lojinha.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fabricantes")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Fabricante extends EntidadeExterna {

	private static final long serialVersionUID = 1554927925039454917L;

	@OneToMany(mappedBy = "fabricante")
	private List<Produto> produtos;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
